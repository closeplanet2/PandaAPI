package com.closeplanet2.pandaapi.APIS;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldAPI   {
    public static boolean DoesWorldExist(String worldName){
        return Bukkit.getWorld(worldName) != null;
    }

    public static void UnloadWorld(String worldName){
        var world = Bukkit.getWorld(worldName);
        UnloadWorld(world);
    }

    public static void UnloadWorld(World world){
        if(world == null) return;
        Bukkit.getServer().unloadWorld(world, true);
    }

    public static void CopyWorld(World oldWorld, World newWorld){
        CopyWorld(oldWorld.getWorldFolder(), newWorld.getWorldFolder());
    }

    private static void CopyWorld(File oldWorld, File newWorld){
        try{
            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
            if(!ignore.contains(oldWorld.getName())) {
                if(oldWorld.isDirectory()) {
                    if(!newWorld.exists())
                        newWorld.mkdirs();
                    String files[] = oldWorld.list();
                    for (String file : files) {
                        File srcFile = new File(oldWorld, file);
                        File destFile = new File(newWorld, file);
                        CopyWorld(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(oldWorld);
                    OutputStream out = new FileOutputStream(newWorld);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        }catch(IOException e){

        }
    }

    public static void DeleteWorld(String worldName){
        var world = Bukkit.getWorld(worldName);
        DeleteWorld(world);
    }

    public static void DeleteWorld(World world){
        if(world == null) return;
        UnloadWorld(world);
        var deleteFolder = world.getWorldFolder();
        DeleteWorld(deleteFolder);
    }

    private static void DeleteWorld(File path){
        if(!path.exists()) return;
        var files = path.listFiles();
        for(int i=0; i<files.length; i++) {
            if(files[i].isDirectory()) {
                DeleteWorld(files[i]);
            } else {
                files[i].delete();
            }
        }
    }
}
