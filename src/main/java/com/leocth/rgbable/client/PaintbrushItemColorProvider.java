package com.leocth.rgbable.client;

import com.leocth.rgbable.api.Colors;
import com.leocth.rgbable.impl.v2.ColorProviderImpl;
import net.minecraft.item.ItemStack;

public class PaintbrushItemColorProvider extends ColorProviderImpl {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 1) {
            return super.getColor(stack, tintIndex);
        }
        return Colors.WHITE;
    }
}
