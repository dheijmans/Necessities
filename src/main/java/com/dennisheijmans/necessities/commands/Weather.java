package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("sun")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("Necessities.sun") || player.hasPermission("Necessities.*")) {
                    player.getWorld().setStorm(false);
                    player.getWorld().setThundering(false);
                    player.sendMessage(Message.NECESSITIES + Color.colorize("&aThe weather has been set to&c sunny "));
                    return true;
                } else {
                    Message.noPerm(player);
                    return true;
                }
            } else {
                Message.noPlayer(sender);
                return true;
            }
        } else if(cmd.getName().equalsIgnoreCase("rain")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("Necessities.rain") || player.hasPermission("Necessities.*")) {
                    player.getWorld().setStorm(true);
                    player.getWorld().setThundering(false);
                    player.sendMessage(Message.NECESSITIES + Color.colorize("&aThe weather has been set to&c raining "));
                    return true;
                } else {
                    Message.noPerm(player);
                    return true;
                }
            } else {
                Message.noPlayer(sender);
                return true;
            }
        } else if(cmd.getName().equalsIgnoreCase("storm")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("Necessities.storm") || player.hasPermission("Necessities.*")) {
                    player.getWorld().setStorm(true);
                    player.getWorld().setThundering(true);
                    player.sendMessage(Message.NECESSITIES + Color.colorize("&aThe weather has been set to&c storming "));
                    return true;
                } else {
                    Message.noPerm(player);
                    return true;
                }
            } else {
                Message.noPlayer(sender);
                return true;
            }
        }
        return true;
    }
}
