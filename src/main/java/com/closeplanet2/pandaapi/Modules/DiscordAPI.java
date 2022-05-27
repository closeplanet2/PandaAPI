package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.Objects.DiscordBot;
import com.closeplanet2.pandaapi.PandaAPI;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.ChatColor;

public class DiscordAPI {
    public static void CreateBot(String botName, String token){
        CreateBot(botName, token, null);
    }

    public static void CreateBot(String botName, String token, Activity botActivity){
        if(DoesDiscordBotExist(botName)) return;
        var mainClass = PandaAPI.pandaAPI;
        var discordBot = new DiscordBot(botName, token, botActivity);
        mainClass.discordBots.put(botName, discordBot);
    }

    public static boolean DoesDiscordBotExist(String botName){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.discordBots.containsKey(botName);
    }

    public static void DeleteDiscordBot(String botName){
        if(!DoesDiscordBotExist(botName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.discordBots.get(botName).CloseConnection();
        mainClass.discordBots.remove(botName);
        PandaAPI.pandaAPI.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.RED + "Connection has been closed: " + ChatColor.BOLD + botName);
    }
}
