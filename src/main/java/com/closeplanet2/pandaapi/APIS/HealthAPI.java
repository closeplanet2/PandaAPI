package com.closeplanet2.pandaapi.APIS;

import org.bukkit.entity.Player;

public class HealthAPI {
    public static void SetPlayersHealth(Player player, double currentLevel, double maxiumLevel){
        var amount = Math.floor(20 * (currentLevel / maxiumLevel));
        player.setHealth(amount);
    }
}
