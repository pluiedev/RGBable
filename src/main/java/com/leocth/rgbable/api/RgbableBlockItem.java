package com.leocth.rgbable.api;

import com.leocth.rgbable.api.color.ColorRepresentable;
import com.leocth.rgbable.api.color.HsvColor3f;
import com.leocth.rgbable.api.color.RgbColor3f;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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
import java.util.Optional;

/**
 * A block item that is capable of containing a colorable block.
 * @author leocth
 */
@Deprecated
public class RgbableBlockItem extends BlockItem implements ColorSerializable {

    public RgbableBlockItem(Block block, Settings settings) {
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
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof Colorable) {
            Colorable colorable = ((Colorable) blockEntity);
            Optional<ColorRepresentable> color = getColorFromStack(stack);
            colorable.setRgb(color.map(ColorRepresentable::toPackedRgb).orElse(Colors.WHITE));
        }
        return super.postPlacement(pos, world, player, stack, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        Optional<ColorRepresentable> optionalRgb = getColorFromStack(stack);
        if (optionalRgb.isPresent()) {
            int rgb = optionalRgb.map(ColorRepresentable::toPackedRgb).get();
            HsvColor3f hsv = Colors.toHsv(RgbColor3f.fromRgb(rgb));
            int r = rgb >> 16 & 255;
            int g = rgb >> 8  & 255;
            int b = rgb       & 255;
            tooltip.add(new TranslatableText("tooltip.rgbable.rgb", r, g, b));
            tooltip.add(new TranslatableText("tooltip.rgbable.hex", String.format("%06X", rgb)));
            tooltip.add(hsv.asText());
        }
    }
}
