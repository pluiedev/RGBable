package com.leocth.rgbable.common.item;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.v2.cca.ColorComponent;
import com.leocth.rgbable.api.v2.cca.ColorComponents;
import com.leocth.rgbable.api.v2.colors.HsvColor3f;
import com.leocth.rgbable.common.misc.Tooltips;
import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PaintbrushItem extends Item /*implements NamedScreenHandlerFactory*/ {

    public PaintbrushItem() {
        super(new Settings().group(Rgbable.ITEM_GROUP).maxDamage(745));
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            BlockPos pos = context.getBlockPos();
            ItemStack stack = context.getStack();
            BlockState state = world.getBlockState(pos);
            PlayerEntity player = context.getPlayer();

            ColorComponent targetBlockComponent = BlockComponents.get(ColorComponents.COLOR, state, world, pos);
            ColorComponent itemComponent = ColorComponent.get(stack);
            itemComponent.setColor(new HsvColor3f(0.57f, 1.0f, 1.0f)); // debug
            if (targetBlockComponent != null) {
                targetBlockComponent.setColor(itemComponent.getColor());
                world.updateListeners(pos, state, state, 3); // wakey wakey
                if (player != null)
                    stack.damage(1,  player, (p) -> p.sendToolBreakStatus(context.getHand()));
                world.playSound(null, pos, SoundEvents.ENTITY_FISHING_BOBBER_SPLASH, SoundCategory.PLAYERS, 0.5f, 1.5f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        Tooltips.appendColorTooltip(stack, world, tooltip, context);
    }

    /*
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
     */
}
