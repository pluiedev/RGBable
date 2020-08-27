package com.leocth.rgbable.api;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

/**
 * Interface that contains colors.
 * @author leocth
 */
public interface Colorable {

    int getRgb();
    void setRgb(int rgb);

    Color3f getColor();
    void setColor(Color3f color);

    /**
     * Extracts "packed" RGB data from an {@code ItemStack}.
     * @param stack the item stack
     * @return an <i>optional</i> integer containing the "packed" RGB color data.
     */
    static Optional<Integer> getRgbFromItemStack(ItemStack stack) {
        return getRgbFromNbt(stack.getOrCreateTag());
    }

    /**
     * Extracts "packed" RGB data from a {@code CompoundTag}.
     * @param tag the NBT tag
     * @return an <i>optional</i> integer containing the "packed" RGB color data.
     */
    static Optional<Integer> getRgbFromNbt(CompoundTag tag) {
        int color = Colors.WHITE;
        if (tag.contains("color", NbtType.INT)) {
            color = tag.getInt("color");
        }
        else if (tag.getCompound("display").contains("color", NbtType.INT)) {
            // vanilla compat, for leather armors, ...
            color = tag.getCompound("display").getInt("color");
        }
        else if (tag.contains("BlockEntityTag", NbtType.COMPOUND) &&
                tag.getCompound("BlockEntityTag").contains("color",  NbtType.INT)) {
            // block entities
            color = tag.getCompound("BlockEntityTag").getInt("color");
        }
        else if (tag.contains("color", NbtType.STRING)) {
            try {
                color = Integer.parseInt(tag.getString("color"));
            }
            catch (NumberFormatException ignored) {}
        }
        else {
            return Optional.empty();
        }
        return Optional.of(color);
    }

    /**
     * Extracts color data from an {@code ItemStack}.
     * @param stack the item stack
     * @return an <i>optional</i> Color3f object containing color data.
     */
    static Optional<Color3f> getColorFromItemStack(ItemStack stack) {
        return getRgbFromItemStack(stack).map(Color3f::fromRgb);
    }

    /**
     * Extracts color data from a {@code CompoundTag}.
     * @param tag the NBT tag
     * @return an <i>optional</i> Color3f object containing color data.
     */
    static Optional<Color3f> getColorFromNbt(CompoundTag tag) {
        return getRgbFromNbt(tag).map(Color3f::fromRgb);
    }
}
