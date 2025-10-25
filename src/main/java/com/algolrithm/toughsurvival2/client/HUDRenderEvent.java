package com.algolrithm.toughsurvival2.client;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class HUDRenderEvent extends Event implements ICancellableEvent {
    public int x;
    public int y;
    public GuiGraphics guiGraphics;

    public HUDRenderEvent(int x, int y, GuiGraphics guiGraphics) {
        this.x = x;
        this.y = y;
        this.guiGraphics = guiGraphics;
    }
    public static class HydrationBar extends HUDRenderEvent {
        public int hydration;

        public HydrationBar(int hydration, int x, int y, GuiGraphics guiGraphics) {
            super(x, y, guiGraphics);
            this.hydration = hydration;
        }
    }
}
