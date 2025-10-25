package com.algolrithm.toughsurvival2.item;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.component.ItemHydration;
import com.algolrithm.toughsurvival2.component.ModComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Objects;


public class ModDrinkableItem extends Item {
    public ModDrinkableItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(clickedBlock == Blocks.DIAMOND_BLOCK) {

            if(!level.isClientSide() && context.getItemInHand().get(ModComponents.ITEM_HYDRATION) != null) {
                ToughSurvival2.LOGGER.info("==========DISPLAY DATA: " + Objects.requireNonNull(context.getItemInHand().get(ModComponents.ITEM_HYDRATION)).getHydration());
            }
        }
        return InteractionResult.SUCCESS;
    }
}
