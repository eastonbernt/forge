package net.easton.tutorialmod.event;


import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.entity.ModEntities;
import net.easton.tutorialmod.entity.custom.PhoenixChickenEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PHOENIXCHICKEN.get(), PhoenixChickenEntity.createAttributes().build());
    }
}
