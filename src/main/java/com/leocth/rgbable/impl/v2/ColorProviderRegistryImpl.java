package com.leocth.rgbable.impl.v2;

import com.leocth.rgbable.api.v2.RgbableColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;

public abstract class ColorProviderRegistryImpl<T, Provider> implements RgbableColorProviderRegistry<T> {
    public static final ColorProviderRegistryImpl<Item, ItemColorProvider> ITEM = new ColorProviderRegistryImpl<Item, ItemColorProvider>(new ColorProviderImpl()) {
        @Override
        protected final void registerUnderlying(ItemColorProvider provider, Item obj) {
            ColorProviderRegistry.ITEM.register(provider, obj);
        }
    };
    public static final ColorProviderRegistryImpl<Block, BlockColorProvider> BLOCK = new ColorProviderRegistryImpl<Block, BlockColorProvider>(new ColorProviderImpl()) {
        @Override
        protected final void registerUnderlying(BlockColorProvider provider, Block obj) {
            ColorProviderRegistry.BLOCK.register(provider, obj);
        }
    };
    private final Provider provider;

    public ColorProviderRegistryImpl(Provider provider) {
        this.provider = provider;
    }

    protected abstract void registerUnderlying(Provider provider, T obj);

    @Override
    @SafeVarargs
    public final void register(T... objs) {
        for (T obj : objs) registerUnderlying(provider, obj);
    }
}
