package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Bukkit;

public class ConsoleAPI {
    public static void ToggleConsoleMessages(boolean state){
        var mainClass = PandaAPI.pandaAPI;
        mainClass.canSendConsoleMessages = state;
    }

    public static void SendConsoleMessage(String message){
        var mainClass = PandaAPI.pandaAPI;
        if(!mainClass.canSendConsoleMessages) return;
        Bukkit.getConsoleSender().sendMessage("\n" +  message + "\n");
    }
}
