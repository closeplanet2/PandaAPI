package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishAPI {

    public static void HidePlayer(Player player){
        HidePlayer(player, new ArrayList<>());
    }

    public static void HidePlayer(Player player, List<Player> visible){
        var mainClass = PandaAPI.pandaAPI;
        if(!mainClass.hideMatrix.containsKey(player.getUniqueId())){
            mainClass.hideMatrix.put(player.getUniqueId(), new ArrayList<>());
        }

        var matrix = mainClass.hideMatrix.get(player.getUniqueId());
        for(var p : visible){
            if(!matrix.contains(p)){
                matrix.add(p);
            }
        }

        for(var p : Bukkit.getOnlinePlayers()){
            if(!matrix.contains(p)){
                p.hidePlayer(mainClass, player);
            }
        }

        mainClass.hideMatrix.put(player.getUniqueId(), matrix);
    }

    public static void ShowToPlayer(Player player, Player other){
        var mainClass = PandaAPI.pandaAPI;
        var matrix = mainClass.hideMatrix.get(player.getUniqueId());
        other.showPlayer(mainClass, player);
        matrix.remove(other);
        mainClass.hideMatrix.put(player.getUniqueId(), matrix);
    }

    public static void ShowToPlayer(Player player, List<Player> players){
        var mainClass = PandaAPI.pandaAPI;
        var matrix = mainClass.hideMatrix.get(player.getUniqueId());

        for(var p : players){
            p.showPlayer(mainClass, player);
            matrix.remove(p);
        }

        mainClass.hideMatrix.put(player.getUniqueId(), matrix);
    }

    public static void ShowToAllPlayers(Player player){
        var mainClass = PandaAPI.pandaAPI;
        for(var p : Bukkit.getOnlinePlayers()){
            p.showPlayer(mainClass, player);
        }
        mainClass.hideMatrix.remove(player.getUniqueId());
    }

    public static boolean IsPlayerInvisible(Player player){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.hideMatrix.containsKey(player.getUniqueId());
    }

    public static boolean IsPlayerInvisibleTOPlayer(Player player, Player other){
        if(!IsPlayerInvisible(player)) return false;
        var mainClass = PandaAPI.pandaAPI;
        var matrix = mainClass.hideMatrix.get(player.getUniqueId());
        return matrix.contains(other);
    }

    public static void LoginPlayer(Player player){
        var mainClass = PandaAPI.pandaAPI;
        if(mainClass.hideMatrix.containsKey(player.getUniqueId())){
            var matrix = mainClass.hideMatrix.get(player.getUniqueId());
            for(var p : Bukkit.getOnlinePlayers()){
                if(!matrix.contains(p)){
                    p.hidePlayer(mainClass, player);
                }
            }
        }

        for(var key : mainClass.hideMatrix.keySet()){
            if(Bukkit.getPlayer(key) != null){
                if(!mainClass.hideMatrix.get(key).contains(player)){
                    player.hidePlayer(mainClass, Bukkit.getPlayer(key));
                }
            }
        }
    }
}
