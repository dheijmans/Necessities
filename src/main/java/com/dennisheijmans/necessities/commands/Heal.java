package me.Tjeerd.MiniEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class Heal implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int length = args.length;
		if(length == 0) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("MiniEssentials.heal") || player.hasPermission("MiniEssentials.*")) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.setFireTicks(0);
					for(PotionEffect effect : player.getActivePotionEffects()) {
						player.removePotionEffect(effect.getType());
					}
					player.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been healed succesfully"));
					return true;
				} else {
					Messages.noPerm(player);
					return true;
				}
			} else {
				Messages.noPlayer(sender);
				return true;
			}
		} else if(length == 1) {
			if(sender.hasPermission("MiniEssentials.heal.other") || sender.hasPermission("MiniEssentials.*")) {
				boolean playerFound = false;
				for(Player target : Bukkit.getOnlinePlayers()) {
					if(target.getName().equalsIgnoreCase(args[0])) {
						
						target.setHealth(20);
						target.setFoodLevel(20);
						target.setFireTicks(0);
						for(PotionEffect effect : target.getActivePotionEffects()) {
							target.removePotionEffect(effect.getType());
						}
						target.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been healed by&2 " + sender.getName() + "!"));
						sender.sendMessage(MiniEssentials.pre + Color.cc("&2" + target.getName() + "&a has been healed succesfully!"));
						playerFound = true;
						break;
					}
				}
				
				if(playerFound == false) {
					Messages.noTarget(args[0], sender);
					return true;
				}
				
			} else {
				Messages.noPerm(sender);
				return true;
			}
		} else {
			sender.sendMessage(MiniEssentials.pre + Color.cc("&cUse: /heal <target>"));
			return true;
		}
		return true;
	}

}
