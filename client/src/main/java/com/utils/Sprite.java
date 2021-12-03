package com.utils;

import com.entities.Rectangle;

import static org.lwjgl.opengl.GL11.*;


public class Sprite implements Texture {
    private int textureId;

    public Sprite(int textureId) {
        this.textureId = textureId;
    }

    @Override
    public void draw(Rectangle rectangle) {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        DrawTexturedQuad(rectangle);
        glDisable(GL_BLEND);
    }

    private void DrawTexturedQuad(Rectangle rectangle) {
        float x = rectangle.getX();
        float y = rectangle.getY();
        float width = rectangle.getWidth();
        float height = rectangle.getHeight();

        glColor4f(1f, 1f, 1f, 1f);

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0); // top left
        glVertex2f(x, y);

        glTexCoord2f(0, 1); // bottom left
        glVertex2f(x, y + height);

        glTexCoord2f(1, 1); // bottom right
        glVertex2f(x + width, y + height);

        glTexCoord2f(1, 0); // top right
        glVertex2f(x + width, y);

        glDisable(GL_TEXTURE_2D);
        glDeleteTextures(textureId);
        glEnd();
    }
}
