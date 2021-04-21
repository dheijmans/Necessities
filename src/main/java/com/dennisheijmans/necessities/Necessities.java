package com.dennisheijmans.necessities;

import com.dennisheijmans.necessities.commands.*;
import com.dennisheijmans.necessities.events.*;
import com.dennisheijmans.necessities.tools.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Necessities extends JavaPlugin {

    FileManager fm = FileManager.getInstance();
    private static Necessities plugin;

    public OnPlayerDeathEvent onPlayerDeathEvent = new OnPlayerDeathEvent();

    public static Necessities getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {

        //Files
        if(!this.getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        fm.setup(this);
        plugin = this;

        //register commands
        this.getCommand("day").setExecutor(new Time());
        this.getCommand("night").setExecutor(new Time());
        this.getCommand("time").setExecutor(new Time());
        this.getCommand("back").setExecutor(new Teleport(this));
        this.getCommand("gm").setExecutor(new Gamemodes());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("sun").setExecutor(new Weather());
        this.getCommand("rain").setExecutor(new Weather());
        this.getCommand("storm").setExecutor(new Weather());
        this.getCommand("vanish").setExecutor(new Vanish());
        this.getCommand("me").setExecutor(new Reload());
        this.getCommand("kick").setExecutor(new Kick());
        this.getCommand("ban").setExecutor(new Ban());
        this.getCommand("dif").setExecutor(new Difficulty());
        this.getCommand("sethome").setExecutor(new Sethome());
        this.getCommand("delhome").setExecutor(new Delhome());
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("setspawn").setExecutor(new Spawn());
        this.getCommand("motd").setExecutor(new MOTD());

        //register events
        getServer().getPluginManager().registerEvents(onPlayerDeathEvent, this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerToggleSneakEvent(), this);


        Message.enabled(getServer().getConsoleSender());
    }

    @Override
    public void onDisable() {
        Message.disabled(getServer().getConsoleSender());
    }
}
