package com.algolrithm.toughsurvival2.item;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ToughSurvival2.MODID);

    public static final DeferredItem<Item> WATER_GLASS = ITEMS.registerItem("water_glass",
            ModDrinkableItem::new,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(0).saturationModifier(0F).build()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
