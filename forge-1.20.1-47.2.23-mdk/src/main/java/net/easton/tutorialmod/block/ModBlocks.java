package net.easton.tutorialmod.block;

import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.block.custom.InfernalForgeBlock;
import net.easton.tutorialmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> CURSED_DIRT_BLOCK = registerBlock("cursed_dirt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.ROOTED_DIRT)));

    public static final RegistryObject<Block> MOLETEN_LANTERN = registerBlock("molten_lantern",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)));

 public static final RegistryObject<Block> SAND_BRICK = registerBlock("sand_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
public static final RegistryObject<Block> SAND_BRICK_FIXTURE = registerBlock("sand_brick_fixture",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));


    public static final RegistryObject<Block> NEUTRALITE_ORE = registerBlock("neutralite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));






    public static final RegistryObject<Block> INFERNAL_FORGE = registerBlock("infernal_forge",
            () -> new InfernalForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}