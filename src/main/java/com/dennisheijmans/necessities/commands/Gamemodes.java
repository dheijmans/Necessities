package com.dennisheijmans.necessities.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Gamemodes implements CommandExecutor {
	
	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		int length = args.length;

		if (cmd.getName().equalsIgnoreCase("gm")) {
			if (length == 0) {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /gm [s/c/a/spec] [target]!"));
				return true;

				// Change your gamemode
			} else if (length == 1) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (args[0].equalsIgnoreCase("s")) {
						if (!(player.hasPermission("MiniEssentials.gm.s")
								|| player.hasPermission("MiniEssentials.*"))) {
							Messages.noPerm(player);
							return true;
						} else {
							if (player.getGameMode().equals(GameMode.SURVIVAL)) {
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are already in Survival Mode!"));
								return true;
							} else {
								player.setGameMode(GameMode.SURVIVAL);
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Survival Mode!"));
								fm.getPlayerFile().set(player.getUniqueId().toString() + ".fly", Boolean.valueOf(false));
								fm.savePlayerFile();
								return true;
							}
						}
					} else if (args[0].equalsIgnoreCase("c")) {
						if (!(player.hasPermission("MiniEssentials.gm.c")
								|| player.hasPermission("MiniEssentials.*"))) {
							Messages.noPerm(player);
							return true;
						} else {
							if (player.getGameMode().equals(GameMode.CREATIVE)) {
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are already in Creative Mode!"));
								return true;
							} else {
								player.setGameMode(GameMode.CREATIVE);
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Creative Mode!"));
								fm.getPlayerFile().set(player.getUniqueId().toString() + ".fly", Boolean.valueOf(true));
								fm.savePlayerFile();
								return true;
							}
						}
					} else if (args[0].equalsIgnoreCase("a")) {
						if (!(player.hasPermission("MiniEssentials.gm.a")
								|| player.hasPermission("MiniEssentials.*"))) {
							Messages.noPerm(player);
							return true;
						} else {
							if (player.getGameMode().equals(GameMode.ADVENTURE)) {
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are already in Adventure Mode!"));
								return true;
							} else {
								player.setGameMode(GameMode.ADVENTURE);
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Adventure Mode!"));
								fm.getPlayerFile().set(player.getUniqueId().toString() + ".fly", Boolean.valueOf(false));
								fm.savePlayerFile();
								return true;
							}
						}
					} else if (args[0].equalsIgnoreCase("spec")) {
						if (!(player.hasPermission("MiniEssentials.gm.spec")
								|| player.hasPermission("MiniEssentials.*"))) {
							Messages.noPerm(player);
							return true;
						} else {
							if (player.getGameMode().equals(GameMode.SPECTATOR)) {
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are already in Spectator Mode!"));
								return true;
							} else {
								player.setGameMode(GameMode.SPECTATOR);
								player.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Spectator Mode!"));
								fm.getPlayerFile().set(player.getUniqueId().toString() + ".fly", Boolean.valueOf(true));
								fm.savePlayerFile();
								return true;
							}
						}
					} else {
						player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /gm [s/c/a/spec] [target]!"));
						return true;
					}
				} else {
					Messages.noPlayer(sender);
					return true;
				}

				// Change other gamemode
			} else if (length == 2) {
				boolean playerFound = false;
				for(Player target : Bukkit.getServer().getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[1])) {
						if (args[0].equalsIgnoreCase("s")) {
							if (sender.hasPermission("MiniEssentials.gm.s.other")
									|| sender.hasPermission("MiniEssentials.*")) {
								if (target.getGameMode().equals(GameMode.SURVIVAL)) {
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&c" + target.getName() + " is already in Survival mode!"));
									playerFound = true;
									break;
								} else {
									target.setGameMode(GameMode.SURVIVAL);
									target.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Survival mode!"));
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&a" + target.getName() + " is now in Survival Mode!"));
									fm.getPlayerFile().set(target.getUniqueId().toString() + ".fly", Boolean.valueOf(false));
									fm.savePlayerFile();
									playerFound = true;
									break;
								}
							} else {
								Messages.noPerm(sender);
								break;
							}
						} else if (args[0].equalsIgnoreCase("c")) {
							if (sender.hasPermission("MiniEssentials.gm.c.other")
									|| sender.hasPermission("MiniEssentials.*")) {
								if (target.getGameMode().equals(GameMode.CREATIVE)) {
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&c" + target.getName() + " is already in Creative mode!"));
									playerFound = true;
									break;
								} else {
									target.setGameMode(GameMode.CREATIVE);
									target.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Creative mode!"));
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&a" + target.getName() + " is now in Creative Mode!"));
									fm.getPlayerFile().set(target.getUniqueId().toString() + ".fly", Boolean.valueOf(true));
									fm.savePlayerFile();
									playerFound = true;
									break;
								}
							} else {
								Messages.noPerm(sender);
								break;
							}
						} else if (args[0].equalsIgnoreCase("a")) {
							if (sender.hasPermission("MiniEssentials.gm.a.other")
									|| sender.hasPermission("MiniEssentials.*")) {
								if (target.getGameMode().equals(GameMode.ADVENTURE)) {
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&c" + target.getName() + " is already in Adventure mode!"));
									playerFound = true;
									break;
								} else {
									target.setGameMode(GameMode.ADVENTURE);
									target.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Adventure mode!"));
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&a" + target.getName() + " is now in Adventure Mode!"));
									fm.getPlayerFile().set(target.getUniqueId().toString() + ".fly", Boolean.valueOf(false));
									fm.savePlayerFile();
									playerFound = true;
									break;
								}
							} else {
								Messages.noPerm(sender);
								break;
							}
						} else if (args[0].equalsIgnoreCase("spec")) {
							if (sender.hasPermission("MiniEssentials.gm.spec.other")
									|| sender.hasPermission("MiniEssentials.*")) {
								if (target.getGameMode().equals(GameMode.SPECTATOR)) {
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&c" + target.getName() + " is already in Spectator mode!"));
									playerFound = true;
									break;
								} else {
									target.setGameMode(GameMode.SPECTATOR);
									target.sendMessage(MiniEssentials.pre + Color.cc("&3You are now in Spectator mode!"));
									sender.sendMessage(
											MiniEssentials.pre + Color.cc("&a" + target.getName() + " is now in Spectator Mode!"));
									fm.getPlayerFile().set(target.getUniqueId().toString() + ".fly", Boolean.valueOf(true));
									fm.savePlayerFile();
									playerFound = true;
									break;
								}
							} else {
								Messages.noPerm(sender);
								break;
							}
						}
					}
				}
				if(playerFound == false) {
					Messages.noTarget(args[1], sender);
					return true;
				}
					
			} else {
				sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /gm [s/c/a/spec] [target]!"));
				return true;
			}
		}
		return true;
	}

}
