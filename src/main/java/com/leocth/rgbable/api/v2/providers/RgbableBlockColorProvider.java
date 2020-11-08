package com.leocth.rgbable.api.v2.providers;

import com.leocth.rgbable.api.v2.cca.ColorComponent;
import com.leocth.rgbable.api.v2.cca.ColorComponents;
import com.leocth.rgbable.impl.v2.ColorProviderCommon;
import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class RgbableBlockColorProvider implements BlockColorProvider {
    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null) {
            ColorComponent component = BlockComponents.get(ColorComponents.COLOR, state, world, pos);
            return ColorProviderCommon.getColorOfComponent(component);
        }
        return ColorProviderCommon.DEFAULT_COLOR;
    }
}
