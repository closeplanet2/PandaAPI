package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.Modules.PlayerDataStorageAPI;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataStorageObject {
    public UUID playerUUID;
    private HashMap<String, Object> keyMap;

    public PlayerDataStorageObject(UUID playerUUID, HashMap<String, Object> keyMap){
        this.playerUUID = playerUUID;
        this.keyMap = keyMap;
    }

    public PlayerDataStorageObject(FileConfiguration fileConfiguration){
        LoadData(fileConfiguration);
    }

    public void SetKey(String key, Object value){
        keyMap.put(key, value);
    }

    public boolean DoesKeyExist(String key){
        return keyMap.containsKey(key);
    }

    public void RemoveKey(String key){
        keyMap.remove(key);
    }

    public Object GetValueRaw(String key){
        return keyMap.get(key);
    }

    public void SaveData(){

    }

    private void LoadData(FileConfiguration fileConfiguration){

    }
}
