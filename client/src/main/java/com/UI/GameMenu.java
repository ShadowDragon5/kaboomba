package com.UI;

import java.awt.Color;

import com.core.State;
import com.entities.Rectangle;
import com.entities.players.*;

public class GameMenu implements UIComponent {
    private UIComponent menu;
    private Text name;
    private Text score;
    private String playerId;

    public GameMenu(Rectangle rectangle) {
        menu = new Menu(rectangle);
        Rectangle content = rectangle.clone();

        // left-right margin
        content.addX(5);
        content.setWidth(rectangle.getWidth() - 10);

        content.addY(2);
        content.setHeight(32);
        name = new Text("Hello!", content.clone(), Color.decode("#e9bd98"));
        menu.add(name);

        // content.addY(30);
        // content.setHeight(18);
        // score = new Text("", content.clone());
        // menu.add(score);

        // content.addY(30);
        // content.setHeight(24);
        // menu.add(new Button("press me", content.clone()));

        content.addY(50);
        content.setHeight(100);
        menu.add(new PlayerList(content.clone()));

    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

	@Override
	public void render() {
        Player player = State.getInstance().getPlayer(playerId);
        name.setValue(player instanceof NullPlayer ? "DEAD" : player.getName());
        // score.setValue(player instanceof NullPlayer ? "" : "Score: " + player.getScore());

        menu.render();
	}
}
