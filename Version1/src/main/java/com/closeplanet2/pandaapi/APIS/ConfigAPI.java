package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Objects.ServerConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;
import java.util.UUID;

public class ConfigAPI {

    private static String ReturnDataPath(String dataType){
        return "PandaAPI/" + dataType;
    }

    public interface SaveableConfig {
        String ReturnUUID();
        String ReturnDataType();
        void SaveData(ServerConfig serverConfig, String startPath);
        void LoadData(ServerConfig serverConfig, String startPath);
    }

    public static void SaveFile(SaveableConfig saveableConfig, String startPath){
        var uuid = saveableConfig.ReturnUUID();
        var dataType = saveableConfig.ReturnDataType();
        var serverConfig = ReturnServerConfig(dataType, uuid);
        saveableConfig.SaveData(serverConfig, startPath);
    }

    public static void LoadFile(SaveableConfig saveableConfig, String startPath){
        var uuid = saveableConfig.ReturnUUID();
        var dataType = saveableConfig.ReturnDataType();
        var serverConfig = ReturnServerConfig(dataType, uuid);
        saveableConfig.LoadData(serverConfig, startPath);
    }

    private static ServerConfig ReturnServerConfig(String dataType, String dataName){
        if(!dataName.contains(".yml")){
            dataName += ".yml";
        }
        FileAPI.CreateDirectory(new File(ReturnDataPath(dataType)));
        return new ServerConfig(ReturnDataPath(dataType), dataName, dataType);
    }
}


