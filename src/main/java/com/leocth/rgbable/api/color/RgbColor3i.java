package com.leocth.rgbable.api.color;

import com.leocth.rgbable.api.Colors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;

/**
 * Represents an RGB color value with three integers (for red, green, blue), ranging from 0-255.
 * This is the canonical and simplest implementation of ColorRepresentable.
 * @author leocth
 */
public class RgbColor3i implements ColorRepresentable {
    protected int r;
    protected int g;
    protected int b;

    /**
     * Creates a RgbColor3f object with three integers ranging from 0 to 255.
     * @param r the red value ranging from 0 to 255.
     * @param g the green value ranging from 0 to 255.
     * @param b the blue value ranging from 0 to 255.
     */
    public RgbColor3i(int r, int g, int b) {
        this.r = MathHelper.clamp(r, 0, 255);
        this.g = MathHelper.clamp(g, 0, 255);
        this.b = MathHelper.clamp(b, 0, 255);
    }

    public RgbColor3i(int[] arr) {
        if (arr.length == 3) {
            this.r = MathHelper.clamp(arr[0], 0, 255);
            this.g = MathHelper.clamp(arr[1], 0, 255);
            this.b = MathHelper.clamp(arr[2], 0, 255);
        }
        throw new IllegalArgumentException("wrong number of elements in array: " + arr.length);
    }

    public RgbColor3i(RgbColor3i color) { this(color.r, color.g, color.b); }

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }

    public RgbColor3i setR(int r) { return new RgbColor3i(r, this.g, this.b); }
    public RgbColor3i setG(int g) { return new RgbColor3i(this.r, g, this.b); }
    public RgbColor3i setB(int b) { return new RgbColor3i(this.r, this.g, b); }

    public RgbColor3i add(int r, int g, int b) {
        return new RgbColor3i(r + this.r, g + this.g, b + this.b);
    }

    /**
     * Converts a "packed" RGB integer to a RgbColor3f object.
     * @param rgb the "packed" RGB value, ranging from 0 - 0xFFFFFF.
     * @return a newly created RgbColor3f object.
     */
    public static RgbColor3i fromRgb(int rgb) {
        return new RgbColor3i(
                rgb >> 16 & 255,
                rgb >>  8 & 255,
                rgb       & 255
        );
    }

    /**
     * Converts an immutable RgbColor3i object to a mutable RgbColor3i.Mutable object.
     * @return a new RgbColor3i.Mutable object with the same color data.
     */
    public Mutable toMutable() {
        return new Mutable(r, g, b);
    }

    @Override public int toPackedRgb() { return MathHelper.packRgb(r, g, b); }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("PackedRgb", toPackedRgb());
        tag.putIntArray("Rgb", new int[]{r, g, b});
        return tag;
    }

    @Override
    public RgbColor3i fromTag(CompoundTag tag) {
        return new RgbColor3i(tag.getIntArray("Rgb"));
    }

    @Override
    public String toString() {
        return "rgb(" +
                r +
                ", " +
                g +
                ", " +
                b +
                ')';
    }

    public Text asText() {
        return new TranslatableText("tooltip.rgbable.rgb",
                String.format("%d", r),
                String.format("%d", g),
                String.format("%d", b));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof RgbColor3i)) {
            return false;
        } else {
            RgbColor3i color = (RgbColor3i)object;
            if (this.r != color.r) {
                return false;
            } else if (this.g != color.g) {
                return false;
            } else {
                return this.b == color.b;
            }
        }
    }

    /**
     * A mutable variant of RgbColor3f. Use this if multiple operations are needed, to reduce allocation overhead.
     *
     * @author leocth
     */
    public static class Mutable extends RgbColor3i {

        public Mutable(int r, int g, int b) {
            super(r, g, b);
        }

        public Mutable set(int r, int g, int b) {
            this.setR(r);
            this.setG(g);
            this.setB(b);
            return this;
        }

        public Mutable set(RgbColor3i color) {
            this.setR(color.r);
            this.setG(color.g);
            this.setB(color.b);
            return this;
        }

        @Override public Mutable setR(int r) { this.r = MathHelper.clamp(r, 0, 255); return this; }
        @Override public Mutable setG(int g) { this.g = MathHelper.clamp(g, 0, 255); return this; }
        @Override public Mutable setB(int b) { this.b = MathHelper.clamp(b, 0, 255); return this; }

        @Override
        public RgbColor3i fromTag(CompoundTag tag) {
            int[] arr = tag.getIntArray("Rgb");
            return this.set(arr[0], arr[1], arr[2]);
        }

        public Mutable add(int r, int g, int b) {
            return this.set(this.getR() + r, this.getG() + g, this.getB() + b);
        }
    }
}
