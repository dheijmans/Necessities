package com.dennisheijmans.necessities.commands;

import java.util.ArrayList;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	ArrayList<String> homes = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("Necessities.home") || player.hasPermission("Necessities.*")) {
				int length = args.length;
				String pName = player.getUniqueId().toString();
				if(length == 0) {
					player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /home [name]"));
					player.sendMessage(Message.NECESSITIES + Color.colorize("&3Your current homes are:"));
					if(fm.getHomes().getConfigurationSection(pName) != null) {
						for(String key : fm.getHomes().getConfigurationSection(pName).getKeys(false)) {
							homes.add(key);
						}
						player.sendMessage(Color.colorize("&b" + homes.toString().replace("[", "' ").replace(",", " ' '").replace("]", " '")));
						homes.clear();
						return true;
					} else {
						player.sendMessage(Color.colorize("&b' '"));
						return true;
					}
				} else if(length == 1) {
					String dir = pName + "." + args[0];
					if(fm.getHomes().getConfigurationSection(dir) != null) {
						World world = Bukkit.getWorld(fm.getHomes().getString(dir + ".world"));
						Double x = fm.getHomes().getDouble(dir + ".x");
						Double y = fm.getHomes().getDouble(dir + ".y");
						Double z = fm.getHomes().getDouble(dir + ".z");
						Float yaw = (float) fm.getHomes().getDouble(dir + ".yaw");
						Float pitch = (float) fm.getHomes().getDouble(dir + ".pitch");
						Location loc = new Location(world, x, y, z, yaw, pitch);
						player.teleport(loc);
						player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou have been teleported to&2 " + args[0] + "&a!"));
						return true;
					} else {
						player.sendMessage(Message.NECESSITIES + Color.colorize("&cThe home&4 " + args[0] + "&c doesn't exist!"));
						player.sendMessage(Message.NECESSITIES + Color.colorize("&3Your current homes are:"));
						for(String key : fm.getHomes().getConfigurationSection(pName).getKeys(false)) {
							homes.add(key);
						}
						player.sendMessage(Color.colorize("&b" + homes.toString().replace("[", "' ").replace(",", " ' '").replace("]", " '")));
						homes.clear();
						return true;
					}
				} else {
					player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /home [name]"));
					player.sendMessage(Message.NECESSITIES + Color.colorize("&3Your current homes are:"));
					for(String key : fm.getHomes().getConfigurationSection(pName).getKeys(false)) {
						homes.add(key);
					}
					player.sendMessage(Color.colorize("&b" + homes.toString().replace("[", "' ").replace(",", " ' '").replace("]", " '")));
					homes.clear();
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
	}
}
