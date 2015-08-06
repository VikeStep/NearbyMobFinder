package io.github.vikestep.nearbymobfinder;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class NearbyMobFinderTransformer implements IClassTransformer
{
    public static String entityPlayerClass = "xw";
    public static String tryToSleepMethod = "a";
    public static String tryToSleepDesc = "(Lcj;)Lxw$a;";
    public static String addChatComponentMethod = "b";

    public static String entityMobClass = "qk";
    public static String getName = "e_";
    public static String posX = "s";
    public static String posY = "t";
    public static String posZ = "u";

    public static String textComponentClass = "fa";
    public static String iChatComponentClass = "eu";


    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if (name.equals(entityPlayerClass))
        {
            return transformEntityPlayer(bytes);
        }
        return bytes;
    }

    private byte[] transformEntityPlayer(byte[] bytes)
    {
        System.out.println("Found the EntityPlayer class");

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals(tryToSleepMethod) && method.desc.equals(tryToSleepDesc))
            {
                System.out.println("Found the tryToSleep method");
                AbstractInsnNode nodeToFind = null;
                for (AbstractInsnNode insn : method.instructions.toArray())
                {
                    if (insn.getOpcode() == GETSTATIC && ((FieldInsnNode)insn).name.equals("f"))
                    {
                        nodeToFind = insn;
                        break;
                    }
                }
                if (nodeToFind == null)
                {
                    System.out.println("Unable to find isEmpty node");
                    break;
                }
                InsnList toInject = getInsnListToInsert();
                method.instructions.insertBefore(nodeToFind, toInject);
                //Replace NOT_SAFE with OTHER_PROBLEM status
                ((FieldInsnNode) nodeToFind).name = "e";
            }
        }

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(classWriter);
        return classWriter.toByteArray();
    }

    public static InsnList getInsnListToInsert()
    {
        /* Local Variables already in code:
        mv.visitLocalVariable("this", "Lxw;", null, l0, l40, 0);
        mv.visitLocalVariable("\u2603", "Lcj;", null, l0, l40, 1);
        mv.visitLocalVariable("\u2603", "D", null, l11, l1, 2);
        mv.visitLocalVariable("\u2603", "D", null, l12, l1, 4);
        mv.visitLocalVariable("\u2603", "Ljava/util/List;", "Ljava/util/List<Lxe;>;", l13, l1, 6);
        mv.visitLocalVariable("\u2603", "Lcq;", null, l20, l32, 2);
        mv.visitLocalVariable("\u2603", "F", null, l21, l32, 3);
        mv.visitLocalVariable("\u2603", "F", null, l22, l32, 4);

        Code being replaced:
        if(!?4.isEmpty()) {
            return xw.a.f;
        }

        to:

        if(!?4.isEmpty()) {
            for (int i = 0; i < ?4.size(); i++) {
                qk mob = ?4.get(i);
                String location = NearbyMobFinderTransformer.getStringToChat(mob.e_(), mob.s, mob.t, mob.u);
                fa chatComponent = new fa(location);
                this.b(chatComponent);
            }
            return xw.a.e;
        }

        New Local Variables:

        7: i
        9: mob
        9: location
        10: chatComponent

        Instructions to inject:

        ICONST_0
        ISTORE 7
        startForLabel
        ILOAD 7
        ALOAD 6
        INVOKEINTERFACE java/util/List size ()I true
        IF_ICMPGE endForLabel
        ALOAD 6
        ILOAD 7
        INVOKEINTERFACE java/util/List get (I)Ljava/lang/Object; true
        CHECKCAST qk
        ASTORE 8
        ALOAD 8
        INVOKEVIRTUAL qk e_ ()Ljava/lang/String; false
        ALOAD 8
        GETFIELD qk s D
        ALOAD 8
        GETFIELD qk t D
        ALOAD 8
        GETFIELD qk u D
        INVOKESTATIC io/github/vikestep/nearbymobfinder/NearbyMobFinderTransformer getStringToChat (Ljava/lang/String;DDD)Ljava/lang/String; false
        ASTORE 9
        NEW fa
        DUP
        ALOAD 9
        INVOKESPECIAL fa <init> (Ljava/lang/String;)V false
        ASTORE 10
        ALOAD 0
        ALOAD 10
        INVOKEVIRTUAL xw b (Leu;)V false
        IINC 7 1
        GOTO startForLabel

         */

        LabelNode startForLabel = new LabelNode();
        LabelNode endForLabel = new LabelNode();

        InsnList i = new InsnList();

        i.add(new InsnNode(ICONST_0));
        i.add(new VarInsnNode(ISTORE, 7));
        i.add(startForLabel);
        i.add(new VarInsnNode(ILOAD, 7));
        i.add(new VarInsnNode(ALOAD, 6));
        i.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "size", "()I", true));
        i.add(new JumpInsnNode(IF_ICMPGE, endForLabel));
        i.add(new VarInsnNode(ALOAD, 6));
        i.add(new VarInsnNode(ILOAD, 7));
        i.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;", true));
        i.add(new TypeInsnNode(CHECKCAST, entityMobClass));
        i.add(new VarInsnNode(ASTORE, 8));
        i.add(new VarInsnNode(ALOAD, 8));
        i.add(new MethodInsnNode(INVOKEVIRTUAL, entityMobClass, getName, "()Ljava/lang/String;", false));
        i.add(new VarInsnNode(ALOAD, 8));
        i.add(new FieldInsnNode(GETFIELD, entityMobClass, posX, "D"));
        i.add(new VarInsnNode(ALOAD, 8));
        i.add(new FieldInsnNode(GETFIELD, entityMobClass, posY, "D"));
        i.add(new VarInsnNode(ALOAD, 8));
        i.add(new FieldInsnNode(GETFIELD, entityMobClass, posZ, "D"));
        i.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(NearbyMobFinderTransformer.class), "getStringToChat", "(Ljava/lang/String;DDD)Ljava/lang/String;", false));
        i.add(new VarInsnNode(ASTORE, 9));
        i.add(new TypeInsnNode(NEW, textComponentClass));
        i.add(new InsnNode(DUP));
        i.add(new VarInsnNode(ALOAD, 9));
        i.add(new MethodInsnNode(INVOKESPECIAL, textComponentClass, "<init>", "(Ljava/lang/String;)V", false));
        i.add(new VarInsnNode(ASTORE, 10));
        i.add(new VarInsnNode(ALOAD, 0));
        i.add(new VarInsnNode(ALOAD, 10));
        i.add(new MethodInsnNode(INVOKEVIRTUAL, entityPlayerClass, addChatComponentMethod, "(L" + iChatComponentClass + ";)V", false));
        i.add(new IincInsnNode(7, 1));
        i.add(new JumpInsnNode(GOTO, startForLabel));
        i.add(endForLabel);

        return i;
    }

    public static String getStringToChat(String name, double x, double y, double z)
    {
        return name + " x: " + (int) x + ", z: " + (int) z + " (y: " + (int) y + ")";
    }
}
