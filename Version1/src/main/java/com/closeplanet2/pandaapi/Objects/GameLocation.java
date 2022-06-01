package com.closeplanet2.pandaapi.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class GameLocation {
    private Location location;

    public GameLocation(Location location){
        this.location = location;
    }

    public GameLocation(String location){
        var data = location.split("#");
        World world = Bukkit.getWorld(data[0]);
        double x = Double.parseDouble(data[1]);
        double y = Double.parseDouble(data[2]);
        double z = Double.parseDouble(data[3]);
        float yaw = Float.parseFloat(data[4]);
        float pitch = Float.parseFloat(data[5]);
        this.location = new Location(world, x, y, z, yaw, pitch);
    }

    public String ReturnData(){
        return location.getWorld().getName() + "#" + location.getX() + "#" + location.getY() + "#" + location.getZ() + "#" + location.getYaw() + "#" + location.getPitch();
    }

    public Location Location(){
        return location;
    }
}
