package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Objects.ServerConfig;

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

}
