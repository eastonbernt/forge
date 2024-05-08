package net.easton.tutorialmod.item;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab",() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HEART_OF_GREED.get()))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CURSED_DIRT_BLOCK.get());
                        output.accept(ModBlocks.MOLETEN_LANTERN.get());
                        output.accept(ModBlocks.SAND_BRICK.get());
                        output.accept(ModBlocks.SAND_BRICK_FIXTURE.get());
                        output.accept(ModItems.PHOENIX_FEATHER.get());
                        output.accept(ModItems.PHOENIX_GEM.get());
                        output.accept(ModItems.NETHERITE_PLATE.get());
                        output.accept(ModItems.CRUSHED_PHOENIX_POWDER.get());
                        output.accept(ModItems.PHOENIX_POWDER_BALL.get());
                        output.accept(ModItems.ALLOY_CASING.get());
                        output.accept(ModItems.HEART_OF_GREED.get());
                        output.accept(ModItems.GILDED_MIDAS_CROWN.get());
                        output.accept(ModItems.GILDED_MIDAS_CHESTPLATE.get());
                        output.accept(ModItems.GILDED_MIDAS_LEGGINGS.get());
                        output.accept(ModItems.GILDED_MIDAS_BOOTS.get());
                        output.accept(ModItems.PHOENIX_FORGED_STEEL.get());
                        output.accept(ModItems.PHOENIX_FORGED_SWORD.get());
                        output.accept(ModBlocks.INFERNAL_FORGE.get());
                        output.accept(ModItems.PHOENIX_FORGED_HELMET.get());
                        output.accept(ModItems.PHOENIX_FORGED_CHESTPLATE.get());
                        output.accept(ModItems.PHOENIX_FORGED_LEGGINGS.get());
                        output.accept(ModItems.PHOENIX_FORGED_BOOTS.get());
                        output.accept(ModBlocks.NEUTRALITE_ORE.get());
                        output.accept(ModItems.NEUTRALITE_STONE.get());
                        output.accept(ModItems.NEUTRALITE_FRAGMENT.get());
                        output.accept(ModItems.RA_SPEAR.get());









                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
