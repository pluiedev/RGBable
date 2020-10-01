package com.leocth.rgbable.client;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.Colors;
import com.leocth.rgbable.api.NetworkUtilities;
import com.leocth.rgbable.api.RgbableBlockColorProvider;
import com.leocth.rgbable.api.RgbableItemColorProvider;
import com.leocth.rgbable.api.color.ColorRepresentable;
import com.leocth.rgbable.client.gui.PaintbrushScreen;
import com.leocth.rgbable.client.gui.RgbBlockScreen;
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
        ColorProviderRegistry.ITEM.register(new PaintbrushItemColorProvider(), Rgbable.PAINTBRUSH_ITEM);
        NetworkUtilities.registerS2CPackets();
        ScreenRegistry.register(Rgbable.RGB_BLOCK_SH, RgbBlockScreen::new);
        ScreenRegistry.register(Rgbable.PAINTBRUSH_SH, PaintbrushScreen::new);
    }
}
