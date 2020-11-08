package com.leocth.rgbable.api.v2;

import com.leocth.rgbable.impl.v2.ColorProviderRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Help
 * @param <T>
 */
public interface RgbableColorProviderRegistry<T> {

    RgbableColorProviderRegistry<Item> ITEM = ColorProviderRegistryImpl.ITEM;
    RgbableColorProviderRegistry<Block> BLOCK = ColorProviderRegistryImpl.BLOCK;

    void register(T... obj);
}
