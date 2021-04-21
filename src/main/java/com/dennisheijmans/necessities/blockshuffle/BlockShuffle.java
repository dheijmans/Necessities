package com.dennisheijmans.necessities.blockshuffle;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

                    } else if(args[1].equalsIgnoreCase("stop")) {

                    } else if(args[1].equalsIgnoreCase("time")) {

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
        return true;
    }
}
