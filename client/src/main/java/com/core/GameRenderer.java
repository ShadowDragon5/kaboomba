package com.core;

import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.players.*;
import com.utils.TextureLoader;

import com.utils.Texture;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameRenderer {
    private String playerId;
    private GameMap map;

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    private void drawTexturedElements(List<? extends GameObject> objects) {
        objects.forEach(it-> {
            Texture texture = TextureLoader.getTexture(it);
            texture.draw(it.getRectangle());
        });
    }

    private void drawPlayersLives(List<? extends Player> players) {
        players.forEach(this::drawPlayerLives);
    }

    private void drawPlayerLives(Player player) {
        var n = Math.min(8, player.getHealth());
        drawPlayerLivesLine(n, player.getRectangle(), 0.1f);
        drawPlayerLivesLine(player.getHealth() - n, player.getRectangle(), 0.15f);
    }

    private void drawPlayerLivesLine(int heartCount, Rectangle position, float height) {
        Texture heart = TextureLoader.getTexture("src/main/resources/heart.png");
        for (int i = 0; i < heartCount; i++) {
            Rectangle heartRectangle = position.clone();
            heartRectangle.addY(height);
            heartRectangle.addX(0.05f * (i - heartCount / 2f + 0.5f));
            // TODO fix width/height
            heart.draw(heartRectangle);
        }
    }

    public void renderMap() {
        map.getGameObjects().forEach(it-> {
            Texture texture = TextureLoader.getTexture(it);
            texture.draw(it.getRectangle());
        });
    }

    public void render(long window) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        if (map != null) {
            renderMap();
        }

        State state = State.getInstance();
        Player player = state.getPlayer(playerId);
        String title = player instanceof NullPlayer ?
            "💀 DEAD 💀" : player.getName() + ": " + player.getScore();
        glfwSetWindowTitle(window, "KABOOMBA! " + title);

        drawTexturedElements(state.getPortals());
        drawTexturedElements(state.getBombs());
        drawTexturedElements(state.getPits());
        drawTexturedElements(state.getBoxes());
        drawTexturedElements(state.getPowerups());
        drawTexturedElements(state.getExplosions());
        drawTexturedElements(state.getShields());
        drawTexturedElements(state.getBosses());
        drawPlayersLives(state.getPlayers());
        drawPlayersLives(state.getBosses());
        drawTexturedElements(state.getPlayers());

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 320, 320, 0, -1, 1);

        glfwSwapBuffers(window); // swap the color buffers
    }
}
