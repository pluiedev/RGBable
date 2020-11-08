package com.leocth.rgbable.client;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.NetworkUtilities;
import com.leocth.rgbable.api.v2.registry.RgbableColorProviderRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

@Environment(EnvType.CLIENT)
public class RgbableClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RgbableColorProviderRegistry.BLOCK.register(Rgbable.RGB_BLOCK);
        RgbableColorProviderRegistry.ITEM.register(Rgbable.RGB_BLOCK_ITEM);
        ColorProviderRegistry.ITEM.register(new PaintbrushItemColorProvider(), Rgbable.PAINTBRUSH_ITEM);
        NetworkUtilities.registerS2CPackets();
        //ScreenRegistry.register(Rgbable.RGB_BLOCK_SH, RgbBlockScreen::new);
        //ScreenRegistry.register(Rgbable.PAINTBRUSH_SH, PaintbrushScreen::new);
    }
}
