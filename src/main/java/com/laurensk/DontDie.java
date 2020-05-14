package com.laurensk;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class DontDie extends JavaPlugin {

    private static DontDie instance;
    public static DontDie getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        // Plugin startup logic
        instance = this;
        //getServer().getPluginManager().registerEvents(new Listeners(), this);

    }

    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args){
        return CommandHandler.commandHandler.ddCommand(commandSender, command, commandLabel, args);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
