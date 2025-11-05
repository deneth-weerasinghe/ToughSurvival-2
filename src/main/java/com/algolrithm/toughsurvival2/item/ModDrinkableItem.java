package com.algolrithm.toughsurvival2.item;

import com.algolrithm.toughsurvival2.component.ItemHydration;
import com.algolrithm.toughsurvival2.component.ModComponents;
import com.algolrithm.toughsurvival2.player.Hydration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.Level;


public class ModDrinkableItem extends Item {
    public ModDrinkableItem(Properties properties, int hydration) {
        super(properties
                .component(ModComponents.ITEM_HYDRATION.get(), new ItemHydration(hydration))
                .component(DataComponents.CONSUMABLE, Consumables.defaultDrink().build())
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack itemStack = super.finishUsingItem(stack, level, livingEntity);
        if(!itemStack.has(ModComponents.ITEM_HYDRATION)) return itemStack;
        if(!level.isClientSide() && livingEntity instanceof Player) {
            Hydration.incrementHydration((Player) livingEntity, stack.get(ModComponents.ITEM_HYDRATION).getHydration());
        }
        return itemStack;
    }
}
