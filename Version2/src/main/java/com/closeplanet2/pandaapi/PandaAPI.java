package com.closeplanet2.pandaapi;

import com.closeplanet2.pandaapi.APIS.CommandAPI;
import com.closeplanet2.pandaapi.Commands.EagleVision.*;
import com.closeplanet2.pandaapi.Configs.DataLoader;
import com.closeplanet2.pandaapi.Loop.Camera;
import com.closeplanet2.pandaapi.Objects.CameraPath;
import com.closeplanet2.pandaapi.Objects.DiscordBot;
import com.closeplanet2.pandaapi.Objects.MessageChannel;
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
    public static String Route = "PandaAPI/";
    public static String pandaPrefix = ChatColor.AQUA + "[" + ChatColor.WHITE + "PandaAPI" + ChatColor.AQUA + "] " + ChatColor.WHITE;

    public HashMap<String, MessageChannel> messageChannels = new HashMap<>();
    public HashMap<String, DiscordBot> discordBots = new HashMap<>();
    public HashMap<String, CameraPath> cameraPaths = new HashMap<>();
    public HashMap<UUID, Location> frozenPlayers = new HashMap<>();
    public HashMap<UUID, List<Player>> hideMatrix = new HashMap<>();
    public HashMap<String, Location> teleportPoints = new HashMap<>();
    public List<UUID> banSendMessages = new ArrayList<>();
    public List<UUID> banGetMessages = new ArrayList<>();
    public boolean canSendConsoleMessages;

    private int cameraLoop;

    @Override
    public void onEnable() {
        pandaAPI = this;
        canSendConsoleMessages = true;
        cameraPaths = DataLoader.ReturnCameraPaths();

        CommandAPI.RegisterCommand(new AddLocation(), this);
        CommandAPI.RegisterCommand(new CreatePath(), this);
        CommandAPI.RegisterCommand(new DeletePath(), this);
        CommandAPI.RegisterCommand(new RemoveLocation(), this);
        CommandAPI.RegisterCommand(new SetPathTime(), this);
        CommandAPI.RegisterCommand(new TriggerPath(), this);

        cameraLoop = Camera.CameraPathLoop(this);
    }
}
