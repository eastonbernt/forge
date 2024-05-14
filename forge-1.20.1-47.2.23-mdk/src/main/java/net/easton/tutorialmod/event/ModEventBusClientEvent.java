package net.easton.tutorialmod.event;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.entity.client.ModModelLayers;
import net.easton.tutorialmod.entity.client.PhoenixChickenModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvent{

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.PHOENIXCHICKEN_LAYER, PhoenixChickenModel::createBodyLayer);
    }
}
