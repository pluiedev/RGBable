package com.leocth.rgbable.common.block;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.RgbableBlock;
import com.leocth.rgbable.common.block.RgbBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

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
