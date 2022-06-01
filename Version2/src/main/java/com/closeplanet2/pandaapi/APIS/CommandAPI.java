package com.closeplanet2.pandaapi.APIS;

import com.closeplanet2.pandaapi.Interfaces.PandaCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CommandAPI {

    public static void RegisterCommand(PandaCommand pandaCommand, JavaPlugin javaPlugin){
        var playerCommand = pandaCommand.PlayerCommand();
        var commandExecutor = (CommandExecutor) pandaCommand;
        javaPlugin.getCommand(playerCommand).setExecutor(commandExecutor);
        ConsoleAPI.SendConsoleMessage(ChatColor.GREEN + "Command Registered: " + ChatColor.AQUA + "/" + playerCommand);
    }

    public static void PlayerRequirements(CommandSender sender, String[] args, boolean op, String perm, String pass, PandaCommand playerCommand){
        if(!(sender instanceof Player)) SendFail(playerCommand,ChatColor.RED + "The sender is not a instance of player!");
        else if(op && !sender.isOp()) SendFail(playerCommand,ChatColor.RED + "The sender is not OP!");
        else if(!perm.equals("")){
            if(!sender.hasPermission(perm)) SendFail(playerCommand,ChatColor.RED + "The player doesnt have the correct permssions!");
        }
        else if(!pass.equals("")){
            if(!Arrays.stream(args).toList().contains(pass)){
                SendFail(playerCommand,ChatColor.RED + "The args doesnt contain the specified password!");
            }
        }
        else playerCommand.CommandSuccess((Player) sender, args);
    }

    private static void SendFail(PandaCommand pandaCommand, String reason){
        ConsoleAPI.SendConsoleMessage(reason);
        pandaCommand.CommandFailure(reason);
    }

}
