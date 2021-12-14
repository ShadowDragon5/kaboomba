package com.core;

import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.Rectangle;
import com.entities.players.Player;
import com.utils.TextureLoader;

import com.utils.Texture;

import java.util.List;

public class GameRenderer {
    private GameMap map;

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
        drawPlayerLivesLine(n, player.getRectangle(), -8);
        drawPlayerLivesLine(player.getHealth() - n, player.getRectangle(), -16);
    }

    private void drawPlayerLivesLine(int heartCount, Rectangle position, float height) {
        Texture heart = TextureLoader.getTexture(TextureFile.HEART);
        for (int i = 1; i <= heartCount; i++) {
            Rectangle heartRectangle = position.clone();
            heartRectangle.addY(height);
            heartRectangle.addX(8 * (i - heartCount / 2f) - 1);
            heartRectangle.setWidth(8);
            heartRectangle.setHeight(8);
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
        if (map != null) {
            renderMap();
        }

        State state = State.getInstance();
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
    }
}
