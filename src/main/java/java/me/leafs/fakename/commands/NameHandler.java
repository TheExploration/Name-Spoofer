package me.leafs.fakename.commands;

import me.leafs.fakename.FakeName;
import me.leafs.fakename.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.mojang.authlib.GameProfile;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.*;
import java.util.stream.Stream;


public class NameHandler implements ICommand {

	public static Hashtable<String, String> targets = new Hashtable<>();
	public static String target = "null";
	public static Path filePath = null;
	public static void ReadConfig(File file)  {
		filePath = file.toPath();
		if (!file.exists()) {
		// can't read what doesn't exist
			targets = new Hashtable<String, String>();
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				ChatUtils.printChat(e.getMessage());
			}
			return;
		}
	
		StringBuilder contentBuilder = new StringBuilder();
	
		try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8))
	  	{
		//Read the content with Stream
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
		
		}
	
		try {
			targets = new ObjectMapper().readValue(contentBuilder.toString(), Hashtable.class);
		} catch (JsonProcessingException ex) {

		} 
} 
    
	@Override
    public String getCommandName() {
        return "spoof";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/spoof <name> or <target> <name>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("namespoof", "namespoofer", "fakename", "fakeign", "hidename", "revealname");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) { 
    	
    	//File System
    	//FakeName instance = FakeName.instance;

        // if no arguments were provided then reset their name
        if (args.length == 0) {
        	targets.clear();
            ChatUtils.printChat("&7All names have been&d reset&7. To use, type &b/spoof <name> or <target> <name>");
            return;
        }

        // allow spaces cuz we're cool like that
        //String.join(" ", args);
        if (args.length == 1) {       
        	GameProfile profile = Minecraft.getMinecraft().getSession().getProfile();
        	String realName = profile.getName();
        	String fakeName = String.join(" ", args);
        	targets.put(realName, fakeName); 
        	ChatUtils.printChat("&7Your name has been set to &d" + fakeName + "&7.");
        } else {
	       
	        String[] subarray = new String[args.length - 1];
	        System.arraycopy(args, 1, subarray, 0, subarray.length);
	        String fakeName = String.join(" ", subarray);
	        target = args[0];
	        targets.put(target, fakeName); 
	        ChatUtils.printChat("&d" + StringUtils.swapCase(target) + "&7 has been set to &d" + fakeName + "&7.");
        }
        try {
			String jsonSave = new ObjectMapper().writeValueAsString(targets);
			Files.write(filePath, jsonSave.getBytes(StandardCharsets.UTF_8));
		} catch (JsonProcessingException e) {
		
		} catch (IOException e) {
			ChatUtils.printChat(e.getMessage());
		
		} 
    
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
