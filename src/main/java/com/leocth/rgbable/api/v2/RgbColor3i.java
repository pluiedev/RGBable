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

    public static class Serializer implements ColorSerializer<RgbColor3i> {
        @Override
        public void serialize(@NotNull RgbColor3i color, @NotNull CompoundTag tag) {
            tag.putIntArray("color", new int[]{color.r, color.g, color.b});
        }

        @Override
        public RgbColor3i deserialize(@NotNull CompoundTag tag) {
            int[] arr = tag.getIntArray("color");
            if (arr != null && arr.length == 3) {
                return new RgbColor3i(arr[0], arr[1], arr[2]);
            }
            return null;
        }
    }
}
