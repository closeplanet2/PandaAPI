package com.closeplanet2.pandaapi.Modules;

import com.closeplanet2.pandaapi.APIS.ConfigAPI;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Objects.PlayerPunishments;
import com.closeplanet2.pandaapi.PandaAPI;
import me.leoko.advancedban.manager.TimeManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.entity.Player;

public class PunishmentAPI {
    public void PunishPlayer(Player player, String reason, String operator, PunishmentType type, Long end, String calculation, boolean silent){
        var punishment = new Punishment(player.getName(), player.getUniqueId().toString(), reason, operator, end == -1L ? type.getPermanent() : type, TimeManager.getTime(), end, calculation, -1);
        var playerPunishments = GetPlayerPunishmentRecord(player);
        playerPunishments.AddPunishment(punishment);
        punishment.create(silent);

        var serverConfig = ConfigAPI.ReturnServerConfig(DataType.PlayerPunishments.name(), player.getUniqueId().toString());
        playerPunishments.SaveData(serverConfig);
    }

    public static PlayerPunishments GetPlayerPunishmentRecord(Player player){
        var serverConfig = ConfigAPI.ReturnServerConfig(DataType.PlayerPunishments.name(), player.getUniqueId().toString());

        if(serverConfig.firstLoad()){
            var playerPunishment = new PlayerPunishments(player);
            playerPunishment.SaveData(serverConfig);
            return playerPunishment;
        }

        return new PlayerPunishments(serverConfig);
    }
}
