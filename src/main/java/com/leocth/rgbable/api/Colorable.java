package com.leocth.rgbable.api;

import com.leocth.rgbable.api.color.ColorRepresentable;

/**
 * Interface that contains colors.
 * @author leocth
 * @since v1.0.0
 * @deprecated v2.0.0
 */
@Deprecated
public interface Colorable {

    int getRgb();
    void setRgb(int rgb);

    ColorRepresentable getColor();
    void setColor(ColorRepresentable color);

}
