package me.Tjeerd.MiniEssentials.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class OnPlayerLeaveEvent implements Listener {

	FileManager fm = FileManager.getInstance();
	MiniEssentials plugin = MiniEssentials.getInstance();

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
