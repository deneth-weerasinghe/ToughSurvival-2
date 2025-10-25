package com.algolrithm.toughsurvival2.event;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.player.ModPlayerAttributes;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber
public class PlayerAttributes {
    @SubscribeEvent
    public static void onAddHydrationAttributes(EntityAttributeModificationEvent event) {
        ToughSurvival2.LOGGER.info("REGISTERING HYDRATION TO PLAYER ENTITY");
        if(!event.has(EntityType.PLAYER, ModPlayerAttributes.HYDRATION)) {
            event.add(EntityType.PLAYER, ModPlayerAttributes.HYDRATION);
        }
    }

}
