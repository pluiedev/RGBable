package com.leocth.rgbable.api.v2;

import java.util.Optional;

/**
 * A functional interface that converts a color into another format.
 * Might result in precision loss.
 * @param <T> the source type
 * @param <R> the result type
 */
@FunctionalInterface
public interface ColorConverter<T extends Color, R extends Color> {
    /**
     * Converts a color to another type.
     * @param color the color
     * @return an optional value that might contain the color but represented in another type/color space.
     */
    Optional<R> convert(T color);
}
