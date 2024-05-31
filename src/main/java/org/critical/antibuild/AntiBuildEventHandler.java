package org.critical.antibuild;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class AntiBuildEventHandler implements Listener {
    private final AntiBuild plugin;

    public AntiBuildEventHandler(AntiBuild plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onHangingBreak(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Player) {
            Player player = (Player) event.getRemover();
            if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
                event.setCancelled(true);
                player.sendMessage(plugin.getLockedWorldMessage());
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onHangingPlace(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getDropItemsMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("antibuild.bypass") && plugin.isLockedWorld(player.getWorld().getName())) {
            event.setCancelled(true);
            player.sendMessage(plugin.getLockedWorldMessage());
        }
    }
}
