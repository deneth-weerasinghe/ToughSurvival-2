package com.algolrithm.toughsurvival2.player;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber(modid = ToughSurvival2.MODID)
public class ModData {
    private static final DeferredRegister<AttachmentType<?>> MOD_ATTACHMENTS = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, ToughSurvival2.MODID);

    public static final Supplier<AttachmentType<Double>> HYDRATION = MOD_ATTACHMENTS.register(
            "hydration", () -> AttachmentType.builder(() -> HydrationHelper.MAX_HYDRATION)
                    .serialize(Codec.DOUBLE.fieldOf("hydration"))
                    .sync((holder, to) -> holder == to, ByteBufCodecs.DOUBLE)
                    .build()
    );
    public static final Supplier<AttachmentType<Integer>> HYDRATION_EXHAUSTION = MOD_ATTACHMENTS.register(
            "hydration_exhaustion", () -> AttachmentType.builder(() -> HydrationHelper.MAX_EXHAUSTION)
                    .serialize(Codec.INT.fieldOf("hydration_exhaustion"))
                    .sync((holder, to) -> holder == to, ByteBufCodecs.INT)
                    .build()
    );

    public static void register(IEventBus modBus) {
        MOD_ATTACHMENTS.register(modBus);
    }
}
