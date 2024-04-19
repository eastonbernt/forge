package net.easton.tutorialmod.item;

import net.easton.tutorialmod.TutorialMod;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> HEART_OF_GREED = ITEMS.register("heart_of_greed",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PHOENIX_FEATHER = ITEMS.register("phoenix_feather", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_PLATE = ITEMS.register("netherite_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY_CASING = ITEMS.register("alloy_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PHOENIX_FORGED_STEEL = ITEMS.register("phoenix_forged_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PHOENIX_FORGED_SWORD = ITEMS.register("phoenix_forged_sword", () -> new SwordItem(ModToolTiers.PHOENIX, 4,2, new Item.Properties()));




    public static final RegistryObject<Item> GILDED_MIDAS_CROWN =
            ITEMS.register("gilded_midas_crown", () -> new ArmorItem(ModArmorMaterials.HEART_OF_GREED, ArmorItem.Type.HELMET, new Item.Properties()));
public static final RegistryObject<Item> GILDED_MIDAS_CHESTPLATE =
            ITEMS.register("gilded_midas_chestplate", () -> new ArmorItem(ModArmorMaterials.HEART_OF_GREED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
public static final RegistryObject<Item> GILDED_MIDAS_LEGGINGS =
            ITEMS.register("gilded_midas_leggings", () -> new ArmorItem(ModArmorMaterials.HEART_OF_GREED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
public static final RegistryObject<Item> GILDED_MIDAS_BOOTS =
            ITEMS.register("gilded_midas_boots", () -> new ArmorItem(ModArmorMaterials.HEART_OF_GREED, ArmorItem.Type.BOOTS, new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

