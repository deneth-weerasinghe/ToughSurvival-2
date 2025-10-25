package com.algolrithm.toughsurvival2.component;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ToughSurvival2.MODID);

    public static final Supplier<DataComponentType<ItemHydration>> ITEM_HYDRATION = DATA_COMPONENT_TYPES.registerComponentType(
            "item_hydration",
            builder -> builder
                    .persistent(ItemHydration.CODEC)
                    .networkSynchronized(ItemHydration.STREAM_CODEC)
    );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
