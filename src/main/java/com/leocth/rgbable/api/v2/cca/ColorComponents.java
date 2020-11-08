package com.leocth.rgbable.api.v2.cca;

import com.leocth.rgbable.Rgbable;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;

public class ColorComponents {
    public static final ComponentKey<ColorComponent> COLOR =
            ComponentRegistry.getOrCreate(Rgbable.id("color"), ColorComponent.class);
}
