package com.leocth.rgbable.api.v2;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * (De-)serializes colors.
 *
 * @author leocth
 * @since 2.0.0
 */
public interface ColorSerializer<T> {

    /**
     * Serializes and saves the color data into a {@see CompoundTag}.
     * @param tag the tag to serialize to
     */
    @Nullable T deserialize(@NotNull CompoundTag tag);

    /**
     * Loads and deserializes the color data from a {@see CompoundTag}.
     * @param tag the tag to deserialize from
     */
    void serialize(@NotNull T color, @NotNull CompoundTag tag);
}
