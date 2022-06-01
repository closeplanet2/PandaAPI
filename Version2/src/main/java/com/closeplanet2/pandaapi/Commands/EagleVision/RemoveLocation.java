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

public class RemoveLocation implements CommandExecutor, PandaCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
        CommandAPI.PlayerRequirements(sender, args, true, "", "", this);
        return false;
    }

    @Override
    public String PlayerCommand() {
        return "removelocation";
    }

    @Override
    public void CommandSuccess(Player player, String[] args) {
        if(args.length == 0) return;
        var pathName = StringAPI.Combine(args, 0);
        EagleVisionAPI.RemoveLocation(pathName, player.getLocation());
        ChatAPI.SendMessageToPlayer(player, ChatColor.RED + "Removed Location!", true);
    }

    @Override
    public void CommandFailure(String reason) {

    }

}
