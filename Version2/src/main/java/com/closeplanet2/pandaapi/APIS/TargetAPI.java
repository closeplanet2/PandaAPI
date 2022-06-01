package com.closeplanet2.pandaapi.APIS;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TargetAPI {
    public static Player GetTargetPlayer(Player player) {
        return getTarget(player, player.getWorld().getPlayers());
    }

    public static Entity GetTargetEntity(Entity entity) {
        return getTarget(entity, entity.getWorld().getEntities());
    }

    private static <T extends Entity> T getTarget(Entity entity, Iterable<T> entities) {
        if (entity == null) return null;
        T target = null;
        var threshold = 1;
        for (var other : entities) {
            var n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
            if (entity.getLocation().getDirection().normalize().crossProduct(n) .lengthSquared() < threshold && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0) {
                if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation()))
                    target = other;
            }
        }
        return target;
    }
}
