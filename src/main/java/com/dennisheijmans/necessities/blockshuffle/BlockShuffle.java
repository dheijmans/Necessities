package com.dennisheijmans.necessities.blockshuffle;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class BlockShuffle implements CommandExecutor {

    FileManager fm = FileManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Necessities.blockshuffle") || player.hasPermission("Necessities.*")) {
                int length = args.length;
                if(length == 0) {
                    player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /blockshuffle <start/stop/time> <time length>"));
                    return true;
                } else {
                    if(args[1].equalsIgnoreCase("start")) {
                        player.sendMessage(Message.NECESSITIES + Color.colorize("&bGame has started!"));
                        return true;
                    } else if(args[1].equalsIgnoreCase("stop")) {
                        player.sendMessage(Message.NECESSITIES + Color.colorize("&cGame has ended!"));
                        return true;
                    } else if(args[1].equalsIgnoreCase("time")) {
                        if(args[2] != null) {
                            if(args[2].contains("min") || args[2].contains("s")) {
                                fm.getBlockshuffle().set("roundtime", args[2]);
                                fm.saveBlockshuffle();
                                player.sendMessage(Message.NECESSITIES + Color.colorize("&6The round time has been set to: " + args[2]));
                                return true;
                            } else {
                                sender.sendMessage(Message.NECESSITIES + Color.colorize("&cYou can use the following suffixes for the round time:"));
                                sender.sendMessage(Message.NECESSITIES + Color.colorize("&6min (minute), s (second)"));
                                return true;
                            }
                        } else {
                            player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /blockshuffle <start/stop/time> <time length>"));
                            return true;
                        }
                    } else {
                        player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /blockshuffle <start/stop/time> <time length>"));
                        return true;
                    }
                }
            } else {
                Message.noPerm(player);
                return true;
            }
        } else {
            Message.noPlayer(sender);
            return true;
        }
    }
}
