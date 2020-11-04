package com.leocth.rgbable.api.color;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

import java.util.function.Function;

@Deprecated
public class ColorParser {

    private final Object2ObjectOpenHashMap<Identifier, Function<CompoundTag, ColorRepresentable>> type2deserializer
            = new Object2ObjectOpenHashMap<>();

    public void register(Identifier type, Function<CompoundTag, ColorRepresentable> deserializer) {
        if (!type2deserializer.containsKey(type)) {
            type2deserializer.put(type, deserializer);
        }
    }

    public ColorRepresentable deserialize(CompoundTag tag) {
        Identifier type = Identifier.tryParse(tag.getString("Type"));
        return type2deserializer.get(type).apply(tag);
    }
}
