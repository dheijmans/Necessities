package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int length = args.length;
		if (length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("MiniEssentials.fly") || player.hasPermission("MiniEssentials.*")) {
						if (player.getAllowFlight()) {
							player.setAllowFlight(false);
							player.setFlying(false);
							player.sendMessage(Message.NECESSITIES + Color.colorize("&cYou are not flying anymore!"));
							return true;
						} else {
							player.setAllowFlight(true);
							player.setFlying(true);
							player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou are now flying!"));
							return true;
						}
				} else {
					Message.noPerm(player);
					return true;
				}
			} else {
				Message.noPlayer(sender);
				return true;
			}

		} else if (length == 1) {
			boolean playerFound = false;
			for (Player target : Bukkit.getOnlinePlayers()) {
				if (target.getName().equalsIgnoreCase(args[0])) {
					if (sender.hasPermission("MiniEssentials.fly.other") || sender.hasPermission("MiniEssentials.*")) {
						if (!target.getAllowFlight()) {
							System.out.println("false");
							target.setAllowFlight(true);
							target.setFlying(true);
							target.sendMessage(Message.NECESSITIES + Color.colorize("&aYou are now flying!"));
							sender.sendMessage(Message.NECESSITIES + Color.colorize("&2" + target.getName() + "&a is now flying!"));
							playerFound = true;
							return true;
						} else {
							target.setAllowFlight(false);
							target.setFlying(false);
							target.sendMessage(Message.NECESSITIES + Color.colorize("&cYou are not flying anymore!"));
							sender.sendMessage(Message.NECESSITIES + Color.colorize("&4" + target.getName() + "&c is not flying anymore!"));
							playerFound = true;
							return true;
						}
					}
				}
			}
			if (!playerFound) {
				Message.noTarget(args[0], sender);
				return true;
			}

		} else {
			sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /fly <target>"));
			return true;
		}
		return true;
	}

}
