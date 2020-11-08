package com.leocth.rgbable.common.screen;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.common.block.RgbBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class RgbBlockScreenHandler extends ScreenHandler {

    private PropertyDelegate delegate;
    private ScreenHandlerContext context;

    public RgbBlockScreenHandler(int syncId, PropertyDelegate delegate, ScreenHandlerContext context) {
        super(null/*Rgbable.RGB_BLOCK_SH*/, syncId);
        checkDataCount(delegate, 1);
        this.delegate = delegate;
        this.context = context;

        this.addProperties(delegate);
    }

    public RgbBlockScreenHandler(int syncId) {
        this(syncId, new ArrayPropertyDelegate(1), ScreenHandlerContext.EMPTY);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, Rgbable.RGB_BLOCK);
    }

    @Environment(EnvType.CLIENT)
    public int getRgb() { return delegate.get(0); }
    public void setRgb(int rgb) { delegate.set(0, rgb); }

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();
        context.run((world, pos) -> {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof RgbBlockEntity) {
                ((RgbBlockEntity) be).sync();
            }
        });
    }
}
