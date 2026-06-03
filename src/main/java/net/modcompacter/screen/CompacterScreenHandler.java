package net.modcompacter.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.screen.ScreenHandlerContext;
import net.modcompacter.block.CompacterBlockEntity;

public class CompacterScreenHandler extends ScreenHandler {
    private final CompacterBlockEntity be;

    public CompacterScreenHandler(int syncId, PlayerInventory inv, ScreenHandlerContext ctx, CompacterBlockEntity be) {
        super(net.modcompacter.ModCompacter.COMPACTER_SCREEN_HANDLER, syncId);
        this.be = be;

        // Input slot
        this.addSlot(new Slot(new SimpleInventory(1), 0, 44, 20));
        // Output slot
        this.addSlot(new Slot(new SimpleInventory(1), 1, 116, 35) {
            @Override
            public boolean canInsert(ItemStack stack) { return false; }
        });

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public void compact() {
        be.tryCompact();
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY; // Simplified; implement as needed
    }
}
