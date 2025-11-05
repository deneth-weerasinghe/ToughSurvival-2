package com.algolrithm.toughsurvival2.component;

import com.algolrithm.toughsurvival2.player.Hydration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.level.Level;

import javax.annotation.concurrent.Immutable;

@Immutable
public record ItemHydration(int hydration) implements ConsumableListener {
    public static final Codec<ItemHydration> CODEC = RecordCodecBuilder.create(
            instance -> instance
                    .group(Codec.INT.optionalFieldOf("item_hydration", 0).forGetter(ItemHydration::hydration))
                    .apply(instance, ItemHydration::new)
    );
    public static final StreamCodec<ByteBuf, ItemHydration> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ItemHydration::hydration,
            ItemHydration::new
    );

    @Override
    public void onConsume(Level level, LivingEntity entity, ItemStack stack, Consumable consumable) {
        if(entity instanceof Player player && !level.isClientSide()) {
            Hydration.incrementHydration(player, stack.get(ModComponents.ITEM_HYDRATION).hydration());
        }
    }
}
