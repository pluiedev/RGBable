package com.leocth.rgbable.common;

import com.google.common.collect.Lists;
import com.leocth.rgbable.Rgbable;
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class RgbableItemGroup extends ItemGroup {

    private ItemStack currentIcon = makeRandomColoredRgbBlockItemStack();
    // for performance. idk
    private List<ItemStack> cachedItems = Lists.newArrayList();
    private int tickTimer = 0;
    private final int timeToSwitch = 80;

    private RgbableItemGroup(int index, String id) {
        super(index, id);
        appendOrderedRgbBlockItems(cachedItems);
    }

    public static RgbableItemGroup make(String id) {
        // evil hax
        ((ItemGroupExtensions) ItemGroup.BUILDING_BLOCKS).fabric_expandArray();
        return new RgbableItemGroup(ItemGroup.GROUPS.length - 1, id);
    }

    @Override
    public void appendStacks(DefaultedList<ItemStack> stacks) {
        stacks.addAll(cachedItems);
    }

    @Override
    public ItemStack getIcon() {
        if (tickTimer >= timeToSwitch) {
            currentIcon = makeRandomColoredRgbBlockItemStack();
            tickTimer = 0;
        } else {
            ++tickTimer;
        }
        return currentIcon;
    }

    @Override public ItemStack createIcon() { return null; }

    /**
     * @param hue 0-1
     * @param saturation 0-1
     * @param value 0-1
     * @return a stack
     */
    public static ItemStack getHsvColoredRgbBlockItemStack(float hue, float saturation, float value) {
        ItemStack stack = new ItemStack(Rgbable.RGB_BLOCK_ITEM);
        int rgb = MathHelper.hsvToRgb(hue, saturation, value);
        CompoundTag tag = new CompoundTag();
        tag.putInt("color", rgb);
        stack.putSubTag("BlockEntityTag", tag);
        return stack;
    }

    public static ItemStack makeRandomColoredRgbBlockItemStack() {
        return getHsvColoredRgbBlockItemStack((float) Math.random(),1.0f, 1.0f);
    }

    public static void appendOrderedRgbBlockItems(List<ItemStack> stacks) {
        // colors
        for (int i = 0; i < 24; i++) { // hue
            for (int j = 0; j < 5; j++) { // saturation
                for (int k = 0; k < 5; k++) { // value
                    float hue = 1f / 24f * i;
                    float saturation = 1f / 5f * (j + 1);
                    float value = 1f / 5f * (k + 1);
                    stacks.add(getHsvColoredRgbBlockItemStack(hue, saturation, value));
                }
            }
        }
        // tints
        for (int i = 0; i < 24; i++) { // value
            float value = 1f / 24f * (i + 1);
            stacks.add(getHsvColoredRgbBlockItemStack(0f, 0f, value));
        }
    }
}
