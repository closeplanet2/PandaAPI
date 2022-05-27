package com.closeplanet2.pandaapi;

import com.closeplanet2.pandaapi.Modules.DiscordAPI;
import com.closeplanet2.pandaapi.Objects.DiscordBot;
import com.closeplanet2.pandaapi.Objects.MessageChannel;
import com.closeplanet2.pandaapi.Objects.PlayerPunishments;
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
    public HashMap<String, DiscordBot> discordBots = new HashMap<>();
    public List<UUID> banSendMessages = new ArrayList<>();
    public List<UUID> banGetMessages = new ArrayList<>();
    public boolean canSendConsoleMessages;

    @Override
    public void onEnable() {
        pandaAPI = this;
        canSendConsoleMessages = true;
        DiscordAPI.CreateBot("Dreamfire", "OTc0Mzk0NzUyOTM2MDcxMjc5.Gu4HVJ.KgQlIIbf3CX1JyYFe9Ph1IEA2RHJEkVlbZ94z0");
    }

    public void SendConsoleMessage(String message){
        if(!canSendConsoleMessages) return;
        Bukkit.getConsoleSender().sendMessage("\n" + message);
    }

}
