package com.leocth.rgbable.api;

import com.leocth.rgbable.api.color.ColorRepresentable;
import com.leocth.rgbable.api.color.RgbColor3f;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

/**
 * A {@code BlockEntity} with the default RGB implementation built-in.
 *
 * @author leocth
 * @since v1.0.0
 * @deprecated v2.0.0
 */
@Deprecated
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

    @Override public ColorRepresentable getColor() { return RgbColor3f.fromRgb(rgb); }
    @Override public void setColor(ColorRepresentable color) {
        this.prevRgb = this.rgb;
        this.rgb = color.toPackedRgb();
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.getColorFromTag(tag).ifPresent(val -> rgb = val.toPackedRgb());
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        this.setColorToTag(tag,  RgbColor3f.fromRgb(rgb));
        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        this.getColorFromTag(tag).ifPresent(val -> rgb = val.toPackedRgb());
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        this.setColorToTag(tag, RgbColor3f.fromRgb(rgb));
        return tag;
    }

    public void tick() {
        // updates the color display. only executed in the client
        if (prevRgb != rgb && world != null && world.isClient) {
            world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
            prevRgb = rgb;
        }
    }
}
