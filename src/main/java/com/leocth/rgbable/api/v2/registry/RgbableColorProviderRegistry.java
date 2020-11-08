package com.leocth.rgbable.api.v2.registry;

import com.leocth.rgbable.impl.v2.ColorProviderRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Makes custom items and blocks' colors handled by RGBable's default color provider.
 * @param <T>
 *
 * @author leocth
 * @since 2.0.0
 */
public interface RgbableColorProviderRegistry<T> {

    RgbableColorProviderRegistry<Item> ITEM = ColorProviderRegistryImpl.ITEM;
    RgbableColorProviderRegistry<Block> BLOCK = ColorProviderRegistryImpl.BLOCK;

    /**
     * Registers objects to use the color provider.
     * @param obj the objects
     */
    @SuppressWarnings({"unchecked", "varargs"})
    void register(T... obj);
}
