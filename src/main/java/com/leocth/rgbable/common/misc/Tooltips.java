package com.leocth.rgbable.common.misc;

import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.cca.ColorComponent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Tooltips {
    public static void appendColorTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        ColorComponent itemComponent = ColorComponent.get(stack);
        Color color = itemComponent.getColor();
        if (color != null) {
            int rgb = color.toRgb();
            int r = rgb >> 16 & 255;
            int g = rgb >> 8  & 255;
            int b = rgb       & 255;
            tooltip.add(new TranslatableText("tooltip.rgbable.rgb", r, g, b));
            tooltip.add(new TranslatableText("tooltip.rgbable.hex", String.format("%06X", rgb)));
        }
    }
}
