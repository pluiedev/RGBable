package com.leocth.rgbable.client;

import com.leocth.rgbable.api.Colors;
import com.leocth.rgbable.api.RgbableItemColorProvider;
import com.leocth.rgbable.api.color.ColorRepresentable;
import net.minecraft.item.ItemStack;

public class PaintbrushItemColorProvider extends RgbableItemColorProvider {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 1) {
            return getColorFromStack(stack).map(ColorRepresentable::toPackedRgb).orElse(Colors.WHITE);
        }
        return Colors.WHITE;
    }
}
