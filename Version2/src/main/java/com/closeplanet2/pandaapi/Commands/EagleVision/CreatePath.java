package com.closeplanet2.pandaapi.Commands.EagleVision;

import com.closeplanet2.pandaapi.APIS.CommandAPI;
import com.closeplanet2.pandaapi.APIS.StringAPI;
import com.closeplanet2.pandaapi.Interfaces.PandaCommand;
import com.closeplanet2.pandaapi.Mods.ChatAPI;
import com.closeplanet2.pandaapi.Mods.EagleVisionAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CreatePath implements CommandExecutor, PandaCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
        CommandAPI.PlayerRequirements(sender, args, true, "", "", this);
        return false;
    }

    @Override
    public String PlayerCommand() {
        return "createpath";
    }

    @Override
    public void CommandSuccess(Player player, String[] args) {
        if(args.length == 0) return;
        var pathName = StringAPI.Combine(args, 0);
        EagleVisionAPI.CreatePath(pathName, 200);
        ChatAPI.SendMessageToPlayer(player, ChatColor.GREEN + "You have created path: " + ChatColor.WHITE + pathName, true);
    }

    @Override
    public void CommandFailure(String reason) {

    }
}
