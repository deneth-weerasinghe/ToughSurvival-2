package com.algolrithm.toughsurvival2.item;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.component.ItemHydration;
import com.algolrithm.toughsurvival2.component.ModComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ToughSurvival2.MODID);

    public static final DeferredItem<Item> WATER_GLASS = ITEMS.register("water_glass",
            registryName -> new ModDrinkableItem(
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, registryName))
                            .food(new FoodProperties.Builder().nutrition(0).saturationModifier(0F).build())
                            .component(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(11))
                )
            );
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
