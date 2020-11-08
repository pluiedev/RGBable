package com.leocth.rgbable.api.color;

import com.leocth.rgbable.api.Colors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;

/**
 * Represents an RGB color value with three <i>normalized</i> floats (for red, green, blue)
 * @author leocth
 * @since v1.0.0
 * @deprecated v2.0.0
 */
@Deprecated
public class RgbColor3f implements ColorRepresentable {
    protected float r;
    protected float g;
    protected float b;

    public RgbColor3f(float r, float g, float b) {
        this.r = Colors.normalize(r);
        this.g = Colors.normalize(g);
        this.b = Colors.normalize(b);
    }

    /**
     * Creates a RgbColor3f object with three integers ranging from 0 to 255.
     * @param r the red value ranging from 0 to 255.
     * @param g the green value ranging from 0 to 255.
     * @param b the blue value ranging from 0 to 255.
     */
    public RgbColor3f(int r, int g, int b) {
        this(r / 255f, g / 255f, b / 255f);
    }

    public RgbColor3f(double r, double g, double b) { this((float) r, (float) g, (float) b); }

    // copy constructors. mmhm, yes. my favorite.
    public RgbColor3f(RgbColor3f color) { this(color.r, color.g, color.b); }

    public float getR() { return r; }
    public float getG() { return g; }
    public float getB() { return b; }

    public RgbColor3f setR(float r) { return new RgbColor3f(r, this.g, this.b); }
    public RgbColor3f setG(float g) { return new RgbColor3f(this.r, g, this.b); }
    public RgbColor3f setB(float b) { return new RgbColor3f(this.r, this.g, b); }

    public RgbColor3f add(float r, float g, float b) {
        return new RgbColor3f(r + this.r, g + this.g, b + this.b);
    }

    /**
     * Converts a "packed" RGB integer to a RgbColor3f object.
     * @param rgb the "packed" RGB value, ranging from 0 - 0xFFFFFF.
     * @return a newly created RgbColor3f object.
     */
    public static RgbColor3f fromRgb(int rgb) {
        return new RgbColor3f(
                (rgb >> 16 & 255) / 255f,
                (rgb >>  8 & 255) / 255f,
                (rgb       & 255) / 255f
        );
    }

    /**
     * Use {@code toPackedRgb} instead.
     * @see RgbColor3f#toPackedRgb()
     */
    @Deprecated
    public static int toRgb(RgbColor3f color) {
        return color.toPackedRgb();
    }

    /**
     * Converts an immutable RgbColor3f object to a mutable RgbColor3f.Mutable object.
     * @return a new RgbColor3f.Mutable object with the same color data.
     */
    public RgbColor3f.Mutable toMutable() {
        return new Mutable(r, g, b);
    }

    @Override public int toPackedRgb() { return MathHelper.packRgb(r, g, b); }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag comp = new CompoundTag();
        comp.putInt("PackedRgb", toPackedRgb());
        comp.putFloat("r", r);
        comp.putFloat("g", g);
        comp.putFloat("b", b);
        tag.put("Color3f", comp);
        return tag;
    }

    @Override
    public ColorRepresentable fromTag(CompoundTag tag) {
        CompoundTag comp = tag.getCompound("Color3f");
        return new RgbColor3f(comp.getFloat("r"), comp.getFloat("g"), comp.getFloat("b"));
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
                String.format("%.2d", (int) r * 255),
                String.format("%.2d", (int) g * 255),
                String.format("%.2d", (int) b * 255));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof RgbColor3f)) {
            return false;
        } else {
            RgbColor3f color = (RgbColor3f)object;
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
    public static class Mutable extends RgbColor3f {

        public Mutable(float r, float g, float b) {
            super(r, g, b);
        }
        public Mutable(int r, int g, int b) {
            super(r, g, b);
        }

        public RgbColor3f.Mutable set(float r, float g, float b) {
            this.setR(r);
            this.setG(g);
            this.setB(b);
            return this;
        }

        public RgbColor3f.Mutable set(RgbColor3f color) {
            this.setR(color.r);
            this.setG(color.g);
            this.setB(color.b);
            return this;
        }

        @Override public RgbColor3f.Mutable setR(float r) { this.r = MathHelper.clamp(r, 0f, 1f); return this; }
        @Override public RgbColor3f.Mutable setG(float g) { this.g = MathHelper.clamp(g, 0f, 1f); return this; }
        @Override public RgbColor3f.Mutable setB(float b) { this.b = MathHelper.clamp(b, 0f, 1f); return this; }

        public RgbColor3f.Mutable add(float r, float g, float b) {
            return this.set(this.getR() + r, this.getG() + g, this.getB() + b);
        }
    }
}
