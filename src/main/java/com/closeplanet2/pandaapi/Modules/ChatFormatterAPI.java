package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.PandaAPI;

import java.util.HashMap;

public class ChatFormatterAPI {

    public static void AddChatFormat(String formatName, String data){
        if(DoesFormatExist(formatName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.chatFormats.put(formatName, data);
    }

    public static boolean DoesFormatExist(String formatName){
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.chatFormats.containsKey(formatName);
    }

    public static void RemoveFormat(String formatName){
        if(!DoesFormatExist(formatName)) return;
        var mainClass = PandaAPI.pandaAPI;
        mainClass.chatFormats.remove(formatName);
    }

    public static String ReturnFormat(String formatName){
        if(!DoesFormatExist(formatName)) return null;
        var mainClass = PandaAPI.pandaAPI;
        return mainClass.chatFormats.get(formatName);
    }

    public static String ReturnFormat(String formatName, HashMap<String, String> formatKeys){
        if(!DoesFormatExist(formatName)) return null;
        var mainClass = PandaAPI.pandaAPI;
        var template = mainClass.chatFormats.get(formatName);
        for(var key: formatKeys.keySet()){
            var value = formatKeys.get(key);
            template = template.replace(key, value);
        }
        return template;
    }

}
