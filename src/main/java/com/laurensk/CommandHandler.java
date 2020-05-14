package com.laurensk;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandHandler {
    static CommandHandler commandHandler = new CommandHandler();
    static boolean isPluginEnabled = true;

    public boolean ddCommand(CommandSender sender, Command command, String commandLabel, String[] args){
        if (command.getName().equals("dd")) {
            if (args.length > 0) {
                switch (args[0]) {
                    case "enable":
                        if (checkPermission(sender)) {
                            commandEnable(sender);
                        } else {
                            PrintUtils.printUtils.noPermission(sender);
                        }
                        break;
                    case "disable":
                        if (checkPermission(sender)) {
                            commandDisable(sender);
                        } else {
                            PrintUtils.printUtils.noPermission(sender);
                        }
                        break;
                    default:
                        PrintUtils.printUtils.commandNotFound(sender);
                }
            } else {
                if (isPluginEnabled) {
                    commandDontDie(sender);
                } else {
                    PrintUtils.printUtils.notActive(sender);
                }
            }
        } else {
            PrintUtils.printUtils.commandNotFound(sender);
        }
        return true;
    }

    public boolean checkPermission(CommandSender player) {
        return player.isOp();
    }

    public boolean commandDontDie(CommandSender sender) {
        Player player = (Player) sender;
        if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
            PrintUtils.printUtils.alreadyActive(sender);
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 12*20, 100000));
            PrintUtils.printUtils.printCountdown(player, 10);
        }
        return true;
    }

    public boolean commandEnable(CommandSender player) {
        isPluginEnabled = true;
        PrintUtils.printUtils.pluginEnabled(player);
        return true;
    }

    public boolean commandDisable(CommandSender player) {
        isPluginEnabled = false;
        PrintUtils.printUtils.pluginDisabled(player);
        return true;
    }

}
