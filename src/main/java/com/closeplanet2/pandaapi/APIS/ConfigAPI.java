package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Objects.ServerConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigAPI {

    private static String ReturnDataPath(String dataType){
        return "PandaAPI/" + dataType;
    }

    public static ServerConfig ReturnServerConfig(String dataType, String dataName){
        if(!dataName.contains(".yml")){
            dataName += ".yml";
        }
        FileAPI.CreateDirectory(new File(ReturnDataPath(dataType)));
        return new ServerConfig(ReturnDataPath(dataType), dataName);
    }

    public static File[] ReturnDirectoryFiles(DataType fileType){
        var dirPath = "PandaML/" + fileType.toString() + "/";
        var file = new File(dirPath);
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file.listFiles();
    }

    public static FileConfiguration LoadConfigFromFile(File file){
        return YamlConfiguration.loadConfiguration(file);
    }

}
