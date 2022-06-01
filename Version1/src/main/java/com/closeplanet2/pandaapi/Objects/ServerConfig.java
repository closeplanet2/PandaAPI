package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.APIS.FileAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    public File file;
    public FileConfiguration fileConfiguration;
    public String dataType;
    public boolean firstLoad;

    public ServerConfig(String dirPath, String fileName, String dataType){
        file = new File(dirPath, fileName);
        firstLoad = !FileAPI.DoesFileExist(dirPath, fileName);
        FileAPI.CreateFile(file);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        this.dataType = dataType;
        SaveConfig();
    }

    public void SaveConfig(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean DeleteConfig(){
        return file.delete();
    }


}
