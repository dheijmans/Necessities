package com.dennisheijmans.necessities;

import com.dennisheijmans.necessities.commands.Teleport;
import com.dennisheijmans.necessities.commands.Time;
import com.dennisheijmans.necessities.events.OnPlayerDeathEvent;
import com.dennisheijmans.necessities.tools.FileManager;
import com.dennisheijmans.necessities.tools.Message;
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

        //register events
        getServer().getPluginManager().registerEvents(onPlayerDeathEvent, this);


        Message.enabled(getServer().getConsoleSender());
    }

    @Override
    public void onDisable() {
        Message.disabled(getServer().getConsoleSender());
    }
}
