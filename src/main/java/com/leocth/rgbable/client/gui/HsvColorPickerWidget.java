package com.leocth.rgbable.client.gui;

import com.leocth.rgbable.api.Colors;
import com.leocth.rgbable.api.color.HsvColor3f;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;

public class HsvColorPickerWidget extends DrawableHelper implements Drawable, Element {

    private final HsvColor3f.Mutable hsv;
    private int mainSize;
    private int hsX1;
    private int hsX2;
    private int x, y;

    public HsvColorPickerWidget(int x, int y, int mainSize, int hsGap, int hsWidth) {
        this.x = x;
        this.y = y;
        this.mainSize = mainSize;
        this.hsX1 = mainSize+hsGap;
        this.hsX2 = mainSize+hsGap+hsWidth;
        this.hsv = new HsvColor3f.Mutable(0f, 0f, 0f, false);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        matrices.translate(x, y, 0);
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.disableAlphaTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(GL11.GL_SMOOTH);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
        Matrix4f matrix = matrices.peek().getModel();
        int z = getZOffset();
        int rgb = MathHelper.hsvToRgb(hsv.getHue(), 1.0f, 1.0f);
        DrawableHelper.fillGradient(matrix, bufferBuilder, 0, 0, mainSize, mainSize, z, Colors.WHITE, rgb);
        DrawableHelper.fillGradient(matrix, bufferBuilder, 0, mainSize, mainSize, z, 0, Colors.BLACK, Colors.WHITE);
        tessellator.draw();

        RenderSystem.shadeModel(GL11.GL_FLAT);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableTexture();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 0) {
            float curX = (float) (mouseX + deltaX - x);
            float curY = (float) (mouseY + deltaY - y);
            if (curX >= 0 && curX <= mainSize) {
                hsv.setSaturation(curX / mainSize);
                hsv.setValue(curY / mainSize);
                return true;
            }
            if (curX >= hsX1 && curX <= hsX2) {
                hsv.setHue(curY / mainSize, false);
                return true;
            }
        }
        return false;
    }

    public HsvColor3f getHsv() { return new HsvColor3f(hsv); }

}
