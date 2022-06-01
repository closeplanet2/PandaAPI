package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.PandaAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CommandAPI {

    public interface PlayerCommand {
        void playerSuccess(Player player, String[] args);
        void playerError(String reason);
    }

    public static void RegisterCommand(String command, CommandExecutor commandExecutor, JavaPlugin plugin){
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + commandExecutor.getClass().getName() + " has been registered!");
        plugin.getCommand(command).setExecutor(commandExecutor);
    }

    public static Player PlayerRequirements(CommandSender sender){
        return null;
    }

    public static void PlayerRequirements(CommandSender sender, String[] args, boolean op, String perm, String pass, PlayerCommand playerCommand){
        if(!(sender instanceof Player)) playerCommand.playerError(ChatColor.RED + "The sender is not a instance of player!");
        else if(op && !sender.isOp()) playerCommand.playerError(ChatColor.RED + "The sender is not OP!");
        else if(!perm.equals("")){
            if(!sender.hasPermission(perm)) playerCommand.playerError(ChatColor.RED + "The player doesnt have the correct permssions!");
        }
        else if(!pass.equals("")){
            if(!Arrays.stream(args).toList().contains(pass)){
                playerCommand.playerError(ChatColor.RED + "The args doesnt contain the specified password!");
            }
        }
        else playerCommand.playerSuccess((Player) sender, args);
    }

}
