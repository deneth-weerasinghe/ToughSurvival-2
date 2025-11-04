package com.algolrithm.toughsurvival2.player;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class Hydration {
    public static final double MAX_HYDRATION = 20d;
    public static void resetHydration(Player player, double hydration) {
        player.setData(ModData.HYDRATION, sanitizeHydration(hydration));
    }
    public static void setHydration(Player player, double hydration) {
        player.setData(ModData.HYDRATION, sanitizeHydration(getHydration(player) + hydration));
    }
    public static double getHydration(Player player) {
        return player.getData(ModData.HYDRATION);
    }

    public static double sanitizeHydration(double value) {
        return Double.isNaN(value) ? 0 : Mth.clamp(value, 0, MAX_HYDRATION);
    }
}
