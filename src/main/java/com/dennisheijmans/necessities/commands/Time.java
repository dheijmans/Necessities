package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.Message;
import com.dennisheijmans.necessities.tools.Tool;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.noPlayer(sender);
            return true;
        }
        Player player = (Player) sender;
        World world = player.getWorld();
        if (command.getName().equalsIgnoreCase("day")) {
            if(player.hasPermission("Necessities.day") || player.hasPermission("Necessities.*")) {
                world.setTime(1000);
                Message.setTime(player, "day");
            }
        } else if (command.getName().equalsIgnoreCase("night")) {
            if(player.hasPermission("Necessities.night") || player.hasPermission("Necessities.*")) {
                world.setTime(13000);
                Message.setTime(player, "night");
            }
        } else if (command.getName().equalsIgnoreCase("time")) {
            if(player.hasPermission("Necessities.time") || player.hasPermission("Necessities.*")) {
                if (args.length == 1 && Tool.isLong(args[0])) {
                    world.setTime(Long.parseLong(args[0]));
                    Message.setTime(player, args[0]);
                } else {
                    Message.invalidArguments(player);
                }
            }
        }
        return true;
    }
}
