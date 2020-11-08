package com.leocth.rgbable.impl.v2;

import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.ColorSerializer;
import com.leocth.rgbable.api.v2.registry.ColorSerializerRegistry;
import com.leocth.rgbable.api.v2.cca.ColorComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ColorComponentImpl implements ColorComponent {

    private Color color;

    @Override
    public @Nullable Color getColor() {
        return color;
    }

    @Override
    public void setColor(@Nullable Color color) {
        this.color = color;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        Identifier id = new Identifier(tag.getString("id"));
        ColorSerializer<? extends Color> serializer = ColorSerializerRegistry.INSTANCE.getSerializerById(id);
        if (serializer != null)
            color = serializer.deserialize(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if (color != null) {
            ColorSerializer<? super Color> serializer = ColorSerializerRegistry.INSTANCE.getSerializerByClass(color.getClass());
            Identifier id = ColorSerializerRegistry.INSTANCE.getIdOfSerializer(serializer);
            if (id != null) {
                tag.putString("id", id.toString());
                serializer.serialize(color, tag);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ColorComponent)) return false;
        if (this.color == null) return true;
        ColorComponent that = (ColorComponent) obj;
        return this.color.equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return this.color.hashCode();
    }
}
