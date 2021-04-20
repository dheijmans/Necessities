package com.dennisheijmans.necessities.commands;

import com.dennisheijmans.necessities.Necessities;
import com.dennisheijmans.necessities.tools.Message;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    private final Necessities plugin;

    public Teleport(Necessities plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.noPlayer(sender);
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("back")) {
            if(player.hasPermission("Necessities.back") || player.hasPermission("Necessities.*")) {
                Location placeOfDeath = plugin.onPlayerDeathEvent.deathLocations.get(player.getName());
                if (placeOfDeath != null) {
                    player.teleport(placeOfDeath);
                    Message.teleport(player, "place of death");
                } else {
                    Message.hasNo(player, "place of death");
                }
            }
        }
        return true;
    }
}
