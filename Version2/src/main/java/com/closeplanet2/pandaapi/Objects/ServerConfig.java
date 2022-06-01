package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.APIS.FileAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    private File file;
    private boolean firstLoad;
    public FileConfiguration fileConfiguration;

    public ServerConfig(String dirPath, String fileName){
        firstLoad = !FileAPI.DoesFileExist(dirPath, fileName);
        file = FileAPI.CreateFile(dirPath, fileName);
        if(file != null) fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void DeleteConfig(){
        file.delete();
    }

    public boolean IsFirstLoad(){
        return firstLoad;
    }

    public void SaveConfig(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
