package com.dennisheijmans.necessities.events;

import com.dennisheijmans.necessities.Necessities;
import com.dennisheijmans.necessities.tools.*;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnPlayerLeaveEvent implements Listener {

	FileManager fm = FileManager.getInstance();
	Necessities plugin = Necessities.getInstance();

	@EventHandler
	public void OnPlayerQuitEvent(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		String pName = player.getUniqueId().toString();
		
		//vanish
		if(fm.getPlayerFile().getBoolean(pName + ".vanish")) {
			e.setQuitMessage("");
		}
		
		//Ban
		if(fm.getBannedPlayers().getBoolean(pName + ".isBanned")) {
			e.setQuitMessage("");
		}


		//Check fly
		if(player.getGameMode().equals(GameMode.CREATIVE)) {
			if(!fm.getPlayerFile().getBoolean(pName + ".fly")) {
				fm.getPlayerFile().set(pName + ".fly", Boolean.valueOf(true));
				fm.savePlayerFile();
			}
		} else if(player.getGameMode().equals(GameMode.SURVIVAL)) {
			if(fm.getPlayerFile().getBoolean(pName + ".fly")) {
				fm.getPlayerFile().set(pName + ".fly", Boolean.valueOf(false));
				fm.savePlayerFile();
			}
		} else if(player.getGameMode().equals(GameMode.ADVENTURE)) {
			if(fm.getPlayerFile().getBoolean(pName + ".fly")) {
				fm.getPlayerFile().set(pName + ".fly", Boolean.valueOf(false));
				fm.savePlayerFile();
			}
		} else if(player.getGameMode().equals(GameMode.SPECTATOR)) {
			if(!fm.getPlayerFile().getBoolean(pName + ".fly")) {
				fm.getPlayerFile().set(pName + ".fly", Boolean.valueOf(true));
				fm.savePlayerFile();
			}
		}

	}

}
