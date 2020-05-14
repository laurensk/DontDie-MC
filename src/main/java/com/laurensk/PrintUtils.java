package com.laurensk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class PrintUtils {
    static PrintUtils printUtils = new PrintUtils();

    public boolean noPermission(CommandSender player) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DontDie | You don't have to necessary permission to run this command");
        return true;
    }

    public boolean commandNotFound(CommandSender player) {
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DontDie | Unknown command...");
        return true;
    }

    public boolean actionCompleted(CommandSender player) {
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DontDie | Setting saved...");
        return true;
    }

    public boolean pluginEnabled(CommandSender player) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DontDie | DontDie is now enabled...");
        return true;
    }

    public boolean pluginDisabled(CommandSender player) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DontDie | DontDie is now disabled...");
        return true;
    }

    public boolean notActive(CommandSender player) {
        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "DontDie | The Plugin is not enabled.");
        return true;
    }

    public boolean alreadyActive(CommandSender player) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DontDie | You're already prevented from dying.");
        return true;
    }

    public boolean printCountdown(CommandSender player, int seconds) {
        notifyPlayersOnEnable(player);
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DontDie | You'll not die for 10 seconds!");

        new Thread(() -> {
            for (int i = seconds; i >= 0; i--) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i <= 0) {
                    notifyPlayersOnDisable(player);
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DontDie | Now you can die again!");
                } else {
                    player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "DontDie | " + i);
                }

            }

        }).start();

        return true;
    }

    public boolean notifyPlayersOnEnable(CommandSender sender) {
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (!player.getDisplayName().equals(sender.getName())) {
                player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "DontDie | " + sender.getName() + " has activated DontDie.");
            }
        }
        return true;
    }

    public boolean notifyPlayersOnDisable(CommandSender sender) {

        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (!player.getDisplayName().equals(sender.getName())) {
                player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "DontDie | " + sender.getName() + " can now die again.");
            }
        }
        return true;
    }

}
