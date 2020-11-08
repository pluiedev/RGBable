package com.leocth.rgbable.impl.v2;

import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.cca.ColorComponent;

public class ColorProviderCommon {
    public static final int DEFAULT_COLOR = 0xffffff;

    public static int getColorOfComponent(ColorComponent component) {
        if (component != null) {
            Color color = component.getColor();
            return color == null ? DEFAULT_COLOR : color.toRgb();
        }
        return DEFAULT_COLOR;
    }
}
