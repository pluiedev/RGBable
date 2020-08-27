package com.leocth.rgbable.client;

import com.google.common.collect.Lists;
import com.leocth.rgbable.api.Color3f;
import com.leocth.rgbable.api.NetworkUtilities;
import com.leocth.rgbable.common.RgbBlockScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@Environment(EnvType.CLIENT)
public class RgbBlockScreen extends HandledScreen<RgbBlockScreenHandler> {

    public static final Identifier TEXTURE = new Identifier("rgbable:textures/gui/rgb_block.png");
    private final RgbBlockScreenHandler handler;
    private final List<SliderWidget> sliders = Lists.newArrayList();
    public Color3f.Mutable color;

    public RgbBlockScreen(RgbBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.handler = handler;
        this.backgroundWidth = 176;
        this.backgroundHeight = 91;
        color = Color3f.fromRgb(handler.getRgb()).toMutable();
    }

    @Override
    protected void init() {
        super.init();
        color = Color3f.fromRgb(handler.getRgb()).toMutable();

        int mid = width / 2;
        sliders.add(this.addButton(new SliderWidget(mid - 60, y + 15, 120, 20, LiteralText.EMPTY, color.getR()) {
            {
                updateMessage();
            }
            @Override
            protected void updateMessage() {
                setMessage(new TranslatableText(
                        "gui.rgbable.rgb_block.slider.r",
                        MathHelper.floor(color.getR() * 255f),
                        String.format("%.3f", color.getR())
                ));
            }

            @Override
            protected void applyValue() {
                RgbBlockScreen.this.color.setR((float)this.value);
                updateAndSyncValues();
            }
        }));
        sliders.add(this.addButton(new SliderWidget(mid - 60, y + 36, 120, 20, LiteralText.EMPTY, color.getG()) {
            {
                updateMessage();
            }
            @Override
            protected void updateMessage() {
                setMessage(new TranslatableText(
                        "gui.rgbable.rgb_block.slider.g",
                        MathHelper.floor(color.getG() * 255f),
                        String.format("%.3f", color.getG())
                ));
            }

            @Override
            protected void applyValue() {
                RgbBlockScreen.this.color.setG((float)this.value);
                updateAndSyncValues();
            }
        }));
        sliders.add(this.addButton(new SliderWidget(mid - 60, y + 57, 120, 20, LiteralText.EMPTY, color.getB()) {
            {
                updateMessage();
            }
            @Override
            protected void updateMessage() {
                setMessage(new TranslatableText(
                        "gui.rgbable.rgb_block.slider.b",
                        MathHelper.floor(color.getB() * 255f),
                        String.format("%.3f", color.getB())
                ));
            }

            @Override
            protected void applyValue() {
                RgbBlockScreen.this.color.setB((float)this.value);
                updateAndSyncValues();
            }
        }));
    }

    private void updateAndSyncValues() {
        int rgb = Color3f.toRgb(color);
        handler.setRgb(rgb);
        handler.sendContentUpdates();
        NetworkUtilities.sendRgbSyncScreenHandlerPacket(handler.syncId, rgb, 0);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.3F);
        assert this.client != null;
        this.client.getTextureManager().bindTexture(TEXTURE);
        this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, this.title, (float)this.titleX, (float)this.titleY, 0x404040);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        // functional programming hell yeah
        return sliders.stream().anyMatch(
                    widget -> widget.isMouseOver(mouseX, mouseY) &&
                    widget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
                ) || super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
