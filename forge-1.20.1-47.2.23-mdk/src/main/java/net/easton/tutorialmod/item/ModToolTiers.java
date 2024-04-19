package net.easton.tutorialmod.item;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier PHOENIX = TierSortingRegistry.registerTier(
            new ForgeTier( 6, 1300, 6f, 5f, 25,
                    ModTags.Blocks.NEEDS_PHOENIX_FORGED_STEEL_TOOL, () -> Ingredient.of(ModItems.PHOENIX_FORGED_STEEL.get())),
            new ResourceLocation(TutorialMod.MOD_ID, "phoenix_forged_steel"), List.of(Tiers.NETHERITE), List.of());

}
