package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Enums.DataType;

import java.io.File;
import java.io.IOException;

public class FileAPI {
    public static void CreateDirectory(File path){
        if(!path.exists())
        {
            path.mkdirs();
        }
    }

    public static boolean DoesFileExist(String dirNAme, String fileName){
        File dir = new File(dirNAme);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if(child.getName().equals(fileName)){
                    return true;
                }
            }
        }
        return false;
    }

    public static File[] ReturnDirectoryFiles(DataType fileType){
        var dirPath = "PandaAPI/" + fileType.toString() + "/";
        var file = new File(dirPath);
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file.listFiles();
    }

    public static void CreateFile(File file){
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
