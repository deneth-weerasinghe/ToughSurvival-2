package com.algolrithm.toughsurvival2.event;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.component.ItemHydration;
import com.algolrithm.toughsurvival2.component.ModComponents;
import com.algolrithm.toughsurvival2.player.Hydration;
import com.algolrithm.toughsurvival2.player.ModData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber(modid = ToughSurvival2.MODID)
public class DataEventHandler {
    @SubscribeEvent
    public static void checkHydration(AttackEntityEvent event){
        Player player = event.getEntity();
        if (!player.level().isClientSide() && !event.isCanceled()) {
            Entity target = event.getTarget();
           if (target instanceof Pig) {
               Hydration.resetHydration(player, 6d);
           } else if (target instanceof Cow) {
               Hydration.incrementHydration(player, -1d);
           }
        }
        if(player.hasData(ModData.HYDRATION)) {
            ToughSurvival2.SimpleLogger("HYDRATION", Hydration.getHydration(player));
        }
    }
    @SubscribeEvent
    public static void addHydrationVanilla(ModifyDefaultComponentsEvent event) {
        event.modify(Items.APPLE, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(4)));
        event.modify(Items.GOLDEN_APPLE, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(6)));
        event.modify(Items.ENCHANTED_GOLDEN_APPLE, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(15)));
        event.modify(Items.CHORUS_FRUIT, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(1)));
        event.modify(Items.MELON_SLICE, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(5)));
        event.modify(Items.SWEET_BERRIES, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(3)));
        event.modify(Items.GLOW_BERRIES, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(3)));
        event.modify(Items.MUSHROOM_STEW, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(10)));
        event.modify(Items.RABBIT_STEW, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(10)));
        event.modify(Items.SUSPICIOUS_STEW, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(10)));
        event.modify(Items.POTION, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(11)));
        event.modify(Items.MILK_BUCKET, builder -> builder.set(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(11)));
    }
}
