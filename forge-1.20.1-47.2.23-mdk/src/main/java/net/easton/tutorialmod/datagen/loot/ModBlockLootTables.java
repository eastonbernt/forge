package net.easton.tutorialmod.datagen.loot;

import net.easton.tutorialmod.block.ModBlocks;
import net.easton.tutorialmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.INFERNAL_FORGE.get());
        this.dropSelf(ModBlocks.CURSED_DIRT_BLOCK.get());

        this.add(ModBlocks.NEUTRALITE_ORE.get(),
                block -> createOreDrop(ModBlocks.NEUTRALITE_ORE.get(), ModItems.HEART_OF_GREED.get()));

    }

    protected LootTable.Builder createOreDrop(Block pBlock, Item pItem) {
        return createSilkTouchDispatchTable(pBlock,
                (LootPoolEntryContainer.Builder)this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(pItem).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}