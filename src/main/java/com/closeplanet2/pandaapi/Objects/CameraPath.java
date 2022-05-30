package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.iwebpp.crypto.TweetNaclFast;
import eu.crushedpixel.camerastudio.CameraStudio;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CameraPath {
    public String pathName;
    private List<Location> playerPath;
    private HashMap<UUID, CameraPlayerState> playerStates;
    private int pathTime;

    public CameraPath(String pathName, List<Location> playerPath, int pathTime){
        this.pathName = pathName;
        this.playerPath = playerPath;
        playerStates = new HashMap<>();
        this.pathTime = pathTime;
        SaveCameraPath();
    }

    public CameraPath(FileConfiguration fileConfiguration){
        LoadData(fileConfiguration);
    }

    public void AddLocation(Location location){
        playerPath.add(location);
        SaveCameraPath();
    }

    public void AddLocation(List<Location> locations){
        playerPath.addAll(locations);
        SaveCameraPath();
    }

    public void RemoveLocation(Location location){
        playerPath.remove(location);
        SaveCameraPath();
    }

    public void RemoveLocation(List<Location> locations){
        playerPath.removeAll(locations);
        SaveCameraPath();
    }

    public void TriggerPlayerPath(Player player){
        playerStates.put(player.getUniqueId(), new CameraPlayerState(player));
        CameraStudio.travel(player, playerPath, pathTime, "", "");
        SaveCameraPath();
    }

    public void CheckAllPlayerStates(){
        for(var uuid : playerStates.keySet()){
            if(!CameraStudio.isTravelling(uuid)){
                StopPath(uuid);
            }
        }
        SaveCameraPath();
    }

    private void StopPath(UUID player){
        CameraStudio.stop(player);
        if(!playerStates.containsKey(player)) return;
        if(Bukkit.getPlayer(player) != null) playerStates.get(player).ResetPlayer(Bukkit.getPlayer(player));
        playerStates.remove(player);
        SaveCameraPath();
    }

    public void SaveCameraPath(){
        var serverConfig = ConfigAPI.ReturnServerConfig(DataType.CameraPath.name(), pathName);
        serverConfig.fileConfiguration().set("CameraPath", null);

        serverConfig.fileConfiguration().set("PathName", pathName);
        var count = 0;
        for(var location : playerPath){
            var gameLocation = new GameLocation(location);
            serverConfig.fileConfiguration().set("CameraPath." + count, gameLocation.ReturnData());
        }
        serverConfig.fileConfiguration().set("PathTime", pathTime);

        serverConfig.SaveConfig();
    }

    private void LoadData(FileConfiguration fileConfiguration){
        pathName = fileConfiguration.getString("PathName");
        pathTime = fileConfiguration.getInt("pathTime");
        playerPath = new ArrayList<>();
        playerStates = new HashMap<>();

        if(fileConfiguration.isConfigurationSection("CameraPath")){
            for(var key : fileConfiguration.getConfigurationSection("CameraPath").getKeys(false)){
                var path = "CameraPath." + key;
                var gamelocation = new GameLocation(fileConfiguration.getString(path));
                playerPath.add(gamelocation.Location());
            }
        }
    }
}
