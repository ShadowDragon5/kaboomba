package com.core;

import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.*;
import com.utils.TextureLoader;
import com.utils.Texture;

import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
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
            texture.draw(it.getPosition(), it.getDimensions(), it.getDimensions());
        });
    }

    private void drawPlayersLives(List<? extends Player> players) {
        players.forEach(this::drawPlayerLives);
    }

    private void drawPlayerLives(Player player) {
        var n = Math.min(8, player.getHealth());
        drawPlayerLivesLine(n, player.getPosition(), 0.1f);
        drawPlayerLivesLine(player.getHealth() - n, player.getPosition(), 0.15f);
    }

    private void drawPlayerLivesLine(int heartCount, Position position, float height) {
        Texture heart = TextureLoader.getTexture("src/main/resources/heart.png");
        for (int i = 0; i < heartCount; i++) {
            Position heartPosition = position.clone();
            heartPosition.addY(height);
            heartPosition.addX(0.05f * (i - heartCount / 2f + 0.5f));
            heart.draw(heartPosition, 0.05f, 0.05f);
        }
    }

    public void renderMap() {
        map.getGameObjects().forEach(it-> {
            Texture texture = TextureLoader.getTexture(it);
            texture.draw(it.getPosition(), it.getDimensions(), it.getDimensions());
        });
    }

    public void render(long window) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        if (map != null) {
            renderMap();
        }

        State state = State.getInstance();
        Player player = state.getPlayer(playerId);
        String title = player instanceof NullPlayer ? "💀 DEAD 💀" : player.getName() + ": " + player.getScore();
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

        glfwSwapBuffers(window); // swap the color buffers
    }
}
