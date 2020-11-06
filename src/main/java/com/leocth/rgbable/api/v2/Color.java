package com.leocth.rgbable.api.v2;


/**
 * An interface to represent color.
 * 
 * @since 2.0.0
 */
public interface Color {
    /**
     * Returns the color value in RGB format. Used with ColorProviders.
     * @return the color in RGB format
     */
    int toRgb();

}
