package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAPI {
    private static void CreateDirectory(File path){
        if(!path.exists())
        {
            path.mkdirs();
            ConsoleAPI.SendConsoleMessage(ChatColor.GREEN + "Path created: " + ChatColor.AQUA + path.getName());
        }
    }

    public static boolean DoesFileExist(String dirName, String fileName){
        File dir = new File(dirName);
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

    public static File CreateFile(String dirName, String fileName){
        CreateDirectory(new File(dirName));
        var file = new File(dirName, fileName);
        try {
            file.createNewFile();
            ConsoleAPI.SendConsoleMessage(ChatColor.GREEN + "File created: " + ChatColor.AQUA + fileName);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> filesNames(String dataType){
        var data = new ArrayList<String>();
        var dirName = PandaAPI.Route + dataType;
        var dir = new File(dirName);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                data.add(child.getName());
            }
        }
        return data;
    }
}
