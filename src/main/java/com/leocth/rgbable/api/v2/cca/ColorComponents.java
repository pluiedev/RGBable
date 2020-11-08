package com.leocth.rgbable.api.v2.cca;

import com.leocth.rgbable.Rgbable;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;

/**
 * Storage of Components.
 *
 * @author leocth
 * @since 2.0.0
 */
public class ColorComponents {
    public static final ComponentKey<ColorComponent> COLOR =
            ComponentRegistry.getOrCreate(Rgbable.id("color"), ColorComponent.class);
}
