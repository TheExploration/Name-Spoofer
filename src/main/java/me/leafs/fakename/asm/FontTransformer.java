package me.leafs.fakename.asm;

import me.leafs.fakename.utils.NameUtils;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Arrays;
import java.util.List;

public class FontTransformer implements IClassTransformer {
    private final List<String> methodNames = Arrays.asList("func_78256_a", "getStringWidth", "func_78255_a", "renderStringAtPos");

    @Override
    public byte[] transform(String name, String transformedName, byte[] data) {
        // only watch for the FontRenderer class
        if (!transformedName.equals("net.minecraft.client.gui.FontRenderer")) {
            return data;
        }

        // read and parse the class
        ClassReader reader = new ClassReader(data);
        ClassNode node = new ClassNode();

        reader.accept(node, 0);

        for (MethodNode method : node.methods) {
            FMLDeobfuscatingRemapper remapper = FMLDeobfuscatingRemapper.INSTANCE;
            String methodName = remapper.mapMethodName(node.name, method.name, method.desc);

            // check if the method is one we want to inject into
            if (!methodNames.contains(methodName)) {
                continue; // it is not
            }

            /*

            modify the first parameter in the method
            to get rid of any instances of the real name

            arg0 = FakeName#apply arg0

             */

            String nameUtils = Type.getInternalName(NameUtils.class);
            Type stringType = Type.getType(String.class);

            // create instructions
            InsnList inst = new InsnList();

            inst.add(new VarInsnNode(Opcodes.ALOAD, 1));
            inst.add(new MethodInsnNode(Opcodes.INVOKESTATIC, nameUtils, "apply", Type.getMethodDescriptor(stringType, stringType)));
            inst.add(new VarInsnNode(Opcodes.ASTORE, 1));

            method.instructions.insertBefore(method.instructions.getFirst(), inst);
            // we don't break here because there may be more methods
        }

        // when for loop breaks, write bytes and return
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);

        return writer.toByteArray();
    }
}
