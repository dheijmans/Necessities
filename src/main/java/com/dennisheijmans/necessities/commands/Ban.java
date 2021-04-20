package me.Tjeerd.MiniEssentials.commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Ban implements CommandExecutor{
	
	FileManager fm = FileManager.getInstance();
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS yyyy-MM-dd");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(fm.getConfigFile().getConfigurationSection("BanMessage") == null) {
			fm.getConfigFile().set("BanMessage.defaultreason", "The ban hammer has spoken!");
			fm.getConfigFile().set("BanMessage.line1", "&bYou have been banned from the server!");
			fm.getConfigFile().set("BanMessage.line2", "&cBy: %player%");
			fm.getConfigFile().set("BanMessage.line3", "&cFor: %reason%");
			fm.getConfigFile().set("BanMessage.line4", "&eBanned till: &6%time%");
			fm.getConfigFile().set("BanMessage.line5", "&bYou can apply on our forums!");
			fm.saveConfigFile();

		}

		if(sender.hasPermission("MiniEssentials.ban") || sender.hasPermission("MiniEssentials.*")) {
			int length = args.length;

			if(length == 0) {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /ban [target] <time> <reason>!"));
				return true;
			} else if(length == 1) {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /ban [target] <time> <reason>!"));
				return true;
			} else if(length == 2){
				boolean playerFound = false;
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[0])) {
						if(fm.getBannedPlayers().getBoolean(target.getUniqueId().toString() + ".isBanned")) {
							sender.sendMessage(MiniEssentials.pre + Color.cc("&Colorhis player is already banned!"));
							return true;
						} else {
							String reason = "";
							for(String key : fm.getConfigFile().getConfigurationSection("BanMessage").getKeys(true)) {
								if(key.contains("line")) {
									reason = reason + fm.getConfigFile().getString("BanMessage." + key) + "\n";
								}
							}
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(new Date());
							Date unbanDate = new Date();
							
							if(args[1].contains("y")) {
								String stringAmount = args[1].replace("y", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.YEAR, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("mon")) {
								String stringAmount = args[1].replace("mon", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.MONTH, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("d")) {
								String stringAmount = args[1].replace("d", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.DAY_OF_YEAR, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("h")) {
								String stringAmount = args[1].replace("h", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.HOUR_OF_DAY, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("min")) {
								String stringAmount = args[1].replace("min", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.MINUTE, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("s")) {
								String stringAmount = args[1].replace("s", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.SECOND, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("ms")) {
								String stringAmount = args[1].replace("ms", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.MILLISECOND, amount);
								unbanDate = cal.getTime();
							} else {
								sender.sendMessage(MiniEssentials.pre + Color.cc("&cYou can use the following suffixes:"));
								sender.sendMessage(MiniEssentials.pre + Color.cc("&6y(year), mon(month), d(day), h(hour), min(minute), s(second), ms(millisecond)"));
								return true;
							}
							
							String unbanTime = sdf.format(unbanDate);
							reason = reason.replaceAll("%player%", sender.getName());
							reason = reason.replaceAll("%reason%", fm.getConfigFile().getString("BanMessage.defaultreason"));
							reason = reason.replaceAll("%time%", unbanTime);
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".isBanned", Boolean.valueOf(true));
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".reason", reason);
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".unbanTime", unbanTime);
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".banCount", Integer.valueOf(fm.getBannedPlayers().getInt(target.getUniqueId().toString() + ".banCount") + 1));
							fm.saveBannedPlayers();
							target.kickPlayer(Color.cc(reason));
							sender.sendMessage(MiniEssentials.pre + Color.cc("&4" + target.getName() + "&a has been banned succesfully!"));
							playerFound = true;
							return true;
						}
					}
				}

				if(playerFound == false) {
					Messages.noTarget(args[0], sender);
					return true;
				}

			} else if(length > 2) {
				boolean playerFound = false;
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[0])) {
						if(fm.getBannedPlayers().getBoolean(target.getUniqueId().toString() + ".isBanned")) {
							sender.sendMessage(MiniEssentials.pre + Color.cc("&Colorhis player is already banned!"));
							return true;
						} else {
							String reason = "";
							for(String key : fm.getConfigFile().getConfigurationSection("BanMessage").getKeys(true)) {
								if(key.contains("line")) {
									reason = reason + fm.getConfigFile().getString("BanMessage." + key) + "\n";
								}
							}

							StringBuilder str = new StringBuilder();

							for(int i = 2; i < args.length; i++) {
								str.append(args[i]).append(" ");
							}
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(new Date());
							Date unbanDate = new Date();
							
							if(args[1].contains("y")) {
								String stringAmount = args[1].replace("y", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.YEAR, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("mon")) {
									String stringAmount = args[1].replace("mon", "");
									int amount = Integer.parseInt(stringAmount);
									cal.add(Calendar.MONTH, amount);
									unbanDate = cal.getTime();
							} else if(args[1].contains("d")) {
								String stringAmount = args[1].replace("d", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.DAY_OF_YEAR, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("h")) {
								String stringAmount = args[1].replace("h", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.HOUR_OF_DAY, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("min")) {
								String stringAmount = args[1].replace("min", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.MINUTE, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("s")) {
								String stringAmount = args[1].replace("s", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.SECOND, amount);
								unbanDate = cal.getTime();
							} else if(args[1].contains("ms")) {
								String stringAmount = args[1].replace("ms", "");
								int amount = Integer.parseInt(stringAmount);
								cal.add(Calendar.MILLISECOND, amount);
								unbanDate = cal.getTime();
							} else {
								sender.sendMessage(MiniEssentials.pre + Color.cc("&cYou can use the following suffixes:"));
								sender.sendMessage(MiniEssentials.pre + Color.cc("&6y(year), mon(month), d(day), h(hour), min(minute), s(second), ms(millisecond)"));
								return true;
							}
							
							String unbanTime = sdf.format(unbanDate);
							reason = reason.replaceAll("%player%", sender.getName());
							reason = reason.replaceAll("%reason%", str.toString());
							reason = reason.replaceAll("%time%", unbanTime);
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".isBanned", Boolean.valueOf(true));
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".unbanTime", unbanTime);
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".reason", reason);
							fm.getBannedPlayers().set(target.getUniqueId().toString() + ".banCount", Integer.valueOf(fm.getBannedPlayers().getInt(target.getUniqueId().toString() + ".banCount") + 1));
							fm.saveBannedPlayers();
							target.kickPlayer(Color.cc(reason));
							sender.sendMessage(MiniEssentials.pre + Color.cc("&4" + target.getName() + "&a has been banned succesfully!"));
							playerFound = true;
							return true;
						}
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
