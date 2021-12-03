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
    public void draw(Rectangle rectangle) {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        SolidFill(rectangle);
        glDisable(GL_BLEND);
    }

    private void SolidFill(Rectangle rectangle) {
        float x = rectangle.getX();
        float y = rectangle.getY();
        float width = rectangle.getWidth();
        float height = rectangle.getHeight();

        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1f);

        glBegin(GL_QUADS);

        glVertex2f(x - width / 2, y + height / 2);
        glVertex2f(x - width / 2, y - height / 2);
        glVertex2f(x + width / 2, y - height / 2);
        glVertex2f(x + width / 2, y + height / 2);

        glEnd();
    }

}
