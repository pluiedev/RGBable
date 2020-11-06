package com.leocth.rgbable.api.v2;

import net.minecraft.util.Identifier;

/**
 * @since 2.0.0
 */
public interface ColorSerializerRegistry {
    ColorSerializer register(Identifier id, ColorSerializer serializer);

    ColorSerializer get(Identifier id);
}
