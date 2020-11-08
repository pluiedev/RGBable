package com.leocth.rgbable.api;

import com.google.common.base.Preconditions;
import com.leocth.rgbable.api.color.HsvColor3f;
import com.leocth.rgbable.api.color.RgbColor3f;
import com.leocth.rgbable.api.color.ColorRepresentable;
import net.minecraft.nbt.*;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;

/**
 * Some constants and helper functions for colors.
 * @author leocth
 */
public final class Colors {
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

    @Deprecated
    public static Optional<ColorRepresentable> parseOmniaTag(CompoundTag tag, String sub) {
        if (tag.contains(sub)) {
            Tag t = tag.get(sub);
            Preconditions.checkNotNull(t); // why would it be null anyway? shut the fuck up idea
            switch (t.getType()) {
                case 3:
                    return parseIntTag((IntTag) t);
                case 9:
                    return parseStringTag((StringTag) t);
                default:
                    throw new IllegalStateException("Welp, didn't expect a tag with type " + t.getType() + "!");
            }
        }
        return Optional.empty();
    }

    @Deprecated
    public static Optional<ColorRepresentable> parseIntTag(IntTag tag) {
        return Optional.of(RgbColor3f.fromRgb(tag.getInt()));
    }

    @Deprecated
    public static Optional<ColorRepresentable> parseStringTag(StringTag tag) {
        String string = tag.toString();
        if (string.startsWith("#")) {
            // hex string
            String sub = string.substring(1);
            int hex = Integer.parseInt(sub, 16);
            return Optional.of(RgbColor3f.fromRgb(hex));
        }
        else {
            return Optional.of(RgbColor3f.fromRgb(Integer.parseInt(string)));
        }
    }

    @Deprecated
    public static HsvColor3f toHsv(RgbColor3f rgb) {
        float r = rgb.getR();
        float g = rgb.getG();
        float b = rgb.getB();
        float min = Math.min(r, g);
        min = Math.min(min, b);
        float max = Math.max(r, g);
        max = Math.max(max, b);

        float delta = max - min;

        if (delta < EPSILON) {
            return new HsvColor3f(0.0f, 0.0f, max, false);
        }
        if (max <= 0f) {
            return new HsvColor3f(Float.NaN, 0f, max, false);
        }
        else {
            // the >='s are here to make the compiler stfu.
            if (r >= max)
                return new HsvColor3f(     ((g - b) / delta) / 6, delta / max, max, false);
            if (g >= max)
                return new HsvColor3f((2f + (b - r) / delta) / 6, delta / max, max, false);
            else
                return new HsvColor3f((4f + (r - g) / delta) / 6, delta / max, delta, false);

        }
    }

}
