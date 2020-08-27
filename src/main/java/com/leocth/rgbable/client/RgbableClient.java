package com.leocth.rgbable.client;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.RgbableBlockColorProvider;
import com.leocth.rgbable.api.RgbableItemColorProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class RgbableClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(new RgbableBlockColorProvider(), Rgbable.RGB_BLOCK);
        ColorProviderRegistry.ITEM.register(new RgbableItemColorProvider(), Rgbable.RGB_BLOCK_ITEM);

        ScreenRegistry.register(Rgbable.RGB_BLOCK_SH, RgbBlockScreen::new);
    }
}
