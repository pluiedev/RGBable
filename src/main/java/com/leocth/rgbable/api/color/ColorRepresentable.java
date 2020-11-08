package com.leocth.rgbable.api.color;

import net.minecraft.nbt.CompoundTag;

/**
 * A color value that can be expressed in a packed RGB value, allowing varying implementations.
 * @author leocth
 * @since v1.1.0
 * @deprecated v2.0.0
 */
@Deprecated
public interface ColorRepresentable {
    /**
     * Returns a packed RGB integer that represents the same color value (allowing precision losses)
     * @return the packed RGB integer
     */
    int toPackedRgb();

    CompoundTag toTag(CompoundTag tag);
    ColorRepresentable fromTag(CompoundTag tag);

}
