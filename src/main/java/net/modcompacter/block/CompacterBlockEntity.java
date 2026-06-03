package net.modcompacter.block;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.modcompacter.ModCompacter;
import net.modcompacter.screen.CompacterScreenHandler;
import org.jetbrains.annotations.Nullable;

public class CompacterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public static final BlockEntityType<CompacterBlockEntity> TYPE =
            BlockEntityType.Builder.create(CompacterBlockEntity::new, ModCompacter.COMPACTER_BLOCK).build(null);

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public CompacterBlockEntity() {
        super(TYPE);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.modcompacter.compacter");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, net.minecraft.entity.player.PlayerInventory inv, net.minecraft.entity.player.PlayerEntity player) {
        return new CompacterScreenHandler(syncId, inv, ScreenHandlerContext.EMPTY, this);
    }

    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public boolean tryCompact() {
        // Simple logic: if slot 0 has >=8 of a block item and slot1 is empty, consume 8 and produce compacted cobble for example
        ItemStack in = items.get(0);
        if (in.isEmpty() || !in.getItem().isIn(net.minecraft.tag.ItemTags.NON_FLAMMABLE_WOOD)) {
            // allow many items; we will specifically look for cobblestone example below
        }
        if (in.getCount() >= 8) {
            // For demo: if item is cobblestone, produce compacted_cobblestone
            Identifier cobbleId = new Identifier("minecraft", "cobblestone");
            Identifier itemId = Registry.ITEM.getId(in.getItem());
            if (itemId.equals(cobbleId)) {
                items.get(0).decrement(8);
                items.set(1, new ItemStack(Registry.BLOCK.get(new Identifier(ModCompacter.MODID, "compacted_cobblestone"))));
                markDirty();
                return true;
            }
        }
        return false;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        net.minecraft.nbt.NbtHelper.fromNbt(tag, items);
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        net.minecraft.nbt.NbtHelper.toNbt(tag, items);
    }
}
