package com.closeplanet2.pandaapi.APIS;

public class StringAPI {
    public static String Combine(String[] args, int startpoint){
        StringBuilder data = new StringBuilder();
        for(int i = startpoint; i < args.length; i++){
            data.append(args[i]);
            if(i < args.length - 1){
                data.append(" ");
            }
        }
        return data.toString();
    }
}
