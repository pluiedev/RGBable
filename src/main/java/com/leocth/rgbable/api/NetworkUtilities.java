package com.leocth.rgbable.api;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;

/**
 * Useful networking stuff.
 * @author leocth
 */
public class NetworkUtilities {
    public static final Identifier C2S_RGB_SYNC = new Identifier("rgbable:c2s/rgb_sync");

    /**
     * Sends a C2S packet to request a RGB data sync to the server-side {@code ScreenHandler}.
     * Useful for syncing color data when changing the color values in the client via a {@code Screen}.
     *
     * @param syncid the syncid of the {@code ScreenHandler}.
     * @param rgb    the "packed" RGB integer.
     * @param index  the index of the {@code Property} in {@code ScreenHandler.properties},
     *               usually corresponds to the nth property in the {@code PropertyDelegate}(s).
     */
    @Environment(EnvType.CLIENT)
    public static void sendRgbSyncScreenHandlerPacket(int syncid, int rgb, int index) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeVarInt(syncid);
        buf.writeVarInt(rgb);
        buf.writeVarInt(index);

        ClientSidePacketRegistry.INSTANCE.sendToServer(C2S_RGB_SYNC, buf);
    }

    /**
     * Packet consumer for {@code NetworkUtilities#sendRgbSyncScreenHandlerPacket}.
     * @see NetworkUtilities#sendRgbSyncScreenHandlerPacket
     */
    public static class RgbSyncScreenHandlerPacketConsumer implements PacketConsumer {
        @Override
        public void accept(PacketContext ctx, PacketByteBuf buf) {
            int syncid = buf.readVarInt();
            int rgb = buf.readVarInt();
            int index = buf.readVarInt();
            ctx.getTaskQueue().execute(() -> {
                if (ctx.getPlayer().currentScreenHandler.syncId == syncid) {
                    ScreenHandler screenHandler = ctx.getPlayer().currentScreenHandler;
                    screenHandler.setProperty(index, rgb);
                }
            });
        }
    }
}
