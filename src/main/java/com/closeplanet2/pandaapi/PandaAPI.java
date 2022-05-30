package com.closeplanet2.pandaapi;

import com.closeplanet2.pandaapi.Modules.DiscordAPI;
import com.closeplanet2.pandaapi.Modules.EagleVisionAPI;
import com.closeplanet2.pandaapi.Objects.*;
import com.iwebpp.crypto.TweetNaclFast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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
    public HashMap<UUID, Location> frozenPlayers = new HashMap<>();
    public HashMap<UUID, List<Player>> hideMatrix = new HashMap<>();
    public HashMap<String, Location> teleportPoints = new HashMap<>();
    public HashMap<String, CameraPath> cameraPaths = new HashMap<>();
    public HashMap<String, String> chatFormats = new HashMap<>();
    public HashMap<UUID, PlayerDataStorageObject> playerDataStorage = new HashMap<>();
    public List<UUID> banSendMessages = new ArrayList<>();
    public List<UUID> banGetMessages = new ArrayList<>();
    public boolean canSendConsoleMessages;

    @Override
    public void onEnable() {
        pandaAPI = this;
        canSendConsoleMessages = true;
        FrozenPlayerLoop();
        CameraPathLoop();
        cameraPaths = EagleVisionAPI.UpdateCameraPaths();
    }

    public void SendConsoleMessage(String message){
        if(!canSendConsoleMessages) return;
        Bukkit.getConsoleSender().sendMessage("\n" + message);
    }

    private void FrozenPlayerLoop(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(var uuid : frozenPlayers.keySet()){
                    if(Bukkit.getPlayer(uuid) != null){
                        var player = Bukkit.getPlayer(uuid);
                        if(player.getLocation() != frozenPlayers.get(uuid)) player.teleport(frozenPlayers.get(uuid));
                    }
                }
            }
        }, 0L, 20L);
    }

    private int CameraPathLoop(){
        return Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                if(!cameraPaths.isEmpty()){
                    for(var pathName : cameraPaths.keySet()){
                        var path = cameraPaths.get(pathName);
                        path.CheckAllPlayerStates();
                    }
                }
            }
        }, 0, 10);
    }

}
