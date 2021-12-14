package com.UI;

import java.awt.Color;

import com.core.State;
import com.entities.Rectangle;

public class PlayerList implements UIComponent {
    Rectangle rectangle;

    public PlayerList(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

	@Override
	public void render() {
        var menu = new Menu(rectangle);
        Rectangle content = rectangle.clone();
        // left-right margin
        content.addX(5);
        content.setWidth(rectangle.getWidth() - 10);

        content.setHeight(28);
        menu.add(new Text("Score", content.clone(), Color.ORANGE));
        content.addY(30);


        var playerList = State.getInstance().getPlayers();
        playerList.sort((o1, o2) -> o2.getScore() - o1.getScore());
        for (var player : playerList) {
            content.setHeight(18);
            menu.add(new Text(
                player.getName() + ":" + player.getScore(),
                content.clone()
            ));
            content.addY(20);
        }
        menu.render();
	}
}
