package com.leocth.rgbable.impl.v2;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.v2.cca.ColorComponents;
import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;

@SuppressWarnings("UnstableApiUsage")
public class ColorComponentInitializer implements BlockComponentInitializer, ItemComponentInitializer {

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        //TODO expose api
        registry.registerFor(Rgbable.RGB_BLOCK_ID, ColorComponents.COLOR, (state, world, pos, side) -> new ColorComponentImpl());
    }

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        //TODO expose api
        registry.registerFor(Rgbable.RGB_BLOCK_ID, ColorComponents.COLOR, (stack) -> new ColorComponentImpl());
        registry.registerFor(Rgbable.PAINTBRUSH_ID, ColorComponents.COLOR, (stack) -> new ColorComponentImpl());
    }
}
