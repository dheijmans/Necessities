package me.Tjeerd.MiniEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class MOTD implements CommandExecutor {
	
	FileManager fm = FileManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("MiniEssentials.motd") || sender.hasPermission("MiniEssentials.*")) {
			int length = args.length;
			if(length == 0) {
				String message = fm.getConfigFile().getString("MOTD");
				message = message.replaceAll("%player%", sender.getName());
				sender.sendMessage(MiniEssentials.pre + Color.cc(message));
				return true;
			} else if(length >= 1) {
				if(args[0].equalsIgnoreCase("on")) {
					String motd = "motdenabled";
					fm.getConfigFile().set(motd, true);
					fm.saveConfigFile();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&2MOTD&a has been enabled!"));
					return true;
				} else if(args[0].equalsIgnoreCase("off")) {
					String motd = "motdenabled";
					fm.getConfigFile().set(motd, false);
					fm.saveConfigFile();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&4MOTD&c has been disabled!"));
					return true;
				} else if(args[0].equalsIgnoreCase("change")) {
					if(length == 1) {
						sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /motd <on/off/change> <text>"));
						return true;
					}
					String motd = "MOTD";
					String message = "";
					
					StringBuilder str = new StringBuilder();
					for(int i = 1; i < args.length; i++) {
						str.append(args[i]).append(" ");
					}
					
					message = str.toString();
					fm.getConfigFile().set(motd, message);
					fm.saveConfigFile();
					sender.sendMessage(MiniEssentials.pre + Color.cc("&2MOTD&a has been set to: "+ message));
					return true;
				} else {
					sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /motd <on/off/change> <text>"));
					return true;
				}
				
			}
		}
		return true;
	}

}
