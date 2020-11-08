package com.leocth.rgbable.common.screen;

import com.leocth.rgbable.Rgbable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;

public class PaintbrushScreenHandler extends ScreenHandler /*implements ColorSerializable*/ {

    private PropertyDelegate delegate;
    private PlayerEntity player;

    public PaintbrushScreenHandler(int syncId, PropertyDelegate delegate, PlayerEntity player) {
        super(null/*Rgbable.RGB_BLOCK_SH*/, syncId);
        checkDataCount(delegate, 1);
        this.delegate = delegate;
        this.player = player;
        this.addProperties(delegate);
    }

    @Environment(EnvType.CLIENT)
    public PaintbrushScreenHandler(int syncId, PlayerEntity player) {
        this(syncId, new ArrayPropertyDelegate(1), player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return player.getMainHandStack().getItem() == Rgbable.PAINTBRUSH_ITEM;
    }

    @Environment(EnvType.CLIENT)
    public int getRgb() { return delegate.get(0); }
    public void setRgb(int rgb) { delegate.set(0, rgb); }

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();
        //this.setColorToStack(player.getMainHandStack(), RgbColor3f.fromRgb(delegate.get(0)));
    }
}
