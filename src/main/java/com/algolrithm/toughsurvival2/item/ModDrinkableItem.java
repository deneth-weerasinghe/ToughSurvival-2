package com.algolrithm.toughsurvival2.item;

import com.algolrithm.toughsurvival2.component.ItemHydration;
import com.algolrithm.toughsurvival2.component.ModComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;


public class ModDrinkableItem extends Item {
    public ModDrinkableItem(Properties properties, int hydration) {
        super(properties
                .component(DataComponents.CONSUMABLE, Consumables.defaultDrink().build())
                .component(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(hydration))
        );
    }
}
