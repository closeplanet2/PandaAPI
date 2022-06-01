package com.closeplanet2.pandaapi.Interfaces;

import org.bukkit.entity.Player;

public interface PandaCommand {String PlayerCommand();
    void CommandSuccess(Player player, String[] args);
    void CommandFailure(String reason);
}
