package com.closeplanet2.pandaapi.Commands.EagleVision;

import com.closeplanet2.pandaapi.APIS.CommandAPI;
import com.closeplanet2.pandaapi.APIS.IntAPI;
import com.closeplanet2.pandaapi.APIS.StringAPI;
import com.closeplanet2.pandaapi.Interfaces.PandaCommand;
import com.closeplanet2.pandaapi.Mods.ChatAPI;
import com.closeplanet2.pandaapi.Mods.EagleVisionAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPathTime implements CommandExecutor, PandaCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
        CommandAPI.PlayerRequirements(sender, args, true, "", "", this);
        return false;
    }

    @Override
    public String PlayerCommand() {
        return "setpathtime";
    }

    @Override
    public void CommandSuccess(Player player, String[] args) {
        if(args.length <= 1){
            ChatAPI.SendMessageToChannel(player, ChatColor.RED + "Invalid command: /setpathtime <time> <pathtime>", true);
            return;
        }
        if(!IntAPI.IsInteger(args[0], 10)) {
            ChatAPI.SendMessageToChannel(player, ChatColor.RED + "Invalid command: /setpathtime <time> <pathtime>", true);
            return;
        }
        var time = Integer.parseInt(args[0]);
        var pathName = StringAPI.Combine(args, 1);
        EagleVisionAPI.SetPathTime(pathName, time);
        ChatAPI.SendMessageToPlayer(player, ChatColor.GREEN + "You have changed the time to: " + ChatColor.WHITE + time, true);
    }

    @Override
    public void CommandFailure(String reason) {

    }

}
