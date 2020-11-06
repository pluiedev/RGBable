package com.leocth.rgbable.impl.v2;

import com.leocth.rgbable.api.v2.ColorSerializer;
import com.leocth.rgbable.api.v2.ColorSerializerRegistry;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.util.Identifier;

public class ColorSerializerRegistryImpl implements ColorSerializerRegistry {
     private Object2ObjectOpenHashMap<Identifier, ColorSerializer> id2ColorSerializers;

    @Override
    public ColorSerializer register(Identifier id, ColorSerializer serializer) {
        return id2ColorSerializers.put(id, serializer);
    }

    @Override
    public ColorSerializer get(Identifier id) {
        return id2ColorSerializers.get(id);
    }
}
