package com.leocth.rgbable.api;

import net.minecraft.util.math.MathHelper;

/**
 * Represents a color value with three *normalized* floats (for red, green, blue)
 * @author leocth
 */
public class Color3f {
    protected float r;
    protected float g;
    protected float b;

    public Color3f(float r, float g, float b) {
        this.r = MathHelper.clamp(r, 0f, 1f);
        this.g = MathHelper.clamp(r, 0f, 1f);
        this.b = MathHelper.clamp(r, 0f, 1f);
    }

    public Color3f(int r, int g, int b) {
        this(r / 255f, g / 255f, b / 255f);
    }

    public Color3f(double r, double g, double b) { this((float) r, (float) g, (float) b); }

    public float getR() { return r; }
    public float getG() { return g; }
    public float getB() { return b; }

    public Color3f setR(float r) { return new Color3f(r, this.g, this.b); }
    public Color3f setG(float g) { return new Color3f(this.r, g, this.b); }
    public Color3f setB(float b) { return new Color3f(this.r, this.g, b); }

    public Color3f add(float r, float g, float b) {
        return new Color3f(r + this.r, g + this.g, b + this.b);
    }

    /**
     * Converts a "packed" RGB integer to a Color3f object.
     * @param rgb the "packed" RGB value, ranging from 0 - 0xFFFFFF.
     * @return a newly created Color3f object.
     */
    public static Color3f fromRgb(int rgb) {
        return new Color3f(
                (rgb >> 16 & 255) / 255f,
                (rgb >>  8 & 255) / 255f,
                (rgb       & 255) / 255f
        );
    }

    /**
     * Converts a Color3f object to a "packed" RGB integer.
     * @param color the Color3f object.
     * @return a "packed" RGB integer representing the same color value.
     */
    public static int toRgb(Color3f color) {
        return MathHelper.packRgb(color.r, color.g, color.b);
    }

    /**
     * Converts an immutable Color3f object to a mutable Color3f.Mutable object.
     * @return a new Color3f.Mutable object with the same color data.
     */
    public Color3f.Mutable toMutable() {
        return new Mutable(this.r, this.g, this.b);
    }

    /**
     * A mutable variant of Color3f. Use this if multiple operations are needed, to reduce allocation overhead.
     *
     * @author leocth
     */
    public static class Mutable extends Color3f {

        public Mutable(float r, float g, float b) {
            super(r, g, b);
        }
        public Mutable(int r, int g, int b) {
            super(r, g, b);
        }

        public Color3f.Mutable set(float r, float g, float b) {
            this.setR(r);
            this.setG(g);
            this.setB(b);
            return this;
        }

        public Color3f.Mutable set(Color3f color) {
            this.setR(color.r);
            this.setG(color.g);
            this.setB(color.b);
            return this;
        }

        @Override public Color3f.Mutable setR(float r) { this.r = MathHelper.clamp(r, 0f, 1f); return this; }
        @Override public Color3f.Mutable setG(float g) { this.g = MathHelper.clamp(g, 0f, 1f); return this; }
        @Override public Color3f.Mutable setB(float b) { this.b = MathHelper.clamp(b, 0f, 1f); return this; }

        public Color3f.Mutable add(float r, float g, float b) {
            return this.set(this.getR() + r, this.getG() + g, this.getB() + b);
        }
    }
}
