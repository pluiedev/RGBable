package com.leocth.rgbable.common.block;

import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.cca.ColorComponent;
import com.leocth.rgbable.api.v2.cca.ColorComponents;
import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RgbBlockItem extends BlockItem {

    public RgbBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    /**
     * Overriding {@code postPlacement} to add custom color data.
     *
     * @param pos the pos of the placed block
     * @param world the world that contains the placed block
     * @param player the player that is placing the block
     * @param stack the item stack used to place the block
     * @param state the block state of the placed block
     * @return mark dirty flag, I guess?
     */
    @Override
    protected boolean postPlacement(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        ColorComponent targetBlockComponent = BlockComponents.get(ColorComponents.COLOR, state, world, pos);
        ColorComponent itemComponent = ColorComponent.get(stack);
        if (targetBlockComponent != null) {
            targetBlockComponent.setColor(itemComponent.getColor());
            world.updateListeners(pos, state, state, 3); // wakey wakey
            return true;
        }
        return super.postPlacement(pos, world, player, stack, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
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
