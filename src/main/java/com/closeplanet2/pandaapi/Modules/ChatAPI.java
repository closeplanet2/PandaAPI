package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.Objects.MessageChannel;
import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatAPI {

    public static void TogglePlayerGetMessage(Player player, boolean canGetMessage){
        var mainClass = PandaAPI.pandaAPI;
        if(canGetMessage) mainClass.banGetMessages.remove(player.getUniqueId());
        if(!canGetMessage && !mainClass.banGetMessages.contains(player.getUniqueId())) mainClass.banGetMessages.add(player.getUniqueId());
    }

    public static void TogglePlayerSendMessage(Player player, boolean canSendMessage){
        var mainClass = PandaAPI.pandaAPI;
        if(canSendMessage) mainClass.banSendMessages.remove(player.getUniqueId());
        if(!canSendMessage && !mainClass.banSendMessages.contains(player.getUniqueId())) mainClass.banSendMessages.add(player.getUniqueId());
    }

    public static boolean CanPlayerSendMessage(Player player){
        var mainClass = PandaAPI.pandaAPI;
        return !mainClass.banSendMessages.contains(player.getUniqueId());
    }

    public static boolean CanPlayerGetMessage(Player player){
        var mainClass = PandaAPI.pandaAPI;
        return !mainClass.banGetMessages.contains(player.getUniqueId());
    }

    public static void CreateMessageChannel(String channelName){
        CreateMessageChannel(UUID.randomUUID(), channelName, new ArrayList<>(), false, true, channelName, ChatColor.AQUA, ChatColor.WHITE);
    }

    public static void CreateMessageChannel(String channelName, boolean defaultChannel, boolean receiveDefaultMessages){
        CreateMessageChannel(UUID.randomUUID(), channelName, new ArrayList<>(), defaultChannel, receiveDefaultMessages, channelName, ChatColor.AQUA, ChatColor.WHITE);
    }

    public static void CreateMessageChannel(String channelName, boolean defaultChannel, boolean receiveDefaultMessages, String messagePrefix){
        CreateMessageChannel(UUID.randomUUID(), channelName, new ArrayList<>(), defaultChannel, receiveDefaultMessages, messagePrefix, ChatColor.AQUA, ChatColor.WHITE);
    }

    public static void CreateMessageChannel(String channelName, boolean defaultChannel, boolean receiveDefaultMessages, String messagePrefix, ChatColor prefixMainColor){
        CreateMessageChannel(UUID.randomUUID(), channelName, new ArrayList<>(), defaultChannel, receiveDefaultMessages, messagePrefix, prefixMainColor, ChatColor.WHITE);
    }

    public static void CreateMessageChannel(String channelName, boolean defaultChannel, boolean receiveDefaultMessages, String messagePrefix, ChatColor prefixMainColor, ChatColor prefixSecondaryColor){
        CreateMessageChannel(UUID.randomUUID(), channelName, new ArrayList<>(), defaultChannel, receiveDefaultMessages, messagePrefix, prefixMainColor, prefixSecondaryColor);
    }

    private static void CreateMessageChannel(UUID channelUUID, String channelName, List<Player> inChannel, boolean defaultChannel, boolean receiveDefaultMessages, String messagePrefix,
                                             ChatColor prefixMainColor, ChatColor prefixSecondaryColor){
        if(DoesMessageChannelExist(channelName)) return;
        var mainClass = PandaAPI.pandaAPI;
        var messageChannel = new MessageChannel(channelUUID, channelName, inChannel, defaultChannel, receiveDefaultMessages, messagePrefix, prefixMainColor, prefixSecondaryColor);
        mainClass.messageChannels.put(channelName, messageChannel);
        mainClass.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.GREEN + "Message Channel has been created: " + ChatColor.BOLD + channelName);
    }

    public static boolean DoesMessageChannelExist(String channelName){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.messageChannels.containsKey(channelName);
    }

    public static MessageChannel ReturnMessageChannel(String channelName){
        if(!DoesMessageChannelExist(channelName)) return null;
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.messageChannels.get(channelName);
    }

    public static MessageChannel ReturnMessageChannel(Player player){
        var mainClass = PandaAPI.pandaAPI;
        for(var channelName: mainClass.messageChannels.keySet()){
            var messageChannel = mainClass.messageChannels.get(channelName);
            if(messageChannel.IsPlayerInChannel(player)) return messageChannel;
        }
        return null;
    }

    public static void DeleteMessageChannel(String channelName){
        if(!DoesMessageChannelExist(channelName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.messageChannels.remove(channelName);
        mainClass.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.RED + "Message Channel has been deleted: " + ChatColor.BOLD + channelName);
    }

    public static void AddPlayerToChannel(Player player, String messageChannelName){
        if(!DoesMessageChannelExist(messageChannelName)) return;
        var mainClass = PandaAPI.pandaAPI;
        for(var channelName: mainClass.messageChannels.keySet()){
            var messageChannel = mainClass.messageChannels.get(channelName);
            messageChannel.RemovePlayerFromChannel(player);
        }
        var messageChannel = mainClass.messageChannels.get(messageChannelName);
        messageChannel.AddPlayerToChannel(player);
        mainClass.SendConsoleMessage(PandaAPI.pandaPrefix + ChatColor.GREEN + "Player has been added to message Channel:" + ChatColor.BOLD +
                "\nPlayer: " + player.getName() + "\nMessage Channel: " + messageChannelName );
    }

    public static void SendMessageToChannel(Player player, String message, boolean opOverride){
        var messageChannel = ReturnMessageChannel(player);
        if(messageChannel == null) return;
        messageChannel.SendMessageToChannel(message, opOverride);
    }

    public static void SendMessageToChannel(String channelName, String message, boolean opOverride ){
        var messageChannel = ReturnMessageChannel(channelName);
        if(messageChannel == null) return;
        messageChannel.SendMessageToChannel(message, opOverride);
    }

    public static void SendMessageToPlayer(Player player, String message, boolean opOverride){
        if(!CanPlayerGetMessage(player) && !opOverride) return;
        player.sendMessage(message);
    }

}
