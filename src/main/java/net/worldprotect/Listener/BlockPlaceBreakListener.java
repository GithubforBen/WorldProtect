package net.worldprotect.Listener;

import net.worldprotect.Main;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (Main.getInstace().getCheckIfBlockIsProtectet().check(event.getBlock().getLocation())){
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (Main.getInstace().getCheckIfBlockIsProtectet().check(event.getBlock().getLocation())) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
}
