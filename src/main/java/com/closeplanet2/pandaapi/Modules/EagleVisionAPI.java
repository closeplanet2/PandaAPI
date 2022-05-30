package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Objects.CameraPath;
import com.closeplanet2.pandaapi.PandaAPI;
import com.iwebpp.crypto.TweetNaclFast;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.ObjectInputFilter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EagleVisionAPI {
    public static HashMap<String, CameraPath> UpdateCameraPaths(){
        var data = new HashMap<String, CameraPath>();
        for (File file : ConfigAPI.ReturnDirectoryFiles(DataType.CameraPath)){
            var fileConfig = ConfigAPI.LoadConfigFromFile(file);
            var camraPath = new CameraPath(fileConfig);
            data.put(camraPath.pathName, camraPath);
        }
        return data;
    }

    public static void CreatePath(String pathName, List<Location> playerPath, int pathTime){
        if(DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.put(pathName, new CameraPath(pathName, playerPath, pathTime));
    }

    public static void AddLocation(String pathName, Location location){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).AddLocation(location);
    }

    public static void AddLocation(String pathName, List<Location> location){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).AddLocation(location);
    }

    public static void RemoveLocation(String pathName, Location location){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).RemoveLocation(location);
    }

    public static void RemoveLocation(String pathName, List<Location> location){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).RemoveLocation(location);
    }

    public static boolean DoesPathExist(String pathName){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.cameraPaths.containsKey(pathName);
    }

    public static void TriggerPath(String pathName, Player player){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).TriggerPlayerPath(player);
    }
}
