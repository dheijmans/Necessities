package com.dennisheijmans.necessities.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Spawn implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			int length = args.length;
			String spawnloc = "spawnloc";
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
				if(player.hasPermission("MiniEssentials.setspawn") || player.hasPermission("MiniEssentials.*")) {
					if(length == 0) {
						String world = player.getWorld().getName();
						double x = player.getLocation().getX();
						double y = player.getLocation().getY();
						double z = player.getLocation().getZ();
						double yaw = player.getLocation().getYaw();
						double pitch = player.getLocation().getPitch();
						
						fm.getConfigFile().set(spawnloc + ".world", world);
						fm.getConfigFile().set(spawnloc + ".x", x);
						fm.getConfigFile().set(spawnloc + ".y", y);
						fm.getConfigFile().set(spawnloc + ".z", z);
						fm.getConfigFile().set(spawnloc + ".yaw", yaw);
						fm.getConfigFile().set(spawnloc + ".pitch", pitch);
						fm.saveConfigFile();
						player.sendMessage(MiniEssentials.pre + Color.cc("&2Spawn point&a has been set succesfully!"));
						return true;
					} else if(length > 0) {
						player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /setspawn"));
						return true;
					}
				} else {
					Messages.noPerm(player);
					return true;
				}
			} else if(cmd.getName().equalsIgnoreCase("spawn")){
				if(player.hasPermission("MiniEssentials.spawn") || player.hasPermission("MiniEssentials.*")) {
					if(length == 0) {
						if(fm.getConfigFile().getConfigurationSection(spawnloc) == null) {
							player.teleport(player.getWorld().getSpawnLocation());
							player.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been teleported to&2 spawn&a!"));
							return true;
						} else {
							World world = Bukkit.getWorld(fm.getConfigFile().getString(spawnloc + ".world"));
							Double x = fm.getConfigFile().getDouble(spawnloc + ".x");
							Double y = fm.getConfigFile().getDouble(spawnloc + ".y");
							Double z = fm.getConfigFile().getDouble(spawnloc + ".z");
							Float yaw = (float) fm.getConfigFile().getDouble(spawnloc + ".yaw");
							Float pitch = (float) fm.getConfigFile().getDouble(spawnloc + ".pitch");

							Location loc = new Location(world, x, y, z, yaw, pitch);
							player.teleport(loc);
							player.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been teleported to&2 spawn&a!"));
							return true;
						}
					} else if(length > 0) {
						player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /spawn"));
						return true;
					}
				} else {
					Messages.noPerm(player);
					return true;
				}
			}

		} else {
			Messages.noPlayer(sender);
			return true;
		}
		return true;
	}

}
