package com.closeplanet2.pandaapi.Mods;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.Objects.CameraPath;
import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class EagleVisionAPI {
    public static void CreatePath(String pathName, int pathTime){
        if(DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.put(pathName, new CameraPath(pathName, pathTime));
    }

    public static void AddLocation(String pathName, Location location){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).AddLocation(location);
    }

    public static void RemoveLocation(String pathName, Location location){
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

    public static void DeletePath(String pathName){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        ConfigAPI.DeleteConfig(mainClass.cameraPaths.get(pathName));
        mainClass.cameraPaths.remove(pathName);
    }

    public static void SetPathTime(String pathName, int pathTime){
        if(!DoesPathExist(pathName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.cameraPaths.get(pathName).SetTime(pathTime);
    }
}
