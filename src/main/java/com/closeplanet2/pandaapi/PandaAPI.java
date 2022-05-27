package com.closeplanet2.pandaapi;

import com.closeplanet2.pandaapi.Objects.MessageChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PandaAPI extends JavaPlugin {

    public static PandaAPI pandaAPI;
    public static String pandaPrefix = ChatColor.AQUA + "[" + ChatColor.WHITE + "PandaAPI" + ChatColor.AQUA + "] " + ChatColor.WHITE;

    public HashMap<String, MessageChannel> messageChannels = new HashMap<>();
    public List<UUID> banSendMessages = new ArrayList<>();
    public List<UUID> banGetMessages = new ArrayList<>();
    public boolean canSendConsoleMessages;

    public void onEnable(){
        pandaAPI = this;
        canSendConsoleMessages = true;
    }

    public void SendConsoleMessage(String message){
        if(!canSendConsoleMessages) return;
        Bukkit.getConsoleSender().sendMessage("\n" + message);
    }

}
