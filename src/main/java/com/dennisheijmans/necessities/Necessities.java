package com.dennisheijmans.necessities;

import com.dennisheijmans.necessities.commands.Teleport;
import com.dennisheijmans.necessities.commands.Time;
import com.dennisheijmans.necessities.events.Death;
import com.dennisheijmans.necessities.tools.Message;
import org.bukkit.plugin.java.JavaPlugin;

public final class Necessities extends JavaPlugin {

    public Death death = new Death();

    @Override
    public void onEnable() {
        this.getCommand("day").setExecutor(new Time());
        this.getCommand("night").setExecutor(new Time());
        this.getCommand("time").setExecutor(new Time());
        this.getCommand("back").setExecutor(new Teleport(this));
        getServer().getPluginManager().registerEvents(death, this);
        Message.enabled(getServer().getConsoleSender());
    }

    @Override
    public void onDisable() {
        Message.disabled(getServer().getConsoleSender());
    }
}
