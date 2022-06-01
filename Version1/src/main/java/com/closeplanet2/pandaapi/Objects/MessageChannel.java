package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.Modules.ChatAPI;
import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageChannel {
    private UUID channelUUID;
    private String channelName;
    private List<Player> inChannel;
    private boolean defaultChannel;
    private boolean receiveDefaultMessages;
    private String messagePrefix;
    private ChatColor prefixMainColor;
    private ChatColor prefixSecondaryColor;

    public MessageChannel(UUID channelUUID, String channelName, List<Player> inChannel, boolean defaultChannel, boolean receiveDefaultMessages, String messagePrefix,
                          ChatColor prefixMainColor, ChatColor prefixSecondaryColor){
        this.channelUUID = channelUUID;
        this.channelName = channelName;
        this.inChannel = inChannel;
        this.defaultChannel = defaultChannel;
        this.receiveDefaultMessages = receiveDefaultMessages;
        this.messagePrefix = messagePrefix;
        this.prefixMainColor = prefixMainColor;
        this.prefixSecondaryColor = prefixSecondaryColor;
    }

    public void AddPlayerToChannel(Player player){
        if(IsPlayerInChannel(player)) return;
        inChannel.add(player);
    }

    public boolean IsPlayerInChannel(Player player){
        return inChannel.contains(player);
    }

    public void RemovePlayerFromChannel(Player player){
        if(!IsPlayerInChannel(player)) return;
        inChannel.remove(player);
    }

    public void SendMessageToChannel(String message, boolean opOverride){
        for(Player player : inChannel){
            if(ChatAPI.CanPlayerGetMessage(player)){
                player.sendMessage(ReturnPrefix() + message);
            }
        }
    }

    private String ReturnPrefix(){
        return prefixSecondaryColor + "[" + prefixMainColor + messagePrefix + prefixSecondaryColor + "] " + ChatColor.WHITE;
    }

    public boolean IsDefaultChannel(){
        return defaultChannel;
    }

    public boolean CanGetDefaultMessages(){
        return receiveDefaultMessages;
    }
}
