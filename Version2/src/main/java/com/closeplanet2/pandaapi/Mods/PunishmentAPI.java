package com.closeplanet2.pandaapi.Mods;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.APIS.DateAPI;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Objects.PlayerPunishments;
import com.closeplanet2.pandaapi.PandaAPI;
import me.leoko.advancedban.manager.TimeManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.entity.Player;

import java.io.ObjectInputFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
public class PunishmentAPI {
    public void PunishPlayer(Player player, String reason, String operator, PunishmentType type, Long end, String calculation, boolean silent){
        var punishment = new Punishment(player.getName(), player.getUniqueId().toString(), reason, operator, end == -1L ? type.getPermanent() : type, TimeManager.getTime(), end, calculation, -1);
        var playerPunishments = GetPlayerPunishmentRecord(player);
        playerPunishments.AddPunishment(punishment);
        punishment.create(silent);
        ConfigAPI.SaveConfig(playerPunishments);
    }

    public static PlayerPunishments GetPlayerPunishmentRecord(Player player){
        var playerPunishments = new PlayerPunishments(player);
        ConfigAPI.LoadConfig(playerPunishments);
        ConfigAPI.SaveConfig(playerPunishments);
        return playerPunishments;
    }
}
