package com.closeplanet2.pandaapi.CustomEventHandler;

import com.closeplanet2.pandaapi.Modules.ChatAPI;
import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatHandler {
    public static void HandleAsyncPlayerChatEvent(AsyncPlayerChatEvent event, boolean cancelEvent, boolean opOverride){
        event.setCancelled(cancelEvent);
        var player = event.getPlayer();
        var message = event.getMessage();

        if(ChatAPI.CanPlayerSendMessage(player) && !opOverride) return;
        if(ChatAPI.ReturnMessageChannel(player) == null) return;

        var mainClass = PandaAPI.pandaAPI;
        var playerMessageChannel = ChatAPI.ReturnMessageChannel(player);

        for(var channelName: mainClass.messageChannels.keySet()){
            var storedMessageChannel = mainClass.messageChannels.get(channelName);
            if(storedMessageChannel == playerMessageChannel){
                storedMessageChannel.SendMessageToChannel(message, opOverride);
            }else if(playerMessageChannel.IsDefaultChannel() && storedMessageChannel.CanGetDefaultMessages()){
                storedMessageChannel.SendMessageToChannel(message, opOverride);
            }
        }
    }
}
