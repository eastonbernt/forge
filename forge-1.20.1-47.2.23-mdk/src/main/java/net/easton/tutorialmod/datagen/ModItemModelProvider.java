package net.easton.tutorialmod.datagen;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.PHOENIX_FEATHER);
        simpleItem(ModItems.HEART_OF_GREED);
        simpleItem(ModItems.NETHERITE_PLATE);
        simpleItem(ModItems.ALLOY_CASING);
        simpleItem(ModItems.PHOENIX_FORGED_STEEL);
        simpleItem(ModItems.PHOENIX_FORGED_STEEL);
        handheldItem(ModItems.PHOENIX_FORGED_SWORD);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
        new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}

