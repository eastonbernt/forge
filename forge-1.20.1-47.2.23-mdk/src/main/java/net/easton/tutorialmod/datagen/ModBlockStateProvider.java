package net.easton.tutorialmod.datagen;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.CURSED_DIRT_BLOCK);
        blockWithItem(ModBlocks.NEUTRALITE_ORE);

        simpleBlockWithItem(ModBlocks.INFERNAL_FORGE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/infernal_forge")));

    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}


