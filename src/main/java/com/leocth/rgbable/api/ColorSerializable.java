package com.leocth.rgbable.api;

import com.leocth.rgbable.api.color.RgbColor3f;
import com.leocth.rgbable.api.color.ColorRepresentable;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

/**
 * Interface for serializing color data. Override these methods to write custom data to the tag.
 * (I dunno, HSV anyone?) (v2 edit: bruh)
 * @author leocth
 */
@Deprecated
public interface ColorSerializable {

    /**
     * Extracts "packed" RGB data from a {@code CompoundTag}.<br/>
     * Supported formats: (note: ? means the root tag)
     * <table border="1">
     * <tr><td>?.color                  </td><td>(Int/String*)  </td></tr>
     * <tr><td>?.display.color          </td><td>(Int)          </td></tr>
     * <tr><td>?.BlockEntityTag.color   </td><td>(Int/String*)  </td></tr>
     * </table>
     * *The string could be formatted as:<br/>
     * - an integer (e.g. 16777215)
     * - a case insensitive hex string, preceded by '#' (e.g. #ff0000)
     * @param tag the NBT tag
     * @return an <i>optional</i> integer containing the "packed" RGB color data.
     */
    default Optional<ColorRepresentable> getColorFromTag(CompoundTag tag) {
        int color = Colors.WHITE;
        if (tag.contains("color")) {
            try {
                return Colors.parseOmniaTag(tag, "color");
            }
            catch (Exception e) { e.printStackTrace(); }
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
        return Optional.of(RgbColor3f.fromRgb(color));
    }

    /**
     * Extracts "packed" RGB data from an {@code ItemStack}.
     * @param stack the item stack
     * @return an <i>optional</i> integer containing the "packed" RGB color data.
     */
    default Optional<ColorRepresentable> getColorFromStack(ItemStack stack) {
        return this.getColorFromTag(stack.getOrCreateTag());
    }

    default CompoundTag setColorToTag(CompoundTag tag, ColorRepresentable color) {
        tag.putInt("color", color.toPackedRgb());
        return tag;
    }
    default ItemStack setColorToStack(ItemStack stack, ColorRepresentable color) {
        this.setColorToTag(stack.getOrCreateTag(), color);
        return stack;
    }
}
