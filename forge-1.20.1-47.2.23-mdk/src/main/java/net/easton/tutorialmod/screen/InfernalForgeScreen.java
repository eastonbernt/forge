package net.easton.tutorialmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.easton.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class InfernalForgeScreen extends AbstractContainerScreen<InfernalForgeMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/infernal_forge_gui.png");
    public static final int GUI_WIDTH = 256;
    public static final int GUI_HEIGHT = 256;



    public InfernalForgeScreen(InfernalForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int scaledWidth = (int) (imageWidth * 1.0);
        int scaledHeight = (int) (imageHeight * 1.0);
        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 9, y + 2, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}




