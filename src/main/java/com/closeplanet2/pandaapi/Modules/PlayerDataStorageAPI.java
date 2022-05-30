package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.Objects.PlayerDataStorageObject;
import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerDataStorageAPI {
    public void SetKey(Player player, String key, Object value){
        var dataObject = ReturnPlayerStorage(player);
        dataObject.SetKey(key, value);
        dataObject.SaveData();
    }

    public boolean DoesKeyExist(Player player, String key){
        var dataObject = ReturnPlayerStorage(player);
        return dataObject.DoesKeyExist(key);
    }

    public void RemoveKey(Player player, String key){
        var dataObject = ReturnPlayerStorage(player);
        dataObject.RemoveKey(key);
        dataObject.SaveData();
    }

    public Object GetValueRaw(Player player, String key){
        var dataObject = ReturnPlayerStorage(player);
        return dataObject.GetValueRaw(key);
    }

    public PlayerDataStorageObject ReturnPlayerStorage(Player player){
        var mainClass = PandaAPI.pandaAPI;
        if(!DoesPlayerStorageExist(player)) return new PlayerDataStorageObject(player.getUniqueId(), new HashMap<>());
        else return mainClass.playerDataStorage.get(player.getUniqueId());
    }

    public Boolean DoesPlayerStorageExist(Player player){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.playerDataStorage.containsKey(player.getUniqueId());
    }

    public void DeletePlayerData(Player player){
        if(!DoesPlayerStorageExist(player)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.playerDataStorage.remove(player.getUniqueId());
    }

}
