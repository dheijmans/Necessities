package me.Tjeerd.MiniEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Fly implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int length = args.length;
		if (length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("MiniEssentials.fly") || player.hasPermission("MiniEssentials.*")) {
						if (player.getAllowFlight() == true) {
							player.setAllowFlight(false);
							player.setFlying(false);
							player.sendMessage(MiniEssentials.pre + Color.cc("&cYou are not flying anymore!"));
							return true;
						} else {
							player.setAllowFlight(true);
							player.setFlying(true);
							player.sendMessage(MiniEssentials.pre + Color.cc("&aYou are now flying!"));
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

		} else if (length == 1) {
			boolean playerFound = false;
			for (Player target : Bukkit.getOnlinePlayers()) {
				if (target.getName().equalsIgnoreCase(args[0])) {
					if (sender.hasPermission("MiniEssentials.fly.other") || sender.hasPermission("MiniEssentials.*")) {
						if (!target.getAllowFlight() == true) {
							System.out.println("false");
							target.setAllowFlight(true);
							target.setFlying(true);
							target.sendMessage(MiniEssentials.pre + Color.cc("&aYou are now flying!"));
							sender.sendMessage(MiniEssentials.pre + Color.cc("&2" + target.getName() + "&a is now flying!"));
							playerFound = true;
							return true;
						} else {
							target.setAllowFlight(false);
							target.setFlying(false);
							target.sendMessage(MiniEssentials.pre + Color.cc("&cYou are not flying anymore!"));
							sender.sendMessage(MiniEssentials.pre + Color.cc("&4" + target.getName() + "&c is not flying anymore!"));
							playerFound = true;
							return true;
						}
					}
				}
			}
			if (playerFound == false) {
				Messages.noTarget(args[0], sender);
				return true;
			}

		} else {
			sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /fly <target>"));
			return true;
		}
		return true;
	}

}
