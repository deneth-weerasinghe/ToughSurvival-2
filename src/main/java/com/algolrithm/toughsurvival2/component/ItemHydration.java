package com.algolrithm.toughsurvival2.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public class ItemHydration {
    public static final Codec<ItemHydration> CODEC = RecordCodecBuilder.create(
            instance -> instance
                    .group(Codec.INT.optionalFieldOf("item_hydration", 0).forGetter(ItemHydration::getHydration))
                    .apply(instance, ItemHydration::new)
    );
    public static final StreamCodec<ByteBuf, ItemHydration> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ItemHydration::getHydration,
            ItemHydration::new
    );

    private int hydration;

    public ItemHydration(int value) {
        this.hydration = value;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof ItemHydration a && this.hydration == a.hydration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.hydration);
    }
}
