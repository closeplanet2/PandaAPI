package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.Enums.DataKeys;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Interfaces.SaveableConfig;
import eu.crushedpixel.camerastudio.CameraStudio;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CameraPath implements SaveableConfig {
    private String pathName;
    private List<GameLocation> playerPath;
    private HashMap<UUID, CameraPlayerState> playerStates;
    private int pathTime;

    public CameraPath(String pathName){
        this.pathName = pathName;
    }

    public CameraPath(String pathName, int pathTime){
        this.pathName = pathName;
        playerStates = new HashMap<>();
        this.pathTime = pathTime;
    }

    public void SetTime(int pathTime){
        this.pathTime = pathTime;
    }

    public void AddLocation(Location location){
        playerPath.add(new GameLocation(location));
    }

    public void RemoveLocation(Location location){
        var index = 0;
        for(var i = 0; i < playerPath.size(); i++){
            if(playerPath.get(i).Location() == location) index = i;
        }
        playerPath.remove(index);
    }

    public void TriggerPlayerPath(Player player){
        playerStates.put(player.getUniqueId(), new CameraPlayerState(player));
        var tarvelList = new ArrayList<Location>();
        for(var gameLocation : playerPath){
            tarvelList.add(gameLocation.Location());
        }
        CameraStudio.travel(player, tarvelList, pathTime, "", "");
    }

    public void CheckAllPlayerStates(){
        for(var uuid : playerStates.keySet()){
            if(!CameraStudio.isTravelling(uuid)){
                var player = Bukkit.getPlayer(uuid);
                if(player != null) StopPath(player);
                else{
                    playerStates.remove(uuid);
                    CameraStudio.stop(uuid);
                }
            }
        }
    }

    private void StopPath(Player player){
        if(!playerStates.containsKey(player.getUniqueId())) return;
        CameraStudio.stop(player.getUniqueId());
        playerStates.get(player.getUniqueId()).ResetPlayer(player);
        playerStates.remove(player.getUniqueId());
    }

    @Override
    public String ReturnUUID() {
        return pathName;
    }

    @Override
    public String ReturnDataType() {
        return DataType.CameraPath.name();
    }

    @Override
    public void SaveData(ServerConfig serverConfig) {
        serverConfig.fileConfiguration.set(DataKeys.CameraPathKeys.playerPath, null);
        serverConfig.fileConfiguration.set(DataKeys.CameraPathKeys.pathName, pathName);
        serverConfig.fileConfiguration.set(DataKeys.CameraPathKeys.pathTime, pathTime);
        for(var i = 0; i < playerPath.size(); i++){
            serverConfig.fileConfiguration.set(DataKeys.CameraPathKeys.playerPath + "." + i, playerPath.get(i).ReturnData());
        }
        serverConfig.SaveConfig();
    }

    @Override
    public void LoadData(ServerConfig serverConfig) {
        pathName = serverConfig.fileConfiguration.getString(DataKeys.CameraPathKeys.pathName);
        pathTime = serverConfig.fileConfiguration.getInt(DataKeys.CameraPathKeys.pathTime);
        if(serverConfig.fileConfiguration.isConfigurationSection(DataKeys.CameraPathKeys.playerPath)){
            for(String key : serverConfig.fileConfiguration.getConfigurationSection(DataKeys.CameraPathKeys.playerPath).getKeys(false)){
                var path = DataKeys.CameraPathKeys.playerPath + "." + key;
                var gameLocation = new GameLocation(serverConfig.fileConfiguration.getString(path));
                playerPath.add(gameLocation);
            }
        }
    }
}
