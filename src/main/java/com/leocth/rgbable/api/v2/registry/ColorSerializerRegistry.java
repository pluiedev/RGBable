package com.leocth.rgbable.api.v2.registry;

import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.ColorSerializer;
import com.leocth.rgbable.impl.v2.ColorSerializerRegistryImpl;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Registers custom {@code ColorSerializer}s. You will <em>have to</em> register color serializers
 * if you have custom color types.
 *
 * @author leocth
 * @since 2.0.0
 */
public interface ColorSerializerRegistry {

    ColorSerializerRegistry INSTANCE = new ColorSerializerRegistryImpl();

    /**
     * Registers a color serializer.
     * @param id the identifier, for serializing
     * @param klazz the class object of the color type
     * @param serializer the serializer
     * @param <T> the color type. Makes sure that the color class and the color serializers have the same generic type
     */
    <T extends Color> void register(
            @NotNull Identifier id,
            @NotNull Class<T> klazz,
            @NotNull ColorSerializer<T> serializer
    );

    /**
     * Retrieves a color serializer from an identifier.
     * @param id the identifier
     * @return a color serializer if registered, null otherwise
     */
    @Contract("null -> null")
    @Nullable ColorSerializer<? extends Color> getSerializerById(Identifier id);

    /**
     * Retrieves a color serializer from a color class
     * @param clazz the color class
     * @return a color serializer if registered, null otherwise
     */
    @Contract("null -> null")
    @Nullable <T extends Color> ColorSerializer<T> getSerializerByClass(Class<? extends Color> clazz);

    /**
     * Retrieves the identifier the color serializer is registered in.
     * @param serializer the color serializer
     * @return the identifier if registered, null otherwise
     */
    @Contract("null -> null")
    @Nullable Identifier getIdOfSerializer(ColorSerializer<? super Color> serializer);
}
