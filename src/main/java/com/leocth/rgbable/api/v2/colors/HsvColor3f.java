package com.leocth.rgbable.api.v2.colors;

import com.leocth.rgbable.api.Colors;
import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.ColorSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HsvColor3f implements Color {
    public final float hue;
    public final float saturation;
    public final float value;

    /**
     * @param hue the hue value.
     * @param saturation the saturation value.
     * @param value the value of the value :)
     */
    public HsvColor3f(float hue, float saturation, float value) {
        this(hue, saturation, value, false);
    }

    /**
     * @param hue the hue value.
     * @param saturation the saturation value.
     * @param value the value of the value :)
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

    @Override
    public int toRgb() {
        return MathHelper.hsvToRgb(hue, saturation, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HsvColor3f that = (HsvColor3f) o;
        return Float.compare(that.hue, hue) == 0 &&
                Float.compare(that.saturation, saturation) == 0 &&
                Float.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hue, saturation, value);
    }

    public static class Serializer implements ColorSerializer<HsvColor3f> {
        @Override
        public void serialize(@NotNull HsvColor3f color, @NotNull CompoundTag tag) {
            ListTag listTag = new ListTag();
            listTag.add(FloatTag.of(color.hue));
            listTag.add(FloatTag.of(color.saturation));
            listTag.add(FloatTag.of(color.value));
            tag.put("color", listTag);
        }

        @Override
        public HsvColor3f deserialize(@NotNull CompoundTag tag) {
            ListTag listTag = tag.getList("color", 5);
            if (listTag.size() == 3) {
                return new HsvColor3f(listTag.getFloat(0), listTag.getFloat(1), listTag.getFloat(2));
            }
            return null;
        }
    }
}
