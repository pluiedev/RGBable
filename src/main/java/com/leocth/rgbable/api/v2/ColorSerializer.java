package com.leocth.rgbable.api.v2;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * (De-)serializes colors.
 *
 * @since 2.0.0
 */
public interface ColorSerializer {

    /**
     * Serializes and saves the color data into a {@see CompoundTag}.
     * @param tag the tag to serialize to
     */
    @Nullable Color deserialize(@NotNull CompoundTag tag);

    /**
     * Loads and deserializes the color data from a {@see CompoundTag}.
     * @param tag the tag to deserialize from
     */
    void serialize(@NotNull Color color, @NotNull CompoundTag tag);
}
