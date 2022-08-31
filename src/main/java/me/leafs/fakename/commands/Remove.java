package me.leafs.fakename.commands;

import me.leafs.fakename.FakeName;
import me.leafs.fakename.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import me.leafs.fakename.commands.NameHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;


import lombok.Getter;
import lombok.Setter;



public class Remove implements ICommand {

	public static String target = "null";

    
	@Override
    public String getCommandName() {
        return "remove";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/remove <string>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("removename", "reveal");
    }

   
    
    
    @Override
    public void processCommand(ICommandSender sender, String[] args) { 
    	
    	//File System

        //allow spaces cuz we're cool like that
        //String.join(" ", args);

    	if (args.length == 0) {
    		NameHandler.targets.clear();
            ChatUtils.printChat("&7All names have been&d reset&7. To use, type &b/spoof <name> or <target> <name>");
            
            try {
    			String jsonSave = new ObjectMapper().writeValueAsString(NameHandler.targets);
    			Files.write(NameHandler.filePath, jsonSave.getBytes(StandardCharsets.UTF_8));
    		} catch (JsonProcessingException e) {
    		
    		} catch (IOException e) {
    			ChatUtils.printChat(e.getMessage());
    		
    		} 
            return;
        } else { 
        	target = String.join(" ", args);
        	NameHandler.targets.put(target, ""); 
        	ChatUtils.printChat("&d" + StringUtils.swapCase(target) + "&7 has been hidden.");
        } 
        try {
			String jsonSave = new ObjectMapper().writeValueAsString(NameHandler.targets);
			Files.write(NameHandler.filePath, jsonSave.getBytes(StandardCharsets.UTF_8));
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
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos theList) {
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
