package com.closeplanet2.pandaapi.Mods;

import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportAPI {
    public static void AddTeleportPoint(String name, Location location){
        var mainClass = PandaAPI.pandaAPI;
        if(DoesTeleportPointExist(name)) return;
        mainClass.teleportPoints.put(name, location);
    }

    public static void RemoveTeleportPoint(String name){
        var mainClass = PandaAPI.pandaAPI;
        if(!DoesTeleportPointExist(name)) return;
        mainClass.teleportPoints.remove(name);
    }

    public static void TeleortToLocation(String name, Player player){
        var mainClass = PandaAPI.pandaAPI;
        if(!DoesTeleportPointExist(name)) return;
        player.teleport(mainClass.teleportPoints.get(name));
    }

    public static boolean DoesTeleportPointExist(String name){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.teleportPoints.containsKey(name);
    }
}
