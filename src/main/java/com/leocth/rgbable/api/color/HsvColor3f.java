package com.leocth.rgbable.api.color;

import com.leocth.rgbable.api.Colors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;

/**
 * Represents an HSV color value with three <i>normalized</i> floats.
 * NOTE: the valid values for hue ranges from 0 to 1, not 0 - 360.
 * @author leocth
 * @since v1.1.0
 * @deprecated v2.0.0
 */
@Deprecated
public class HsvColor3f implements ColorRepresentable {
    protected float hue;
    protected float saturation;
    protected float value;

    /**
     * @param hue the hue value.
     * @param saturation the saturation value.
     * @param value the value value (hold on that doesn't make sense)
     * @param hueDegrees if true, then treat {@code hue} as a angle ranging from 0 to 360 degrees,
     *                   instead of a normalized float from 0 to 1.
     */
    public HsvColor3f(float hue, float saturation, float value, boolean hueDegrees) {
        if (hueDegrees) {
            this.hue = Colors.normalize(MathHelper.wrapDegrees(hue) / 360f);
        }
        else {
            this.hue = Colors.normalize(hue < 0 ? hue + 1 : hue);
        }
        this.saturation = Colors.normalize(saturation);
        this.value = Colors.normalize(value);
    }

    public HsvColor3f(double r, double g, double b, boolean hueDegrees) { this((float) r, (float) g, (float) b, hueDegrees); }

    public HsvColor3f(HsvColor3f color) { this(color.hue, color.saturation, color.value, false); }

    @Override
    public int toPackedRgb() {
        return MathHelper.hsvToRgb(hue, saturation, value);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public HsvColor3f fromTag(CompoundTag tag) {
        throw new UnsupportedOperationException("TODO");
    }

    public float getHue() { return hue; }
    public float getSaturation() { return saturation; }
    public float getValue() { return value; }

    /**
     * Sets the hue value for a new {@code HsvColor3f} object.
     *
     * @param h the hue value
     * @param degrees if true, then treat {@code hue} as a angle ranging from 0 to 360 degrees,
     *                   instead of a normalized float from 0 to 1.
     * @return a new {@code HsvColor3f} object with a new value
     */
    public HsvColor3f setHue(float h, boolean degrees) { return new HsvColor3f(h, this.saturation, this.value, degrees); }

    /**
     * Sets the saturation value for a new {@code HsvColor3f} object.
     *
     * @param s the saturation value as a normalized float (0-1)
     * @return a new {@code HsvColor3f} object with a new value
     */
    public HsvColor3f setSaturation(float s) { return new HsvColor3f(this.hue, s, this.value, false); }

    /**
     * Sets the value value (ok this is dumb) for a new {@code HsvColor3f} object.
     *
     * @param v the value as a normalized float (0-1)
     * @return a new {@code HsvColor3f} object with a new value (pun <i>not</i> intended)
     */
    public HsvColor3f setValue(float v) { return new HsvColor3f(this.hue, this.saturation, v, false); }

    @Override
    public String toString() {
        return "hsv(" +
                String.format("%.2f", hue * 360f) +
                "°, " +
                String.format("%.2f", saturation * 100) +
                "%, " +
                String.format("%.2f", value * 100) +
                "%)";
    }

    public Text asText() {
        return new TranslatableText("tooltip.rgbable.hsv",
                String.format("%.2f", hue * 360f),
                String.format("%.2f", saturation * 100),
                String.format("%.2f", value * 100));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof HsvColor3f)) {
            return false;
        } else {
            HsvColor3f color = (HsvColor3f)object;
            if (this.hue != color.hue) {
                return false;
            } else if (this.saturation != color.saturation) {
                return false;
            } else {
                return this.value == color.value;
            }
        }
    }

    /**
     * A mutable variant of HsvColor3f. Use this if multiple operations are needed, to reduce allocation overhead.
     *
     * @author leocth
     */
    public static class Mutable extends HsvColor3f {

        public Mutable(float hue, float saturation, float value, boolean hueDegrees) { super(hue, saturation, value, hueDegrees); }
        public Mutable(double r, double g, double b, boolean hueDegrees) { super(r, g, b, hueDegrees); }

        public HsvColor3f.Mutable set(float hue, float saturation, float value, boolean hueDegrees) {
            if (hueDegrees) {
                this.hue = Colors.normalize(hue / 360f);
            }
            else {
                this.hue = Colors.normalize(hue);
            }
            this.saturation = saturation;
            this.value = value;
            return this;
        }

        @Override
        public HsvColor3f.Mutable setHue(float h, boolean degrees) {
            if (degrees) {
                this.hue = Colors.normalize(hue / 360f);
            }
            else {
                this.hue = Colors.normalize(hue);
            }
            return this;
        }

        @Override
        public HsvColor3f.Mutable setSaturation(float s) {
            this.saturation = s;
            return this;
        }

        @Override
        public HsvColor3f.Mutable setValue(float v) {
            this.value = v;
            return this;
        }
    }
}
