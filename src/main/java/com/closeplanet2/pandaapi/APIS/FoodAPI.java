package com.closeplanet2.pandaapi.APIS;

import org.bukkit.entity.Player;

public class FoodAPI {
    public static void SetHungeramount(Player player, double currentLevel, double maxiumLevel){
        var amount = (int) Math.floor(20 * (currentLevel / maxiumLevel));
        player.setFoodLevel(amount);
    }
}
