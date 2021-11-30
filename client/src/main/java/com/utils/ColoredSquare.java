package com.utils;

import com.entities.Rectangle;
import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;


public class ColoredSquare implements Texture {

    private Color color;

    public ColoredSquare(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Rectangle position, float width, float height) {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        SolidFill(position, width, height, color);
        glDisable(GL_BLEND);
    }

    private void SolidFill(Rectangle position, float width, float height, Color textureId) {
        float x = position.getX();
        float y = position.getY();

        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1f);

        glBegin(GL_QUADS);

        glVertex2f(x - width / 2, y + height / 2);
        glVertex2f(x - width / 2, y - height / 2);
        glVertex2f(x + width / 2, y - height / 2);
        glVertex2f(x + width / 2, y + height / 2);

        glEnd();
    }

}
