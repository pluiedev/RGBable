package com.leocth.rgbable;

import com.leocth.rgbable.api.NetworkUtilities;
import com.leocth.rgbable.api.v2.Color;
import com.leocth.rgbable.api.v2.ColorSerializer;
import com.leocth.rgbable.api.v2.colors.HsvColor3f;
import com.leocth.rgbable.api.v2.registry.ColorSerializerRegistry;
import com.leocth.rgbable.api.v2.colors.RgbColor3i;
import com.leocth.rgbable.common.RgbableItemGroup;
import com.leocth.rgbable.common.block.RgbBlock;
import com.leocth.rgbable.common.block.RgbBlockItem;
import com.leocth.rgbable.common.item.PaintbrushItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Rgbable implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("RGBable");

    public static final String MODID = "rgbable";
    public static final Identifier RGB_BLOCK_ID;
    public static final RgbBlock RGB_BLOCK;
    public static final BlockItem RGB_BLOCK_ITEM;
    //public static final BlockEntityType<RgbBlockEntity> RGB_BLOCK_TYPE;
    //public static final ScreenHandlerType<RgbBlockScreenHandler> RGB_BLOCK_SH;

    public static final Identifier PAINTBRUSH_ID;
    public static final PaintbrushItem PAINTBRUSH_ITEM;
    //public static final ScreenHandlerType<PaintbrushScreenHandler> PAINTBRUSH_SH;
    public static final ItemGroup ITEM_GROUP;

    @Override
    public void onInitialize() {
        LOGGER.info("RGBable loaded! Haha colored blocks go brrrrrrrrrrrrrrrr");
        NetworkUtilities.registerC2SPackets();
        Registry.register(Registry.BLOCK, RGB_BLOCK_ID, RGB_BLOCK);
        Registry.register(Registry.ITEM, RGB_BLOCK_ID, RGB_BLOCK_ITEM);
        //Registry.register(Registry.BLOCK_ENTITY_TYPE, RGB_BLOCK_ID, RGB_BLOCK_TYPE);

        Registry.register(Registry.ITEM, PAINTBRUSH_ID, PAINTBRUSH_ITEM);

        registerColorSerializer("rgbcolor3i", RgbColor3i.class, new RgbColor3i.Serializer());
        registerColorSerializer("hsvcolor3f", HsvColor3f.class, new HsvColor3f.Serializer());
    }

    static {
        RGB_BLOCK_ID = id("rgb_block");
        RGB_BLOCK = new RgbBlock();
        RGB_BLOCK_ITEM = new RgbBlockItem(RGB_BLOCK, new Item.Settings());
        ITEM_GROUP = RgbableItemGroup.make(MODID);
        //RGB_BLOCK_TYPE = BlockEntityType.Builder.create(RgbBlockEntity::new, RGB_BLOCK).build(null);
        //RGB_BLOCK_SH = ScreenHandlerRegistry.registerSimple(RGB_BLOCK_ID, (syncid, ignored) -> new RgbBlockScreenHandler(syncid));

        PAINTBRUSH_ID = new Identifier(MODID, "paintbrush");
        PAINTBRUSH_ITEM = new PaintbrushItem();
        //PAINTBRUSH_SH = ScreenHandlerRegistry.registerSimple(PAINTBRUSH_ID, (syncId, inventory) -> new PaintbrushScreenHandler(syncId, inventory.player));
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    private static <T extends Color> void registerColorSerializer(String strId, Class<T> colorClass, ColorSerializer<T> serializer) {
        ColorSerializerRegistry.INSTANCE.register(id(strId), colorClass, serializer);
    }
}
