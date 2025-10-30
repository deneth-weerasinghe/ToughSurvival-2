package com.algolrithm.toughsurvival2.player;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.mojang.serialization.Codec;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber
public class ModData {
    private static final DeferredRegister<AttachmentType<?>> MOD_ATTACHMENTS = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, ToughSurvival2.MODID);

    public static final Supplier<AttachmentType<Double>> HYDRATION = MOD_ATTACHMENTS.register(
            "hydration", () -> AttachmentType.builder(() -> 20d).serialize(Codec.DOUBLE.fieldOf("hydration")).build()
    );

    public static void register(IEventBus modBus) {
        MOD_ATTACHMENTS.register(modBus);
    }

    @SubscribeEvent
    private static void setModAttachments(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(!player.hasData(HYDRATION)) {
            event.getEntity().getData(HYDRATION);
            ToughSurvival2.LOGGER.info("ATTACHED HYDRATION DATA!!!");
        } else {
            ToughSurvival2.LOGGER.info("NO NEED TO REGISTER DATA");
        }
    }
}
