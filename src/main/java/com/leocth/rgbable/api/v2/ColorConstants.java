package com.leocth.rgbable.api.v2;

import net.minecraft.util.math.MathHelper;

/**
 * Some constants and helper functions for colors.
 * @author leocth
 */
public final class ColorConstants {
    public static final int BLACK = 0x000000;
    public static final int WHITE = 0xFFFFFF;
    public static final double EPSILON = 0.00001;

    /**
     * Normalize {@code n} to a range of 0 to 1.
     *
     * @param n the float to be <strong><del>COMPRESSED</del></strong> normalized
     * @return a normalized float
     */
    public static float normalize(float n) {
        return MathHelper.clamp(n, 0f, 1f);
    }
}
