package com.dennisheijmans.necessities.blockshuffle;

import com.dennisheijmans.necessities.Necessities;
import com.dennisheijmans.necessities.tools.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BlockShuffle implements CommandExecutor {

    FileManager fm = FileManager.getInstance();
    Necessities plugin = Necessities.getInstance();
    BlockShuffleGame bsg = new BlockShuffleGame();

    public static boolean inBlockShuffle = false;
    private final String inventory = "inventory-saves.";
    private final String blocks = "blocks-allowed";

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
                    if(args[0].equalsIgnoreCase("start")) {
                        inBlockShuffle = true;
                        bsg.invSave();
                        bsg.startGame();
                        player.sendMessage(Message.NECESSITIES + Color.colorize("&bGame has just started!"));
                        return true;
                    } else if(args[0].equalsIgnoreCase("stop")) {
                        inBlockShuffle = false;
                        bsg.stopGame();
                        player.sendMessage(Message.NECESSITIES + Color.colorize("&cGame has ended!"));
                        return true;
                    } else if(args[0].equalsIgnoreCase("time")) {
                        if(length == 2) {
                            if(args[1].contains("min") || args[1].contains("s")) {
                                fm.getBlockshuffle().set("roundtime", args[1]);
                                fm.saveBlockshuffle();
                                player.sendMessage(Message.NECESSITIES + Color.colorize("&6The round time has been set to: " + args[1]));
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
