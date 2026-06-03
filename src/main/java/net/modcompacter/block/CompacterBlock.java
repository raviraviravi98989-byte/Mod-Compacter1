package net.modcompacter.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CompacterBlock extends Block {
    public CompacterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Nullable
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, net.minecraft.util.math.BlockPos pos) {
        // Provide a simple screen handler factory; the block entity will implement NamedScreenHandlerFactory
        net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof NamedScreenHandlerFactory) {
            return (NamedScreenHandlerFactory) be;
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, net.minecraft.util.math.BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            net.minecraft.block.entity.BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof NamedScreenHandlerFactory) {
                player.openHandledScreen((NamedScreenHandlerFactory) be);
            }
        }
        return ActionResult.SUCCESS;
    }
}
