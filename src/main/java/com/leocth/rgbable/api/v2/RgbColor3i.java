package com.leocth.rgbable.api.v2;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents an RGB color value with three integers (for red, green, blue), ranging from 0-255.
 * This is the canonical and simplest implementation of {@see Color}.
 * @author leocth
 */
public class RgbColor3i implements Color {
    public int r, g, b;

    public RgbColor3i(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public int toRgb() {
        return MathHelper.packRgb(r, g, b); // no reinventing the wheel :yeefuckinhaw:
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RgbColor3i that = (RgbColor3i) o;
        return r == that.r &&
                g == that.g &&
                b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }

    public static class Serializer implements ColorSerializer {
        @Override
        public void serialize(@NotNull Color color, @NotNull CompoundTag tag) {
            if (color instanceof RgbColor3i) {
                RgbColor3i rgbColor3i = (RgbColor3i) color;
                tag.putIntArray("color", new int[]{rgbColor3i.r, rgbColor3i.g, rgbColor3i.b});
            }
        }

        @Override
        public Color deserialize(@NotNull CompoundTag tag) {
            int[] arr = tag.getIntArray("color");
            if (arr != null && arr.length == 3) {
                return new RgbColor3i(arr[0], arr[1], arr[2]);
            }
            return null;
        }
    }
}
