package br.com.introcdc.mapmeelv1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class MapListeners implements Listener {

    public static Location SPAWN = new Location(Bukkit.getWorlds().get(0), 83.0, 43.0, 177.0);

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

    @EventHandler
    public void onEmpty(PlayerBucketEmptyEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

    @EventHandler
    public void onFill(PlayerBucketFillEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (!event.getClickedBlock().getType().equals(Material.WOOD_BUTTON) && !event.getClickedBlock().getType().equals(Material.LEVER) && !event.getClickedBlock().getType().equals(Material.POWERED_RAIL)) {
                event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getY() < -64) {
            Location location = event.getTo().clone();
            location.setY(1);
            event.getPlayer().getWorld().strikeLightningEffect(location);
            MapMain.sendPlayer(event.getPlayer(), "KitPvP");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(SPAWN);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(!event.getPlayer().getName().equalsIgnoreCase("Introo"));
    }

}
