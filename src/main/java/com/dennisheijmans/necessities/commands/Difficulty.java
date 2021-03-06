package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Difficulty implements CommandExecutor {

	FileManager fm = FileManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("Necessities.difficulty") || sender.hasPermission("Necessities.*")) {
			int length = args.length;
			if(length == 0) {
				sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /dif [p/e/n/h]!"));
				return true;
			} else if(length == 1){
				if(args[0].equalsIgnoreCase("p")) {
					for(World worlds : Bukkit.getWorlds()) {
						worlds.setDifficulty(org.bukkit.Difficulty.PEACEFUL);
					}
					fm.getConfigFile().set("difficulty", "peaceful");
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aThe difficulty has been set to &2Peaceful"));
					return true;
				} else if(args[0].equalsIgnoreCase("e")) {
					for(World worlds : Bukkit.getWorlds()) {
						worlds.setDifficulty(org.bukkit.Difficulty.EASY);
					}
					fm.getConfigFile().set("difficulty", "easy");
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aThe difficulty has been set to &2Easy"));
					return true;
				} else if(args[0].equalsIgnoreCase("n")) {
					for(World worlds : Bukkit.getWorlds()) {
						worlds.setDifficulty(org.bukkit.Difficulty.NORMAL);
					}
					fm.getConfigFile().set("difficulty", "normal");
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aThe difficulty has been set to &2Normal"));
					return true;
				} else if(args[0].equalsIgnoreCase("h")) {
					for(World worlds : Bukkit.getWorlds()) {
						worlds.setDifficulty(org.bukkit.Difficulty.HARD);
					}
					fm.getConfigFile().set("difficulty", "hard");
					sender.sendMessage(Message.NECESSITIES + Color.colorize("&aThe difficulty has been set to &2Hard"));
					return true;
				}
			} else {
				sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /dif [p/e/n/h]!"));
				return true;
			}
		} else {
			Message.noPerm(sender);
			return true;
		}
		return true;
	}

}
