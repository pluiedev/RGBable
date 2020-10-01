package com.leocth.rgbable.api;

import com.leocth.rgbable.api.color.ColorRepresentable;
import com.leocth.rgbable.api.color.RgbColor3f;

/**
 * Interface that contains colors.
 * @author leocth
 */
public interface Colorable {

    int getRgb();
    void setRgb(int rgb);

    ColorRepresentable getColor();
    void setColor(ColorRepresentable color);

}
