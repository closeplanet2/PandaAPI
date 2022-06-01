package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MovementAPI {

    public static void FreezePlayer(Player player, Location location){
        if(IsPlayerFrozen(player)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.frozenPlayers.put(player.getUniqueId(), location);
        PandaAPI.pandaAPI.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.RED + "Player has been frozen: " + ChatColor.AQUA + player.getName());
    }

    public static void UnfreezePlayer(Player player){
        if(!IsPlayerFrozen(player)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.frozenPlayers.remove(player.getUniqueId());
        PandaAPI.pandaAPI.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.GREEN + "Player has been un-frozen: " + ChatColor.AQUA + player.getName());
    }

    public static boolean IsPlayerFrozen(Player player){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.frozenPlayers.containsKey(player.getUniqueId());
    }

}
