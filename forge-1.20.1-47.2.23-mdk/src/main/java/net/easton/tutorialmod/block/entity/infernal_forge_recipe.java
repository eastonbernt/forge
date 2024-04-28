package net.easton.tutorialmod.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;


public class infernal_forge_recipe {
    private static final int OUTPUT_SLOT = 9;

    public Item[][] input;
    public Item output;

    public infernal_forge_recipe(Item[][] input, Item output) {
        this.input = input;
        this.output = output;
    }

    private Item get_item_at(int i) {
        return this.input[i / 3][i % 3];
    }

    public boolean can_craft(ItemStackHandler itemHandler) {
        for(int i=0; i<9; i++) {
            if(itemHandler.getStackInSlot(i).getItem() != get_item_at(i))
                return false;
        }
        return canInsertAmountIntoOutputSlot(itemHandler, 1) && canInsertItemIntoOutputSlot(itemHandler);
    }

    public void craft(ItemStackHandler itemHandler) {
        for(int i=0; i<9; i++) {
            if(get_item_at(i) != ItemStack.EMPTY.getItem()) {
                itemHandler.extractItem(i, 1, false);
            }
        }
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(this.output, itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + 1));
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStackHandler itemHandler, int count) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStackHandler itemHandler) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT).is(this.output);
    }
}
