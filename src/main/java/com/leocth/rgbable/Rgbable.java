package com.leocth.rgbable;

import com.leocth.rgbable.api.NetworkUtilities;
import com.leocth.rgbable.api.RgbableBlockItem;
import com.leocth.rgbable.common.RgbBlock;
import com.leocth.rgbable.common.RgbBlockEntity;
import com.leocth.rgbable.common.RgbBlockScreenHandler;
import com.leocth.rgbable.common.RgbableItemGroup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Rgbable implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("RGBable");

    public static final String MODID = "rgbable";
    public static final Identifier RGB_BLOCK_ID;
    public static final RgbBlock RGB_BLOCK;
    public static final RgbableBlockItem RGB_BLOCK_ITEM;
    public static final BlockEntityType<RgbBlockEntity> RGB_BLOCK_TYPE;
    public static final ScreenHandlerType<RgbBlockScreenHandler> RGB_BLOCK_SH;
    public static final ItemGroup ITEM_GROUP;



    @Override
    public void onInitialize() {
        LOGGER.info("RGBable loaded! Haha colored blocks go brrrrrrrrrrrrrrrr");
        ServerSidePacketRegistry.INSTANCE.register(NetworkUtilities.C2S_RGB_SYNC, new NetworkUtilities.RgbSyncScreenHandlerPacketConsumer());
        Registry.register(Registry.BLOCK, RGB_BLOCK_ID, RGB_BLOCK);
        Registry.register(Registry.ITEM, RGB_BLOCK_ID, RGB_BLOCK_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, RGB_BLOCK_ID, RGB_BLOCK_TYPE);
    }

    static {
        RGB_BLOCK_ID = new Identifier(MODID, "rgb_block");
        RGB_BLOCK = new RgbBlock();
        RGB_BLOCK_ITEM = new RgbableBlockItem(RGB_BLOCK, new Item.Settings());
        ITEM_GROUP = RgbableItemGroup.make(MODID);
        RGB_BLOCK_TYPE = BlockEntityType.Builder.create(RgbBlockEntity::new, RGB_BLOCK).build(null);
        RGB_BLOCK_SH = ScreenHandlerRegistry.registerSimple(RGB_BLOCK_ID, (syncid, ignored) -> new RgbBlockScreenHandler(syncid));
    }


}
