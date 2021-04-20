package com.dennisheijmans.necessities.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Message {

    public static final String NECESSITIES = Color.colorize("&7[&9Necessities&7] ");

    public static void enabled(CommandSender sender) {
        sender.sendMessage(NECESSITIES + Color.colorize("&aNecessities is enabled!"));
    }

    public static void disabled(CommandSender sender) {
        sender.sendMessage(NECESSITIES + Color.colorize("&aNecessities is disabled!"));
    }

    public static void noPlayer(CommandSender sender) {
        sender.sendMessage(NECESSITIES + Color.colorize("&cOnly a player can use this command!"));
    }

    public static void noPerm(CommandSender sender) {
        sender.sendMessage(NECESSITIES + Color.colorize("&cYou don't have permission to use this command!"));
    }
    public static void noTarget(String target, CommandSender sender) {
        sender.sendMessage(Message.NECESSITIES + Color.colorize("&4" + target + "&c is not on the server!"));
    }

    public static void invalidArguments(CommandSender sender) {
        sender.sendMessage(NECESSITIES + Color.colorize("&cThese arguments are invalid!"));
    }

    public static void hasNo(CommandSender player, String item) {
        player.sendMessage(NECESSITIES + Color.colorize("&d" + player.getName() + " &chas no &b" + item));
    }

    public static void setTime(CommandSender player, String time) {
        player.sendMessage(NECESSITIES + Color.colorize("&fTime is set to &b" + time));
    }

    public static void teleport(CommandSender player, String location) {
        player.sendMessage(NECESSITIES + Color.colorize("&d" + player.getName() + " &fteleported to &b" + location));
    }

    public static void noFileSave(String filename) {
        Bukkit.getServer().getLogger().severe(NECESSITIES + Color.colorize("&cCan't save&4 " + filename + "&c!"));
    }
}
