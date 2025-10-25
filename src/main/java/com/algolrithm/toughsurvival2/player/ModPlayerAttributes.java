package com.algolrithm.toughsurvival2.player;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPlayerAttributes {
    private static final int MAX_HYDRATION = 20;

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(
            BuiltInRegistries.ATTRIBUTE, ToughSurvival2.MODID);

    public static final Holder<Attribute> HYDRATION = ATTRIBUTES.register(
            "player_hydration", () -> new RangedAttribute(
                    "attributes." + ToughSurvival2.MODID + ".player_hydration",
                    MAX_HYDRATION,
                    0,
                    MAX_HYDRATION)
    );

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
