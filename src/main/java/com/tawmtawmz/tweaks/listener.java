package com.tawmtawmz.tweaks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class listener implements Listener {
    @EventHandler
    public void onEntityDeathEvent(final EntityDeathEvent event) {
        final Entity entity = event.getEntity();
        if (util.getPlugin().getConfig().getBoolean(constants.KEY_DROP)) {
            for (String drop : util.getPlugin().getConfig().getStringList(util.dropTable(entity.getType().getName()))) {
                // serialize the string into the drop configuration
                String[] data = drop.split(",");
                String modifider = data[0];
                String item = data[1];
                if (modifider.equals(constants.SUBCOMMAND_ADD)) {
                    float rate = Float.parseFloat(data[2]);
                    float chance = new Random().nextFloat();
                    Integer quantity = Integer.parseInt(data[3]);
                    if (chance <= rate) {
                        event.getDrops().add(new ItemStack(Material.valueOf(item.toUpperCase()), quantity));
                    }
                }
                else if (modifider.equals(constants.SUBCOMMAND_REMOVE)) {
                    for (ItemStack stack : event.getDrops()) {
                        if (stack.getType().equals(Material.valueOf(item.toUpperCase()))) {
                            stack.subtract(stack.getAmount());
                        }
                    }
                }
                else if (modifider.equals(constants.SUBCOMMAND_MULTIPLY)) {
                    float rate = Float.parseFloat(data[2]);
                    for (ItemStack stack : event.getDrops()) {
                        if (stack.getType().equals(Material.valueOf(item.toUpperCase()))) {
                            stack.add((int)(stack.getAmount() * rate));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCreatureSpawnEvent(final CreatureSpawnEvent event) {
        final Entity entity = event.getEntity();
        if (util.getPlugin().getConfig().getBoolean(constants.KEY_SHULKER)) {
            if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.ENDER_PEARL)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(util.getPlugin(), new Runnable() {
                    public void run() {
                        Location location = entity.getLocation().clone().subtract(0, 1, 0);
                        Block block = location.getBlock();
                        if (block.getType().equals(Material.CHORUS_FLOWER)) {
                            block.setType(Material.AIR);
                            entity.getWorld().spawnEntity(location, EntityType.SHULKER);
                            entity.remove();
                        }
                    }
                }, constants.ENDERMITE_EVOLVE_TIME);
            }
        }
    }

    @EventHandler
    public void onPlayerBedEnterEvent(final PlayerBedEnterEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBed();
        final PlayerBedEnterEvent.BedEnterResult result = event.getBedEnterResult();

        if (util.getPlugin().getConfig().getBoolean(constants.KEY_SPAWN)) {
            if (result.equals(PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_NOW) || result.equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
                player.setBedSpawnLocation(block.getLocation());
                player.sendMessage("Spawn point set.");
            }
            if (result.equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
                Bukkit.broadcastMessage(String.format("%s is sleeping.", player.getDisplayName()));
                Bukkit.getScheduler().scheduleSyncDelayedTask(util.getPlugin(), new Runnable() {
                    public void run() {
                        player.getWorld().setTime(0);
                    }
                }, constants.PLAYER_SLEEP_TIME);
            }
        }
    }
}
