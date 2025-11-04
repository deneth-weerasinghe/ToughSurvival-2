package com.algolrithm.toughsurvival2.event;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.player.Hydration;
import com.algolrithm.toughsurvival2.player.ModData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber(modid = ToughSurvival2.MODID)
public class DataEventHandler {
    @SubscribeEvent
    public static void checkHydration(AttackEntityEvent event){
        Player player = event.getEntity();
        if (!player.level().isClientSide() && !event.isCanceled()) {
            Entity target = event.getTarget();
           if (target instanceof Pig) {
               Hydration.resetHydration(player, 16d);
           } else if (target instanceof Cow) {
               Hydration.setHydration(player, -1d);
           }
        }
        if(player.hasData(ModData.HYDRATION)) {
            ToughSurvival2.LOGGER.info(String.format("==============HYDRATION: %.1f==============", Hydration.getHydration(player)));
        }
    }
}
