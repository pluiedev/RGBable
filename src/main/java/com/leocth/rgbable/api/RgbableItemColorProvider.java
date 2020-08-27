package com.leocth.rgbable.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemStack;

/**
 * A default implementation of ItemColorProvider for simple RGB block items.
 * Note you will still have to register the color provider yourself, using {@code ColorProviderRegistry#register}.
 * @see net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry#register
 *
 * @author leocth
 */
@Environment(EnvType.CLIENT)
public class RgbableItemColorProvider implements ItemColorProvider {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        return Colorable.getRgbFromItemStack(stack).orElse(Colors.WHITE);
    }
}
