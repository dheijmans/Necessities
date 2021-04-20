package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			int length = args.length;
			String spawnloc = "spawnloc";
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
				if(player.hasPermission("Necessities.setspawn") || player.hasPermission("Necessities.*")) {
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
						player.sendMessage(Message.NECESSITIES + Color.colorize("&2Spawn point&a has been set succesfully!"));
						return true;
					} else {
						player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /setspawn"));
						return true;
					}
				} else {
					Message.noPerm(player);
					return true;
				}
			} else if(cmd.getName().equalsIgnoreCase("spawn")){
				if(player.hasPermission("Necessities.spawn") || player.hasPermission("Necessities.*")) {
					if(length == 0) {
						if(fm.getConfigFile().getConfigurationSection(spawnloc) == null) {
							player.teleport(player.getWorld().getSpawnLocation());
							player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou have been teleported to&2 spawn&a!"));
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
							player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou have been teleported to&2 spawn&a!"));
							return true;
						}
					} else {
						player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /spawn"));
						return true;
					}
				} else {
					Message.noPerm(player);
					return true;
				}
			}

		} else {
			Message.noPlayer(sender);
			return true;
		}
		return true;
	}

}
