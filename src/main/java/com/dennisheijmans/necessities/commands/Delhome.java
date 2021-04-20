package com.dennisheijmans.necessities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Delhome implements CommandExecutor {
	
	FileManager fm = FileManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("MiniEssentials.delhome") || player.hasPermission("MiniEssentials.*")) {
				int length = args.length;
				String pName = player.getUniqueId().toString();
				if(length == 0) {
					player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /delhome [name]"));
					return true;
				} else if(length == 1) {
					if(fm.getHomes().getConfigurationSection(pName + "." + args[0]) != null) {
						String dir = pName + "." + args[0];
						fm.getHomes().set(dir, null);
						fm.saveHomes();
						player.sendMessage(MiniEssentials.pre + Color.cc("&aYour home&2 " + args[0] + "&a has been deleted succesfully!"));
						return true;
					} else {
						player.sendMessage(MiniEssentials.pre + Color.cc("&cThere isn't any home named&4 " + args[0] + "&c!"));
						return true;
					}
				} else if(length > 1) {
					player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /delhome [name]"));
					return true;
				}
			} else {
				Messages.noPerm(player);
				return true;
			}
		} else {
			Messages.noPlayer(sender);
			return true;
		}
		return true;
	}

}
