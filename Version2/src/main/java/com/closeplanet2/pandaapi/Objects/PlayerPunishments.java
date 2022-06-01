package com.closeplanet2.pandaapi.Objects;

import com.closeplanet2.pandaapi.APIS.DateAPI;
import com.closeplanet2.pandaapi.Enums.DataType;
import com.closeplanet2.pandaapi.Interfaces.SaveableConfig;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PlayerPunishments implements SaveableConfig {

    private UUID playerUUID;
    private HashMap<Date, Punishment> playerPunishments = new HashMap<>();

    public PlayerPunishments(Player player){
        playerUUID = player.getUniqueId();
    }

    public void AddPunishment(Punishment punishment){
        playerPunishments.put(new Date(), punishment);
    }

    public HashMap<Date, Punishment> ReturnPlayerPunishments(){
        return playerPunishments;
    }

    @Override
    public String ReturnUUID() {
        return playerUUID.toString();
    }

    @Override
    public String ReturnDataType() {
        return DataType.PlayerPunishments.name();
    }

    @Override
    public void SaveData(ServerConfig serverConfig) {
        playerUUID = UUID.fromString(serverConfig.fileConfiguration.getString("PlayerUUID"));
        if(serverConfig.fileConfiguration.isConfigurationSection("Punishments")){
            for(var dateKey : serverConfig.fileConfiguration.getConfigurationSection("Punishments").getKeys(false)){
                var path = "Punishments." + dateKey + ".";
                var date = DateAPI.ConvertToDate(dateKey);

                var name = serverConfig.fileConfiguration.getString(path + "Name");
                var uuid = serverConfig.fileConfiguration.getString(path + "UUID");
                var operator = serverConfig.fileConfiguration.getString(path + "Operator");
                var calculation = serverConfig.fileConfiguration.getString(path + "Calculation");
                var start = serverConfig.fileConfiguration.getLong(path + "Start");
                var end = serverConfig.fileConfiguration.getLong(path + "End");
                var type = PunishmentType.valueOf(serverConfig.fileConfiguration.getString(path + "Type"));
                var reason = serverConfig.fileConfiguration.getString(path + "Reason");
                var id = serverConfig.fileConfiguration.getInt(path + "ID");

                var punishment = new Punishment(name, uuid, reason, operator, type, start, end, calculation, id);
                playerPunishments.put(date, punishment);
            }
        }
    }

    @Override
    public void LoadData(ServerConfig serverConfig) {
        serverConfig.fileConfiguration.set("PlayerUUID", playerUUID.toString());
        for(var date: playerPunishments.keySet()){
            var dateKey = DateAPI.ConvertToString(date);
            var punishement = playerPunishments.get(date);
            var path = "Punishments." + dateKey + ".";

            serverConfig.fileConfiguration.set(path + "Name", punishement.getName());
            serverConfig.fileConfiguration.set(path + "UUID", punishement.getUuid());
            serverConfig.fileConfiguration.set(path + "Operator", punishement.getOperator());
            serverConfig.fileConfiguration.set(path + "Calculation", punishement.getCalculation());
            serverConfig.fileConfiguration.set(path + "Start", punishement.getStart());
            serverConfig.fileConfiguration.set(path + "End", punishement.getEnd());
            serverConfig.fileConfiguration.set(path + "Type", punishement.getType().toString());
            serverConfig.fileConfiguration.set(path + "Reason", punishement.getReason());
            serverConfig.fileConfiguration.set(path + "ID", punishement.getId());
        }
        serverConfig.SaveConfig();
    }
}
