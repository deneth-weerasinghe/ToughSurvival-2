package com.algolrithm.toughsurvival2.event;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.player.ModData;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void checkHydration(AttackEntityEvent event){
        Player player = event.getEntity();
        if (!event.isCanceled()) {
           if (event.getTarget() instanceof Pig) {
               player.setData(ModData.HYDRATION, 16d);
           } else if (event.getTarget() instanceof Cow) {
               player.setData(ModData.HYDRATION, player.getData(ModData.HYDRATION) - 1d);
           }
           ToughSurvival2.LOGGER.info(String.format("==============HYDRATION: %.1f==============", player.getData(ModData.HYDRATION)));
        }
    }
}
