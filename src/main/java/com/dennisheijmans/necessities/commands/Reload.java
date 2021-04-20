package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("Necessities.reload") || sender.hasPermission("Necessities.*")) {
			int length = args.length;
			if(length <= 1) {
				sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /me reload [all/config/players/home/ban]"));
				return true;
			} else if(length == 2) {
				if(args[1].equalsIgnoreCase("all")) {
					fm.reloadBannedPlayer();
					fm.reloadConfigFile();
					fm.reloadHomes();
					fm.reloadPlayerFile();
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aAll files have been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("config")) {
					fm.reloadConfigFile();
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aconfig.yml has been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("home")) {
					fm.reloadHomes();
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&ahomes.yml been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("ban")) {
					fm.reloadBannedPlayer();
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&abannedPlayer.yml has been reloaded!"));
					return true;
				} else if(args[1].equalsIgnoreCase("players")) {
					fm.reloadPlayerFile();
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aplayerData.yml has been reloaded!"));
					return true;
				} else {
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /me reload [all/config/players/home/ban]"));
					return true;
				}
			} else {
				sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /me reload [all/config/players/home/ban]"));
				return true;
			}
		} else {
			Message.noPerm(sender);
			return true;
		}
	}

}
