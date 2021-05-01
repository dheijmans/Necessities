package com.dennisheijmans.necessities.blockshuffle;

import com.dennisheijmans.necessities.tools.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BlockShuffleGame {

    FileManager fm = FileManager.getInstance();

    private HashMap<Player, ItemStack[]> inventories = new HashMap<Player, ItemStack[]>();
    private final ArrayList<String> blocksAllowed = new ArrayList<>();

    public void invSave() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            inventories.put(player, player.getInventory().getContents());
        }
    }

    public void startGame() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            giveRandomBlock();
        }
    }

    public void stopGame() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().setContents(inventories.get(player));
        }
        inventories.clear();
    }

    private void giveRandomBlock() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (String key : fm.getBlockshuffle().getConfigurationSection("blocks-allowed").getKeys(false)) {
                blocksAllowed.add(fm.getBlockshuffle().getString(key));
            }
            player.getInventory().setItemInMainHand(new ItemStack(getRandomBlock()));
        }
    }

    private Material getRandomBlock() {
        blocksAllowed.clear();

        Material assignedBlock = null;
        Random rand = new Random();

//		Generate random number and get it from list
        while(assignedBlock == null) {
            int randomNumber = rand.nextInt(blocksAllowed.size());
            assignedBlock = Material.getMaterial(blocksAllowed.get(randomNumber));
        }
        return assignedBlock;
    }
}
