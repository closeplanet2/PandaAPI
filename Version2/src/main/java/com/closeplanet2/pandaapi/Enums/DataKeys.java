package com.closeplanet2.pandaapi.Enums;

import com.closeplanet2.pandaapi.Objects.CameraPlayerState;
import me.leoko.advancedban.utils.Punishment;
import org.bukkit.Location;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataKeys {
    public static class CameraPathKeys{
        public static String pathName = "pathName";
        public static String playerPath = "playerPath";
        public static String pathTime = "pathTime";
    }
    public static class PlayerPunishmentKeys{
        public static String playerUUID = "playerUUID";
        public static String playerPunishments = "playerPunishments";
    }
}
