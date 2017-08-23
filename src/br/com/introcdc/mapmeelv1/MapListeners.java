package br.com.introcdc.mapmeelv1;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MapListeners implements Listener {

    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

    @EventHandler
    public void onEmpty(final PlayerBucketEmptyEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

    @EventHandler
    public void onFill(final PlayerBucketFillEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (!event.getClickedBlock().getType().equals(Material.WOOD_BUTTON) && !event.getClickedBlock().getType().equals(Material.LEVER) && !event.getClickedBlock().getType().equals(Material.POWERED_RAIL)) {
                event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
            }
        }
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

}
