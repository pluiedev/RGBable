package com.leocth.rgbable.api.v2.providers;

import com.leocth.rgbable.api.v2.cca.ColorComponent;
import com.leocth.rgbable.impl.v2.ColorProviderCommon;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemStack;

public class RgbableItemColorProvider implements ItemColorProvider {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        return ColorProviderCommon.getColorOfComponent(ColorComponent.get(stack));
    }
}
