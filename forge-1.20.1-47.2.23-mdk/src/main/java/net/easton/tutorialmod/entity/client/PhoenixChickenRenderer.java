package net.easton.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.entity.custom.PhoenixChickenEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PhoenixChickenRenderer extends MobRenderer<PhoenixChickenEntity, PhoenixChickenModel<PhoenixChickenEntity>> {
    public PhoenixChickenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PhoenixChickenModel<>(pContext.bakeLayer(ModModelLayers.PHOENIXCHICKEN_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixChickenEntity pEntity) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/phoenixchicken.png");
    }

    @Override
    public void render(PhoenixChickenEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
