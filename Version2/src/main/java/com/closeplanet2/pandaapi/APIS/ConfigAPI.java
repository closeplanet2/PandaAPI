package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Interfaces.SaveableConfig;
import com.closeplanet2.pandaapi.Objects.ServerConfig;
import com.closeplanet2.pandaapi.PandaAPI;

import java.io.File;

public class ConfigAPI {
    public static void SaveConfig(SaveableConfig saveableConfig){
        var serverConfig = ReturnServerConfig(saveableConfig.ReturnUUID(), saveableConfig.ReturnDataType());
        saveableConfig.SaveData(serverConfig);
    }

    public static void LoadConfig(SaveableConfig saveableConfig){
        var serverConfig = ReturnServerConfig(saveableConfig.ReturnUUID(), saveableConfig.ReturnDataType());
        saveableConfig.LoadData(serverConfig);
    }

    public static void DeleteConfig(SaveableConfig saveableConfig){
        var serverConfig = ReturnServerConfig(saveableConfig.ReturnUUID(), saveableConfig.ReturnDataType());
        serverConfig.DeleteConfig();
    }

    private static ServerConfig ReturnServerConfig(String dataType, String dataName){
        if(!dataName.contains(".yml")){
            dataName += ".yml";
        }
        return new ServerConfig(PandaAPI.Route + dataType, dataName);
    }
}
