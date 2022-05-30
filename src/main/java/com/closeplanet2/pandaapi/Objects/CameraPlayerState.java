package com.closeplanet2.pandaapi.Objects;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CameraPlayerState {
    private GameMode lastGamemode;
    private Location lastLocation;

    public CameraPlayerState(Player player){
        lastGamemode = player.getGameMode();
        lastLocation = player.getLocation();
    }

    public void ResetPlayer(Player player){
        player.setGameMode(lastGamemode);
        player.teleport(lastLocation);
    }
}
