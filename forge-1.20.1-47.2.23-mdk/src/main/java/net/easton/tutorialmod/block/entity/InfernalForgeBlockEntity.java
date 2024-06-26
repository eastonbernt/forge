package net.easton.tutorialmod.block.entity;

import net.easton.tutorialmod.item.ModItems;
import net.easton.tutorialmod.screen.InfernalForgeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InfernalForgeBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(10);

    //private static final int INPUT_SLOT = 8;
    private static final int OUTPUT_SLOT = 9;

    private infernal_forge_recipe gilded_midas_boots_recipe = new infernal_forge_recipe(
            new Item[][] {
                    { ItemStack.EMPTY.getItem(),     ItemStack.EMPTY.getItem(), ItemStack.EMPTY.getItem() },
                    { ModItems.HEART_OF_GREED.get(), ItemStack.EMPTY.getItem(), ModItems.HEART_OF_GREED.get() },
                    { ModItems.HEART_OF_GREED.get(), ItemStack.EMPTY.getItem(), ModItems.HEART_OF_GREED.get() }
            },
            ModItems.GILDED_MIDAS_BOOTS.get()
    );

    private infernal_forge_recipe gilded_midas_leggings_recipe = new infernal_forge_recipe(
            new Item[][] {
                    { ModItems.HEART_OF_GREED.get(), ModItems.HEART_OF_GREED.get(), ModItems.HEART_OF_GREED.get() },
                    { ModItems.HEART_OF_GREED.get(), ItemStack.EMPTY.getItem(),     ModItems.HEART_OF_GREED.get() },
                    { ModItems.HEART_OF_GREED.get(), ItemStack.EMPTY.getItem(),     ModItems.HEART_OF_GREED.get() }
            },
            ModItems.GILDED_MIDAS_LEGGINGS.get()
    );

    private infernal_forge_recipe gilded_midas_chest_recipe = new infernal_forge_recipe(
            new Item[][] {
                    { ModItems.HEART_OF_GREED.get(), ItemStack.EMPTY.getItem(), ModItems.HEART_OF_GREED.get() },
                    { ModItems.HEART_OF_GREED.get(), ModItems.HEART_OF_GREED.get(),     ModItems.HEART_OF_GREED.get() },
                    { ModItems.HEART_OF_GREED.get(), ModItems.HEART_OF_GREED.get(),     ModItems.HEART_OF_GREED.get() }
            },
            ModItems.GILDED_MIDAS_CHESTPLATE.get()
    );

    private infernal_forge_recipe[] recipes = new infernal_forge_recipe[] {
            gilded_midas_boots_recipe,
            gilded_midas_leggings_recipe,
            gilded_midas_chest_recipe
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxprogress = 78;

    public InfernalForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.INFERNAL_FORGE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 8 -> InfernalForgeBlockEntity.this.progress;
                    case 9 -> InfernalForgeBlockEntity.this.maxprogress;
                    default -> 8;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 8 -> InfernalForgeBlockEntity.this.progress = i1;
                    case 9 -> InfernalForgeBlockEntity.this.maxprogress = i1;
                }
            }

            @Override
            public int getCount() {
                return 10;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
    for(int i = 0; i < itemHandler.getSlots(); i++) {
        inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tutorialmod.infernal_forge");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new InfernalForgeMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("infernal_forge_progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("infernal_forge_progress");
    }

    public void tick(Level plevel, BlockPos blockPos, BlockState pState) {
        if(hasRecipe()){
            increaseCraftingProgress();

            setChanged(plevel, blockPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        for (infernal_forge_recipe recipe : recipes) {
            if (recipe.can_craft(this.itemHandler)) {
                recipe.craft(this.itemHandler);
                return;
            }
        }
    }

    private boolean hasProgressFinished() {
        return progress >=maxprogress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        for (infernal_forge_recipe recipe : recipes) {
            if (recipe.can_craft(this.itemHandler)) {
                return true;
            }
        }
        return false;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

}
