package com.algolrithm.toughsurvival2.item;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.component.ModComponents;
import net.minecraft.core.BlockPos;
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
        BlockPos data = (context.getItemInHand().get(ModComponents.COORDINATES));

        if(clickedBlock == Blocks.GOLD_BLOCK) {
            if(!level.isClientSide()) {
                context.getItemInHand().set(ModComponents.COORDINATES, context.getClickedPos());
                ToughSurvival2.LOGGER.info("==========DATA ADDED==========");
            }
        }
        if(clickedBlock == Blocks.DIAMOND_BLOCK) {
            if(!level.isClientSide() && data != null) {
                ToughSurvival2.LOGGER.info("==========DISPLAY DATA:" + data.toShortString());
            }
        }
        return InteractionResult.SUCCESS;
    }
}
