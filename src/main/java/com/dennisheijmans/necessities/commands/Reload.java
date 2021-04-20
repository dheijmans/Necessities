package com.dennisheijmans.necessities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Reload implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("MiniEssentials.reload") || sender.hasPermission("MiniEssentials.*")) {
			int length = args.length;
			if(length <= 1) {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /me reload [all/config/players/home/ban]"));
				return true;
			} else if(length == 2) {
				if(args[1].equalsIgnoreCase("all")) {
					fm.reloadBannedPlayer();
					fm.reloadConfigFile();
					fm.reloadHomes();
					fm.reloadPlayerFile();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&aAll files have been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("config")) {
					fm.reloadConfigFile();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&aconfig.yml has been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("home")) {
					fm.reloadHomes();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&ahomes.yml been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("ban")) {
					fm.reloadBannedPlayer();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&abannedPlayer.yml has been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("players")) {
					fm.reloadPlayerFile();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&aplayerData.yml has been reloaded!"));
					return true;
				} else {
					sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /me reload [all/config/players/home/ban]"));
					return true;
				}
			} else if(length > 2) {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /me reload [all/config/players/home/ban]"));
				return true;
			}
		} else {
			Messages.noPerm(sender);
			return true;
		}
		return true;
	}

}
