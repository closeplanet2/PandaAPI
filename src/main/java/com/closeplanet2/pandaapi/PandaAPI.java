package com.closeplanet2.pandaapi;

import org.bukkit.plugin.java.JavaPlugin;

public class PandaAPI extends JavaPlugin {

    public static PandaAPI pandaAPI;
    public boolean isDiscordAPIInstalled = false;

    public void onEnable(){
        pandaAPI = this;
        isDiscordAPIInstalled = getServer().getPluginManager().getPlugin("JDASpigot") != null;
    }


}
