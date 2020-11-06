package com.leocth.rgbable.api;

import com.leocth.rgbable.common.block.RgbBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

/**
 * A default implementation of BlockColorProvider for simple RGB blocks.
 * Note you will still have to register the color provider yourself, using {@code ColorProviderRegistry#register}.
 * @see net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry#register
 *
 * @author leocth
 */
@Deprecated
@Environment(EnvType.CLIENT)
public class RgbableBlockColorProvider implements BlockColorProvider {
    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof RgbBlockEntity)) {
                return Colors.WHITE;
            }
            return ((RgbBlockEntity) blockEntity).getRgb();
        }
        return Colors.WHITE;
    }
}
