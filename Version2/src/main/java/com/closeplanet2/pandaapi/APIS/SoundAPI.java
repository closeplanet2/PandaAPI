package com.closeplanet2.pandaapi.APIS;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundAPI {
    public static void PlaySound(Player player, Sound sound){
        PlaySound(player, sound, 0, 0, 0);
    }

    public static void PlaySound(Player player, Sound sound, int xOffset, int yOffset, int zOffset){
        PlaySound(player.getLocation(), sound, xOffset, yOffset, zOffset);
    }

    public static void PlaySound(Location location, Sound sound){
        PlaySound(location, sound, 0, 0, 0);
    }

    public static void PlaySound(Location location, Sound sound, int xOffset, int yOffset, int zOffset){
        var newLocation = new Location(location.getWorld(), location.getX() + xOffset, location.getY() + yOffset, location.getZ() + zOffset);
        if(location.getWorld() == null){
            return;
        }
        location.getWorld().playSound(newLocation, sound, 3.0F, 0.5F);
    }
}
