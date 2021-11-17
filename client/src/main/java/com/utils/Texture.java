package com.utils;

import com.entities.Position;

import static org.lwjgl.opengl.GL11.*;


public class Texture implements Drawable {
    private int textureId;

    public Texture(int textureId) {
        this.textureId = textureId;
    }

    @Override
    public void draw(Position position, float width, float height) {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        DrawTexturedQuad(position, width, height);
        glDisable(GL_BLEND);
    }

    private void DrawTexturedQuad(Position position, float width, float height) {
        float x = position.getX();
        float y = position.getY();

        glColor4f(1f, 1f, 1f, 1f);

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0); // top left
        glVertex2f(x - width / 2, y + height / 2);

        glTexCoord2f(0, 1); // bottom left
        glVertex2f(x - width / 2, y - height / 2);

        glTexCoord2f(1, 1); // bottom right
        glVertex2f(x + width / 2, y - height / 2);

        glTexCoord2f(1, 0); // top right
        glVertex2f(x + width / 2, y + height / 2);

        glDisable(GL_TEXTURE_2D);
        glDeleteTextures(textureId);
        glEnd();
    }
}
