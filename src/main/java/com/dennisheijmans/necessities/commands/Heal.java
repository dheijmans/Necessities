package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Heal implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int length = args.length;
		if(length == 0) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("Necessities.heal") || player.hasPermission("Necessities.*")) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.setFireTicks(0);
					for(PotionEffect effect : player.getActivePotionEffects()) {
						player.removePotionEffect(effect.getType());
					}
					player.sendMessage(Message.NECESSITIES + Color.colorize("&aYou have been healed succesfully"));
					return true;
				} else {
					Message.noPerm(player);
					return true;
				}
			} else {
				Message.noPlayer(sender);
				return true;
			}
		} else if(length == 1) {
			if(sender.hasPermission("Necessities.heal.other") || sender.hasPermission("Necessities.*")) {
				boolean playerFound = false;
				for(Player target : Bukkit.getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[0])) {
						
						target.setHealth(20);
						target.setFoodLevel(20);
						target.setFireTicks(0);
						for(PotionEffect effect : target.getActivePotionEffects()) {
							target.removePotionEffect(effect.getType());
						}
						target.sendMessage(Message.NECESSITIES + Color.colorize("&aYou have been healed by&2 " + sender.getName() + "!"));
						sender.sendMessage(Message.NECESSITIES + Color.colorize("&2" + target.getName() + "&a has been healed succesfully!"));
						playerFound = true;
						break;
					}
				}
				
				if(playerFound == false) {
					Message.noTarget(args[0], sender);
					return true;
				}
				
			} else {
				Message.noPerm(sender);
				return true;
			}
		} else {
			sender.sendMessage(Message.NECESSITIES + Color.colorize("&cUse: /heal <target>"));
			return true;
		}
		return true;
	}

}
