package com.algolrithm.toughsurvival2.client;

import com.algolrithm.toughsurvival2.ToughSurvival2;
import com.algolrithm.toughsurvival2.event.client.HUDRenderEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.GuiLayer;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

public class HUDHandler {

    static final ResourceLocation SPRITES = ResourceLocation.fromNamespaceAndPath(ToughSurvival2.MODID, "textures/icons/sprite_sheet.png");
    static final int SPRITE_SHEET_HEIGHT = 81;
    static final int SPRITE_SHEET_WIDTH = 80;

    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.FOOD_LEVEL, HydrationOverlay.ID, new HydrationOverlay());
    }

    public static abstract class Overlay implements GuiLayer {
        public abstract void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int top, int right, int left);
        @Override
        public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player == null || !shouldRenderOverlay(mc, mc.player, guiGraphics)) return;

            int top = guiGraphics.guiHeight();
            int right = guiGraphics.guiWidth();
            int left = guiGraphics.guiWidth() - 60;

            render(mc, mc.player, guiGraphics, top, right, left);
        }

        public boolean shouldRenderOverlay(Minecraft mc, Player player, GuiGraphics guiGraphics) {
            return !mc.options.hideGui && mc.gameMode != null && mc.gameMode.canHurtPlayer();
        }
    }

    public static class HydrationOverlay extends Overlay {
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(ToughSurvival2.MODID, "hydration_overlay");

        @Override
        public void render(Minecraft mc, Player player, GuiGraphics guiGraphics, int top, int right, int left) {
            float testValue = 5.0F;
            int x = right / 2 + 10;
            int y = top - 49;

            HUDRenderEvent.HydrationBar hydrationBarEvent = new HUDRenderEvent.HydrationBar(testValue, x, y, guiGraphics);
            if(player.getVehicle() instanceof LivingEntity) {
                hydrationBarEvent.setCanceled(true);
            }
            if(!hydrationBarEvent.isCanceled()) NeoForge.EVENT_BUS.post(hydrationBarEvent);
            if(hydrationBarEvent.isCanceled()) return;

            // Moves the air bar up, so it won't be overdrawn by the hydration bar
            mc.gui.rightHeight += 10;

            drawHydrationBar(hydrationBarEvent, player);
        }
    }

    private static void drawHydrationBar(HUDRenderEvent.HydrationBar event, Player player) {
        drawHydrationBar(player, event.guiGraphics, event.x, event.y, event.hydration);
    }
    public static void drawHydrationBar(Player player, GuiGraphics guiGraphics, int right, int top, float hydration) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITES, right, top, 0, 0, 81, 9, SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
    }
}
