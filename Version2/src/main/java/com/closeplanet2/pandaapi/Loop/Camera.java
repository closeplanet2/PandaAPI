package com.closeplanet2.pandaapi.Loop;

import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Bukkit;

public class Camera {
    public static int CameraPathLoop(PandaAPI pandaAPI){
        return Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pandaAPI, new Runnable() {
            public void run() {
                if(!pandaAPI.cameraPaths.isEmpty()){
                    for(var pathName : pandaAPI.cameraPaths.keySet()){
                        var path = pandaAPI.cameraPaths.get(pathName);
                        path.CheckAllPlayerStates();
                    }
                }
            }
        }, 0, 10);
    }
}
