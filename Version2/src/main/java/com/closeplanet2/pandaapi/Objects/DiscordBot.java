package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.APIS.ConsoleAPI;
import com.closeplanet2.pandaapi.PandaAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private String botName;
    private JDA jda;

    public DiscordBot(String botName, String token, Activity botActivity){
        this.botName = botName;
        var jdaBuilder = JDABuilder.createDefault(token);
        if(botActivity != null) jdaBuilder.setActivity(botActivity);
        try{
            jda = jdaBuilder.build();
            jda.awaitReady();
            ConsoleAPI.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.GREEN + "Connected Bot: " + ChatColor.BOLD + botName);
        }catch (LoginException | InterruptedException e) {
            e.printStackTrace();
            ConsoleAPI.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.RED + "Failed to connect bot: " + ChatColor.BOLD + botName);
        }
    }

    public void CloseConnection(){
        jda.shutdownNow();
    }

    public JDA ReturnBot(){
        return jda;
    }
}
