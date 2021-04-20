package me.Tjeerd.MiniEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Vanish implements CommandExecutor {

	FileManager fm = FileManager.getInstance();
	private MiniEssentials plugin = MiniEssentials.getInstance();


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
						player.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been vanished!"));
						return true;
					}

					if(fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
						for(Player other : Bukkit.getServer().getOnlinePlayers()) {
							other.showPlayer(plugin, player);

						}
						fm.getPlayerFile().set(player.getUniqueId().toString() + ".vanish", Boolean.valueOf("false"));
						fm.savePlayerFile();
						player.sendMessage(MiniEssentials.pre + Color.cc("&cYou have been un-vanished!"));
						return true;
					}

				} else if(args.length == 1){
					if(player.hasPermission("MiniEssentials.vanish.check")) {
						if(args[0].equalsIgnoreCase("check")) {
							if(fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
								player.sendMessage(MiniEssentials.pre + Color.cc("&aYou are vanished for other players!"));
								return true;
							} else if(!fm.getPlayerFile().getBoolean(player.getUniqueId().toString() + ".vanish")) {
								player.sendMessage(MiniEssentials.pre + Color.cc("&cYou are un-vanished for other players!"));
								return true;
							}
						} else {
							player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /vanish [check]!"));
							return true;
						}
					} else {
						Messages.noPerm(player);
						return true;
					}
				} else if(args.length > 1) {
					player.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /vanish [check]!"));
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
