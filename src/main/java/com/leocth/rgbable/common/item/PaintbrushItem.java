package com.leocth.rgbable.common.item;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.ColorSerializable;
import com.leocth.rgbable.api.RgbableBlockEntity;
import com.leocth.rgbable.api.color.ColorRepresentable;
import com.leocth.rgbable.api.color.RgbColor3f;
import com.leocth.rgbable.common.screen.PaintbrushScreenHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Optional;

public class PaintbrushItem extends Item implements ColorSerializable, NamedScreenHandlerFactory {

    public PaintbrushItem() {
        super(new Settings().group(Rgbable.ITEM_GROUP).maxDamage(745));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            BlockEntity be = context.getWorld().getBlockEntity(context.getBlockPos());
            if (be instanceof RgbableBlockEntity) {
                RgbableBlockEntity rbe = (RgbableBlockEntity) be;
                // #abusingfunctionalprogramming
                ColorRepresentable clr = rbe.getColor();
                Optional<ColorRepresentable> rgb = getColorFromStack(context.getStack());
                if (rgb.isPresent()) {
                    ColorRepresentable val = rgb.get();
                    if (!val.equals(clr)) {
                        rbe.setRgb(val.toPackedRgb());
                        rbe.sync();
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && user.isSneaking()) {
            user.openHandledScreen(this);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("gui.rgbable.paintbrush");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new PaintbrushScreenHandler(syncId, new PropertyDelegate() {
            @Override
            public int get(int index) {
                return getColorFromStack(player.getMainHandStack()).map(ColorRepresentable::toPackedRgb).orElse(0xffffff);
            }

            @Override
            public void set(int index, int value) {
                setColorToStack(player.getMainHandStack(), RgbColor3f.fromRgb(value));
            }

            @Override
            public int size() {
                return 1;
            }
        }, player);
    }
}
