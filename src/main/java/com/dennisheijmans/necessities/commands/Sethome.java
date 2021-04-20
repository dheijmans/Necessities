package com.dennisheijmans.necessities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Sethome implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("MiniEssentials.sethome") || player.hasPermission("MiniEssentials.*")) {
				int length = args.length;
				if(length == 0) {
					player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /sethome [name]"));
					return true;
				} else if(length == 1) {
					String pName = player.getUniqueId().toString();
					String dir = pName + "." + args[0];
					String world = player.getWorld().getName();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					double yaw = player.getLocation().getYaw();
					double pitch = player.getLocation().getPitch();

					if(fm.getHomes().getConfigurationSection(pName) == null) {
						fm.getHomes().createSection(pName);
						if(fm.getHomes().getConfigurationSection(pName + "." + args[0]) == null) {
							fm.getHomes().createSection(dir.toString());
							fm.getHomes().set(dir + ".world", world);
							fm.getHomes().set(dir + ".x", x);
							fm.getHomes().set(dir + ".y", y);
							fm.getHomes().set(dir + ".z", z);
							fm.getHomes().set(dir + ".yaw", yaw);
							fm.getHomes().set(dir + ".pitch", pitch);
							fm.saveHomes();
							player.sendMessage(MiniEssentials.pre + Color.cc("&aYou home&2 " + args[0] + "&a has been set succesfully!"));
							return true;
						} else {
							player.sendMessage(MiniEssentials.pre + Color.cc("&cThe home&4 " + args[0] + "&c already exists!"));
							return true;
						}
					} else {
						if(fm.getHomes().getConfigurationSection(pName + "." + args[0]) == null) {
							fm.getHomes().createSection(dir.toString());
							fm.getHomes().set(dir + ".world", world);
							fm.getHomes().set(dir + ".x", x);
							fm.getHomes().set(dir + ".y", y);
							fm.getHomes().set(dir + ".z", z);
							fm.getHomes().set(dir + ".yaw", yaw);
							fm.getHomes().set(dir + ".pitch", pitch);
							fm.saveHomes();
							player.sendMessage(MiniEssentials.pre + Color.cc("&aYou home&2 " + args[0] + "&a has been set succesfully!"));
							return true;
						} else {
							player.sendMessage(MiniEssentials.pre + Color.cc("&cThe home&4 " + args[0] + "&c already exists!"));
							return true;
						}
					}


				} else if(length > 2) {
					player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /sethome [name]"));
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
