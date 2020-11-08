package com.leocth.rgbable.common.block;

import com.leocth.rgbable.Rgbable;
import com.leocth.rgbable.api.RgbableBlockEntity;
import com.leocth.rgbable.common.screen.RgbBlockScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class RgbBlockEntity extends RgbableBlockEntity implements NamedScreenHandlerFactory {

    public PropertyDelegate delegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return getRgb();
        }

        @Override
        public void set(int index, int value) { setRgb(value); }

        @Override
        public int size() {
            return 1;
        }
    };

    public RgbBlockEntity() {
        super(null/*Rgbable.RGB_BLOCK_TYPE*/);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new RgbBlockScreenHandler(syncId, delegate, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("gui.rgbable.rgb_block");
    }
}
