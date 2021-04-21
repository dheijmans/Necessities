package com.dennisheijmans.necessities.events;

import com.dennisheijmans.necessities.blockshuffle.BlockShuffle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class OnPlayerToggleSneakEvent implements Listener {

    @EventHandler
    public void OnPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if(!player.isSneaking() && BlockShuffle.inBlockShuffle) {
            Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
            player.sendMessage(block.getType().name());
        }
    }
}
