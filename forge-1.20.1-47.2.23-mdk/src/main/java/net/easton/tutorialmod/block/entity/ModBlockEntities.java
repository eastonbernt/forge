package net.easton.tutorialmod.block.entity;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<InfernalForgeBlockEntity>> INFERNAL_FORGE_BE =
            BLOCK_ENTITIES.register("infernal_forge_be", () ->
                    BlockEntityType.Builder.of(InfernalForgeBlockEntity::new,
                            ModBlocks.INFERNAL_FORGE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
