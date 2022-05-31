package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.APIS.FileAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    private File file;
    private FileConfiguration fileConfiguration;
    private boolean firstLoad;

    public ServerConfig(String dirPath, String fileName){
        file = new File(dirPath, fileName);
        firstLoad = !FileAPI.DoesFileExist(dirPath, fileName);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        SaveConfig();
    }

    public FileConfiguration fileConfiguration(){
        return fileConfiguration;
    }

    public boolean firstLoad(){
        return firstLoad;
    }

    public File file(){
        return file;
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
