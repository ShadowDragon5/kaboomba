package com.core;

import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.Player;
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

    private void drawTexturedElements(List<? extends GameObject> objects) {
        objects.forEach(it-> {
            int textureId = TextureLoader.getTexture(it);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            DrawTexturedQuad(it.getPosition(), it.getDimensions(), it.getDimensions(), textureId);
            glDisable(GL_BLEND);
        });
    }

    private void drawPlayersLives(List<Player> players) {
        players.forEach(this::drawPlayerLives);
    }

    private void drawPlayerLives(Player player) {
        int textureId = TextureLoader.getTexture("src/main/resources/heart.png");
        for (int i = 0; i < player.getHealth(); i++) {
            Position heartPosition = player.getPosition().clone();
            heartPosition.addY(0.1f);
            heartPosition.addX(0.05f * (i - player.getHealth() / 2f + 0.5f));
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            DrawTexturedQuad(heartPosition, 0.05f, 0.05f, textureId);
            glDisable(GL_BLEND);
        }
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

    private void DrawTexturedQuad(Position position, float width, float height, int textureId) {
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
            drawPlayersLives(state.getPlayers());
            drawTexturedElements(state.getPlayers());
        }

        glfwSwapBuffers(window); // swap the color buffers
    }
}
