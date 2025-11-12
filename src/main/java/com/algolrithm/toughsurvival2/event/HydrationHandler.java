package com.algolrithm.toughsurvival2.event;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.player.HydrationHelper;
import com.algolrithm.toughsurvival2.player.ModData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = ToughSurvival2.MODID)
public class HydrationHandler {
    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if(!player.level().isClientSide()) {
            if(player.hasData(ModData.HYDRATION_EXHAUSTION)) {
                int exhaustion = player.getData(ModData.HYDRATION_EXHAUSTION);
                ToughSurvival2.SimpleLogger("EXHAUSTION", exhaustion);
                player.setData(ModData.HYDRATION_EXHAUSTION, Mth.clamp(exhaustion - 1, 0, HydrationHelper.MAX_EXHAUSTION));
            }
        }
    }

    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if(!player.level().isClientSide()) {
            if(player.getData(ModData.HYDRATION_EXHAUSTION) == 0) {
                player.setData(ModData.HYDRATION_EXHAUSTION, HydrationHelper.MAX_EXHAUSTION);
            }
        }
    }
}
