package com.leocth.rgbable.api;

import net.minecraft.nbt.CompoundTag;

/**
 * Interface for serializing color data. Override these methods to write custom data to the tag.
 * (I dunno, HSV anyone?)
 * @author leocth
 */
public interface ColorSerializable {

    default int getColorTag(CompoundTag tag) {
        return tag.getInt("color");
    }

    default CompoundTag setColorTag(CompoundTag tag, Color3f color) {
        return setColorTag(tag, Color3f.toRgb(color));
    }

    default CompoundTag setColorTag(CompoundTag tag, int rgb) {
        tag.putInt("color", rgb);
        return tag;
    }
}
