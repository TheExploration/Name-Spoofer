package me.leafs.fakename.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.ChatComponentText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {
    // stolen straight from EnumChatFormatting
    public static final Pattern COLOR_FORMAT = Pattern.compile("[§&][0-9a-fk-or]");

    public static void printChat(String message) {
        GuiNewChat chatGUI = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        message = ChatUtils.color(message);
        chatGUI.printChatMessage(new ChatComponentText(message));
    }

    public static String color(String input) {
        Matcher matcher = COLOR_FORMAT.matcher(input);

        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            input = input.replace(fullMatch, ChatFormatting.getByChar(fullMatch.charAt(1)).toString());
        }

        return input;
    }
}
