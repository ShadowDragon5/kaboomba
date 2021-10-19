package com.core;

import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Position;
import com.utils.TextureLoader;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class GameRenderer {
    public GameMap map;

    public void setMap(GameMap map) {
        this.map = map;
    }

    public void drawTexturedElements(List<? extends GameObject> objects) {
        objects.forEach(it-> {
            int textureId = TextureLoader.getTexture(it);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            DrawTexturedQuad(it.getPosition(), it.getDimensions(), it.getDimensions(), textureId);
            glDisable(GL_BLEND);
        });
    }

    public void renderMap() {
        map.getGameObjects().forEach(it-> {
            int textureId = TextureLoader.getTexture(it);
            DrawTexturedQuad(
                    it.getPosition(),
                    it.getDimensions(),
                    it.getDimensions(),
                    textureId
            );
        });
    }

    public void DrawQuad(Position position, float width, float height, Color color) {
        float x = position.getX();
        float y = position.getY();

        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1f);

        glBegin(GL_QUADS);

        glVertex2f(x - width / 2, y - height / 2);
        glVertex2f(x + width / 2, y - height / 2);

        glVertex2f(x + width / 2, y + height / 2);
        glVertex2f(x - width / 2, y + height / 2);

        glEnd();
    }

    public void DrawTexturedQuad(Position position, float width, float height, int textureId) {
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

    public void DrawTriangle(Position position, float size, Color color) {
        float x = position.getX();
        float y = position.getY();

        GL11.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);

        glBegin(GL_POLYGON);

        glVertex2f(x, y+size/2);

        glVertex2f(x+size/2, y-size/2);
        glVertex2f(x-size/2, y-size/2);

        glEnd();
    }

    public void DrawCircle(Position position, float radius, Color color) {
        GL11.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);

        glBegin(GL_POLYGON);

        double ori_x = position.getX();
        double ori_y = position.getY();
        float r = radius / 2;
        for (int i = 0; i <= 300; i++) {
            double angle = 2 * PI * i / 300;
            double x = cos(angle) * r;
            double y = sin(angle) * r;
            glVertex2d(ori_x + x, ori_y + y);
        }
        glEnd();
    }

    public void render(long window) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        if(map != null){
            renderMap();
        }

        State state = State.getInstance();
        if(state != null) {
            drawTexturedElements(state.getBombs());
            //Render bomb explosions
            drawTexturedElements(state.getShields());
            drawTexturedElements(state.getPits());
            drawTexturedElements(state.getBoxes());
            drawTexturedElements(state.getPowerups());
            drawTexturedElements(state.getExplosions());
            drawTexturedElements(state.getPlayers());
        }

        glfwSwapBuffers(window); // swap the color buffers
    }
}
