package me.Tjeerd.MiniEssentials.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Tjeerd.MiniEssentials.MiniEssentials;

public class FileManager {
	
	private static FileManager fm = new FileManager();
	
	
	public static FileManager getInstance() {
		return fm;
	}
	
	//FileConfiguration
	private FileConfiguration config;
	private FileConfiguration player;
	private FileConfiguration ban;
	private FileConfiguration homes;
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//File
	private File cfile;
	private File pfile;
	private File bfile;
	private File hfile;
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setup(MiniEssentials plugin) {
		
		//Naming Files
		cfile = new File(plugin.getDataFolder(), "config.yml");
		pfile = new File(plugin.getDataFolder(), "Playerdata.yml");
		bfile = new File(plugin.getDataFolder(), "bannedPlayers.yml");
		hfile = new File(plugin.getDataFolder(), "homes.yml");
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Creating Files
		if(!cfile.exists()) {
			plugin.saveResource("config.yml", false);
		}
		
		if(!pfile.exists()) {
			plugin.saveResource("Playerdata.yml", false);
		}
		
		if(!bfile.exists()) {
			plugin.saveResource("bannedPlayers.yml", false);
		}
		
		if(!hfile.exists()) {
			plugin.saveResource("homes.yml", false);
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//YamlConfiguration
		config = YamlConfiguration.loadConfiguration(this.cfile);
		player = YamlConfiguration.loadConfiguration(this.pfile);
		ban = YamlConfiguration.loadConfiguration(this.bfile);
		homes = YamlConfiguration.loadConfiguration(this.hfile);
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
	}
	
	//Methods
	public FileConfiguration getConfigFile() {
		return this.config;
	}
	
	public void saveConfigFile() {
		try {
			this.config.save(cfile);
			ConfigUpdater.update(MiniEssentials.getInstance(), "config.yml", cfile, Arrays.asList("none"));
			reloadConfigFile();
		} catch (IOException e) {
			Messages.noFileSave("config.yml"); 
		}
	}
	
	public void reloadConfigFile() {
		this.config = YamlConfiguration.loadConfiguration(this.cfile);
	}
	
	//------------------------------------------------------------------
	
	public FileConfiguration getPlayerFile() {
		return this.player;
	}
	
	public void savePlayerFile() {
		try {
			this.player.save(pfile);
		} catch (IOException e) {
			Messages.noFileSave("Playerdata.yml");
		}
	}
	
	public void reloadPlayerFile() {
		this.player = YamlConfiguration.loadConfiguration(this.pfile);
	}
	
	//------------------------------------------------------------------
	
	public FileConfiguration getBannedPlayers() {
		return this.ban;
	}
	
	public void saveBannedPlayers() {
		try {
			this.ban.save(bfile);
		} catch (IOException e) {
			Messages.noFileSave("bannedPlayer.yml");
		}
	}
	
	public void reloadBannedPlayer() {
		this.ban = YamlConfiguration.loadConfiguration(this.bfile);
	}
	
	//------------------------------------------------------------------
	
		public FileConfiguration getHomes() {
			return this.homes;
		}
		
		public void saveHomes() {
			try {
				this.homes.save(hfile);
			} catch (IOException e) {
				Messages.noFileSave("homes.yml");
			}
		}
		
		public void reloadHomes() {
			this.homes = YamlConfiguration.loadConfiguration(this.hfile);
		}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////

}
