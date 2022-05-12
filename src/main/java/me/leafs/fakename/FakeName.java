package me.leafs.fakename;

import lombok.Getter;
import me.leafs.fakename.commands.NameHandler;
import me.leafs.fakename.commands.Remove;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "name-spoofer", name = "Name Spoofer", version = "1.1")
public class FakeName {
    @Mod.Instance
    public static FakeName instance;


   

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register the handler command
        ClientCommandHandler.instance.registerCommand(new NameHandler());
        ClientCommandHandler.instance.registerCommand(new Remove());
    }

}
