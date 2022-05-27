package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.PandaAPI;

public class ConsoleAPI {
    public static void ToggleConsoleMessages(boolean state){
        var mainClass = PandaAPI.pandaAPI;
        mainClass.canSendConsoleMessages = state;
    }
}
