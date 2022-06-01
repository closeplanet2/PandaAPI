package com.closeplanet2.pandaapi.Configs;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.APIS.FileAPI;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Objects.CameraPath;

import java.util.HashMap;

public class DataLoader {
    public static HashMap<String, CameraPath> ReturnCameraPaths(){
        var data = new HashMap<String, CameraPath>();
        var files = FileAPI.filesNames(DataType.CameraPath.name());
        for(var file : files){
            var fileName = file.replace(".yml", "");
            var cameraPath = new CameraPath(fileName);
            ConfigAPI.LoadConfig(cameraPath);
            data.put(fileName, cameraPath);
        }
        return data;
    }
}
