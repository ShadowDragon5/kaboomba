package com.core;

import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Position;
import com.entities.players.*;
import com.utils.TextureLoader;
import com.utils.Drawable;

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
            Drawable texture = TextureLoader.getTexture(it);
            texture.draw(it.getPosition(), it.getDimensions(), it.getDimensions());
        });
    }

    private void drawPlayersLives(List<Player> players) {
        players.forEach(this::drawPlayerLives);
    }

    private void drawPlayerLives(Player player) {
        Drawable heart = TextureLoader.getTexture("src/main/resources/heart.png");
        for (int i = 0; i < player.getHealth(); i++) {
            Position heartPosition = player.getPosition().clone();
            heartPosition.addY(0.1f);
            heartPosition.addX(0.05f * (i - player.getHealth() / 2f + 0.5f));
            heart.draw(heartPosition, 0.05f, 0.05f);
        }
    }

    public void renderMap() {
        map.getGameObjects().forEach(it-> {
            Drawable texture = TextureLoader.getTexture(it);
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
        drawTexturedElements(state.getShields());
        drawTexturedElements(state.getPits());
        drawTexturedElements(state.getBoxes());
        drawTexturedElements(state.getPowerups());
        drawTexturedElements(state.getExplosions());
        drawPlayersLives(state.getPlayers());
        drawTexturedElements(state.getPlayers());

        glfwSwapBuffers(window); // swap the color buffers
    }
}
