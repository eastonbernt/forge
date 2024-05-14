package net.easton.tutorialmod.entity;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.entity.custom.PhoenixChickenEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<PhoenixChickenEntity>> PHOENIXCHICKEN =
            ENTITY_TYPES.register("phoenixchicken", () -> EntityType.Builder.of(PhoenixChickenEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("phoenixchicken"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
