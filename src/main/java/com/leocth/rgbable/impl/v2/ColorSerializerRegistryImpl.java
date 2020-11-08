package com.leocth.rgbable.impl.v2;

import com.google.common.collect.HashBiMap;
import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.ColorSerializer;
import com.leocth.rgbable.api.v2.registry.ColorSerializerRegistry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ColorSerializerRegistryImpl implements ColorSerializerRegistry {

    private final HashBiMap<Identifier, ColorSerializer<? extends Color>> id2Serializer;
    private final HashBiMap<Class<? extends Color>, ColorSerializer<? extends Color>> classToSerializer;

    public ColorSerializerRegistryImpl() {
        this.id2Serializer = HashBiMap.create();
        this.classToSerializer = HashBiMap.create();
    }

    @Override
    public <T extends Color> void register(@NotNull Identifier id, @NotNull Class<T> klazz, @NotNull ColorSerializer<T> serializer) {
        id2Serializer.put(id, serializer);
        classToSerializer.put(klazz, serializer);
    }

    @Override
    public ColorSerializer<? extends Color> getSerializerById(Identifier id) {
        return id2Serializer.get(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Color> ColorSerializer<T> getSerializerByClass(Class<? extends Color> clazz) {
        return (ColorSerializer<T>) classToSerializer.get(clazz);
    }

    @Override
    public Identifier getIdOfSerializer(ColorSerializer<? super Color> serializer) {
        return id2Serializer.inverse().get(serializer);
    }
}
