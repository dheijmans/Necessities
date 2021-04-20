package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.Necessities;
import com.dennisheijmans.necessities.tools.Color;
import com.dennisheijmans.necessities.tools.FileManager;
import com.dennisheijmans.necessities.tools.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {

	FileManager fm = FileManager.getInstance();
	private final Necessities plugin = Necessities.getInstance();


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("MiniEssentials.vanish") || player.hasPermission("MiniEssentials.*")) {
				if (args.length == 0) {
					if (!fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
						for (Player other : Bukkit.getServer().getOnlinePlayers()) {
							if (other.hasPermission("MiniEssentials.vanish.see") || other.hasPermission("MiniEssentials.*")) {
								continue;
							}
							other.hidePlayer(plugin, player);
						}
						fm.getPlayerFile().set(player.getUniqueId().toString() + ".vanish", Boolean.valueOf("true"));
						fm.savePlayerFile();
						player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou have been vanished!"));
						return true;
					}

					if(fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
						for(Player other : Bukkit.getServer().getOnlinePlayers()) {
							other.showPlayer(plugin, player);

						}
						fm.getPlayerFile().set(player.getUniqueId().toString() + ".vanish", Boolean.valueOf("false"));
						fm.savePlayerFile();
						player.sendMessage(Message.NECESSITIES + Color.colorize("&cYou have been un-vanished!"));
						return true;
					}

				} else if(args.length == 1){
					if(player.hasPermission("MiniEssentials.vanish.check")) {
						if(args[0].equalsIgnoreCase("check")) {
							if(fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
								player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou are vanished for other players!"));
								return true;
							} else if(!fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
								player.sendMessage(Message.NECESSITIES + Color.colorize("&cYou are un-vanished for other players!"));
								return true;
							}
						} else {
							player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /vanish [check]!"));
							return true;
						}
					} else {
						Message.noPerm(player);
						return true;
					}
				} else {
					player.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /vanish [check]!"));
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
		return true;
	}

}
