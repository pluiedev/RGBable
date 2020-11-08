package com.leocth.rgbable.impl.v2;

import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.ColorSerializer;
import com.leocth.rgbable.api.v2.RgbColor3i;
import com.leocth.rgbable.api.v2.cca.ColorComponent;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

public class ColorComponentImpl implements ColorComponent {

    private Color color;
    /**
     * for test purposes only
     */
    @Deprecated
    private static final ColorSerializer serializer = new RgbColor3i.Serializer();;

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
        //Identifier id = new Identifier(tag.getString("id"));
        //ColorSerializer<RgbColor3i> serializer = new RgbColor3i.Serializer(); //TODO ColorSerializerRegistry.INSTANCE.get(id);
        //if (serializer != null)
        color = serializer.deserialize(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        //tag.putString("id", );
        //ColorSerializer serializer = new RgbColor3i.Serializer(); //TODO ColorSerializerRegistry.INSTANCE.get(id);
        //if (color != null)
        serializer.serialize(color, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ColorComponent)) return false;
        ColorComponent that = (ColorComponent) obj;
        return this.color != null && this.color.equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return this.color.hashCode();
    }
}
