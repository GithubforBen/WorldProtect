package net.worldprotect.Listener;

import net.worldprotect.Main;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplodeListener implements Listener {
    @EventHandler
    public void onExplodeEvent(EntityExplodeEvent event) {
        for (Block block : event.blockList()) {
            if (Main.getInstace().getCheckIfBlockIsProtectet().check(block.getLocation())) {
                event.setCancelled(true);
            }
        }
    }
}
