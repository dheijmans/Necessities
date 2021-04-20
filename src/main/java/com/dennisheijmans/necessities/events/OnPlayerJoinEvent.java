package me.Tjeerd.MiniEssentials.events;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Tjeerd.MiniEssentials.MiniEssentials;
import me.Tjeerd.MiniEssentials.utils.*;

public class OnPlayerJoinEvent implements Listener {

	FileManager fm = FileManager.getInstance();
	MiniEssentials me = MiniEssentials.getInstance();

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS yyyy-MM-dd");

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String pName = player.getUniqueId().toString();
		
		//MOTD
		if(fm.getConfigFile().getBoolean("motdenabled")) {
			String MOTDMes = fm.getConfigFile().getString("MOTD");
			MOTDMes = MOTDMes.replace("%player%", player.getName().toString());
			player.sendMessage(MiniEssentials.pre + Color.cc(MOTDMes));
		}

		//Playerdata file
		if(!player.hasPlayedBefore()) {
			fm.getPlayerFile().createSection(player.getUniqueId().toString());
			fm.getPlayerFile().set(pName + ".name", player.getDisplayName());
			fm.getPlayerFile().set(pName + ".vanish", Boolean.valueOf(false));
			fm.savePlayerFile();
		} 
		if(fm.getPlayerFile().getConfigurationSection(pName) == null) {
			fm.getPlayerFile().createSection(pName);
			fm.getPlayerFile().set(pName + ".name", player.getDisplayName());
			fm.getPlayerFile().set(pName + ".vanish", Boolean.valueOf(false));
			fm.savePlayerFile();
		}


		//Banned Players
		if(fm.getBannedPlayers().getConfigurationSection(pName) == null) {
			fm.getBannedPlayers().createSection(pName);
			fm.getBannedPlayers().set(pName + ".name", player.getDisplayName());
			fm.getBannedPlayers().set(pName + ".isBanned", Boolean.valueOf(false));
			fm.getBannedPlayers().set(pName + ".unbanTime", " ");
			fm.getBannedPlayers().set(pName + ".banCount", Integer.valueOf(0));
			fm.getBannedPlayers().set(pName + ".reason", " ");
			fm.saveBannedPlayers();
		}

		if(fm.getBannedPlayers().getBoolean(pName + ".isBanned")) {
			Calendar cal = Calendar.getInstance();
			String unbanTime = fm.getBannedPlayers().getString(pName + ".unbanTime");
			Date unbanDate = null;
			try {
				unbanDate = sdf.parse(unbanTime);
			} catch (ParseException e1) {
				System.out.println("Can't parse the time!");
			}
			Date currentDate = cal.getTime();
			if(unbanDate.compareTo(currentDate) >= 0) {
				String reason = "";
				reason = fm.getBannedPlayers().getString(pName + ".reason");
				e.setJoinMessage("");
				player.kickPlayer(Color.cc(reason));
			} else {	
				fm.getBannedPlayers().set(pName + ".unbanTime", " ");
				fm.getBannedPlayers().set(pName + ".reason", " ");
				fm.getBannedPlayers().set(pName + ".isBanned", Boolean.valueOf(false));
				fm.saveBannedPlayers();
			}
		}

		//Vanished Players
		if(fm.getPlayerFile().getBoolean(pName + ".vanish")) {
			e.setJoinMessage("");
			for(Player vplayer : Bukkit.getOnlinePlayers()) {
				vplayer.hidePlayer(me, player);
			}
		} else {
			for(Player vplayer : Bukkit.getOnlinePlayers()) {
				if(fm.getPlayerFile().getBoolean(vplayer.getUniqueId().toString() + ".vanish")) {
					player.hidePlayer(me, vplayer);
				}
			}
		}

		//Spawn
		if(fm.getConfigFile().getBoolean("tptospawn")) {
			String spawnloc = "spawnloc";
			if(fm.getConfigFile().getConfigurationSection(spawnloc) == null) {
				player.teleport(player.getWorld().getSpawnLocation());
				player.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been teleported to&2 spawn&a!"));
			} else {
				World world = Bukkit.getWorld(fm.getConfigFile().getString(spawnloc + ".world"));
				Double x = fm.getConfigFile().getDouble(spawnloc + ".x");
				Double y = fm.getConfigFile().getDouble(spawnloc + ".y");
				Double z = fm.getConfigFile().getDouble(spawnloc + ".z");
				Float yaw = (float) fm.getConfigFile().getDouble(spawnloc + ".yaw");
				Float pitch = (float) fm.getConfigFile().getDouble(spawnloc + ".pitch");

				Location loc = new Location(world, x, y, z, yaw, pitch);
				player.teleport(loc);
				player.sendMessage(MiniEssentials.pre + Color.cc("&aYou have been teleported to&2 spawn&a!"));
			}
		}
	}
}
