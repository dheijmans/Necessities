package me.Tjeerd.MiniEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Kick implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(fm.getConfigFile().getConfigurationSection("KickMessage") == null) {
			fm.getConfigFile().set("KickMessage.defaultreason", "You have been kicked!");
			fm.getConfigFile().set("KickMessage.line1", "&bYou have been kicked from the server!");
			fm.getConfigFile().set("KickMessage.line2", "&cBy: %player%");
			fm.getConfigFile().set("KickMessage.line3", "&cFor: %reason%");
			fm.saveConfigFile();

		}

		if(sender.hasPermission("MiniEssentials.kick") || sender.hasPermission("MiniEssentials.*")) {
			int length = args.length;

			if(length == 0) {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /kick [target] <reason>!"));
				return true;
			} else if(length == 1) {
				boolean playerFound = false;
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[0])) {
						String reason = "";
						for(String key : fm.getConfigFile().getConfigurationSection("KickMessage").getKeys(true)) {
							if(key.contains("line")) {
								reason = reason + fm.getConfigFile().getString("KickMessage." + key) + "\n";
							}
						}
						reason = reason.replaceAll("%player%", sender.getName());
						reason = reason.replaceAll("%reason%", fm.getConfigFile().getString("KickMessage.defaultreason"));
						target.kickPlayer(Color.cc(reason));
						sender.sendMessage(MiniEssentials.pre + Color.cc("&4" + target.getName() + "&a has been kicked succesfully!"));
						playerFound = true;
						return true;
					}
				}

				if(playerFound == false) {
					Messages.noTarget(args[0], sender);
					return true;
				}

			} else if(length > 1) {
				boolean playerFound = false;
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[0])) {
						String reason = "";
						for(String key : fm.getConfigFile().getConfigurationSection("KickMessage").getKeys(true)) {
							if(key.contains("line")) {
								reason = reason + fm.getConfigFile().getString("KickMessage." + key) + "\n";
							}
						}
						
						StringBuilder str = new StringBuilder();
						
						for(int i = 1; i < args.length; i++) {
							str.append(args[i]).append(" ");
						}
						
						reason = reason.replaceAll("%player%", sender.getName());
						reason = reason.replaceAll("%reason%", str.toString());
						target.kickPlayer(Color.cc(reason));
						sender.sendMessage(MiniEssentials.pre + Color.cc("&4" + target.getName() + "&a has been kicked succesfully!"));
						playerFound = true;
						return true;
					}
				}

				if(playerFound == false) {
					Messages.noTarget(args[0], sender);
					return true;
				}
			}

		} else {
			Messages.noPerm(sender);
			return true;
		}
		return true;
	}

}
