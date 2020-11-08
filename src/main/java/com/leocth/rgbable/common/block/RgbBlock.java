package com.leocth.rgbable.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class RgbBlock extends Block {
    public RgbBlock() {
        super(FabricBlockSettings.copy(Blocks.STONE));
    }

    /*
    @Override
    public ItemStack getItem() {
        return new ItemStack(Rgbable.RGB_BLOCK_ITEM);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new RgbBlockEntity();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.isSneaking()) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof RgbBlockEntity) {
                    player.openHandledScreen((RgbBlockEntity) be);
                }
                return ActionResult.SUCCESS;
            }
            else {
                return ActionResult.FAIL;
            }
        }
        else {
            return ActionResult.FAIL;
        }
    }
    */
}
