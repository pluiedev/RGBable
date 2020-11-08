package com.leocth.rgbable.client;

import com.leocth.rgbable.api.Colors;
import com.leocth.rgbable.api.color.ColorRepresentable;
import com.leocth.rgbable.api.v2.providers.RgbableItemColorProvider;
import net.minecraft.item.ItemStack;

public class PaintbrushItemColorProvider extends RgbableItemColorProvider {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 1) {
            return super.getColor(stack, tintIndex);
        }
        return Colors.WHITE;
    }
}
