package com.leocth.rgbable.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * A {@code Block} with the default RGB implementation built-in.
 */
public abstract class RgbableBlock extends Block implements BlockEntityProvider, ColorSerializable {
    public RgbableBlock(Settings settings) {
        super(settings);
    }

    /**
     * Overrides {@code onBreak} to store custom NBT color data.
     *
     * @param world  the world. CAUTION: no guarantees on logical sides are being made!
     * @param pos    the block pos
     * @param state  the block state
     * @param player the player that broke the block
     */
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof Colorable) {
            Colorable colorable = (Colorable) blockEntity;
            if (!world.isClient && !player.isCreative()) {
                ItemStack itemStack = getItem();
                CompoundTag tag = new CompoundTag();
                this.setColorToTag(tag, colorable.getColor());
                itemStack.putSubTag("BlockEntityTag", tag);
                ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    /**
     * Overrides {@code getPickStack} to store custom NBT color data.
     *
     * @param world the world
     * @param pos   the position of the block
     * @param state the state of the block
     * @return an item stack containing the block item and the color data
     */
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack itemStack = super.getPickStack(world, pos, state);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof Colorable) {
            Colorable colorable = (Colorable) blockEntity;
            CompoundTag tag = new CompoundTag();
            this.setColorToTag(tag, colorable.getColor());
            itemStack.putSubTag("BlockEntityTag", tag);
        }
        return itemStack;
    }

    /**
     * Gets the base item stack that is going to be appended with color data.
     * Put any additional custom NBT data here before returning.
     *
     * @return an item stack, ready to be written with color data
     */
    public abstract ItemStack getItem();
}
