package me.leafs.fakename.utils;

import java.util.Set;

import com.mojang.authlib.GameProfile;
import me.leafs.fakename.FakeName;
import me.leafs.fakename.commands.NameHandler;
import me.leafs.fakename.commands.Remove;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class NameUtils {
    @SuppressWarnings("unused") // it is used :(
    public static String apply(String input) {

        if (NameHandler.targets.isEmpty()) {
        	return input;
        }

        if (input == null) {
            return input; // don't proceed if the fakeName or input isn't set
        }
        
        Set<String> setOfKeys = NameHandler.targets.keySet();
        for (String realName : setOfKeys) {
        	if (!input.contains(realName)) {
                continue;
            }       
        	String fakeName =  NameHandler.targets.get(realName);
        	if (fakeName == null) {
        		continue;
        	}
            String colored = ChatUtils.color(fakeName);

            // add reset code to prevent color
            if (!colored.equals(fakeName)) {
                // TODO: 9/21/2020 make this better
                colored += EnumChatFormatting.RESET;
            }
            // return the input with the real name replaced
        	input = input.replace(realName, colored);
        }
        
        return input;

        
    }
}
