package com.leocth.rgbable.api;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

/**
 * A {@code BlockEntity} with the default RGB implementation built-in.
 * @author leocth
 */
public abstract class RgbableBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Colorable, ColorSerializable, Tickable {

    protected int prevRgb;
    protected int rgb;

    public RgbableBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    @Override public int getRgb() { return rgb; }
    @Override public void setRgb(int rgb) {
        this.prevRgb = this.rgb;
        this.rgb = rgb;
    }

    @Override public Color3f getColor() { return Color3f.fromRgb(rgb); }
    @Override public void setColor(Color3f color) {
        this.prevRgb = this.rgb;
        this.rgb = Color3f.toRgb(color);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        rgb = this.getColorTag(tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        this.setColorTag(tag, rgb);
        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        rgb = getColorTag(tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        setColorTag(tag, rgb);
        return tag;
    }

    public void tick() {
        // updates the color display. only executed in the client
        if (prevRgb != rgb && this.world != null && this.world.isClient) {
            this.world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
            prevRgb = rgb;
        }
    }
}
