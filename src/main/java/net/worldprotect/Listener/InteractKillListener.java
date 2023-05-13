package net.worldprotect.Listener;

import net.worldprotect.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class InteractKillListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (event.hasBlock()) {
            if (event.getClickedBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE)) {
                return;
            }
        }
        if (Main.getInstace().getCheckIfBlockIsProtectet().check(player.getLocation())){
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }

        try {
            if (Main.getInstace().getCheckIfBlockIsProtectet().check(event.getClickedBlock().getLocation())){
                event.setCancelled(true);
            }else {
                event.setCancelled(false);
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof ArmorStand)) {
            return;
        }
        if (Main.getInstace().getCheckIfBlockIsProtectet().check(Objects.requireNonNull(event.getEntity()).getLocation())){
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }
}
