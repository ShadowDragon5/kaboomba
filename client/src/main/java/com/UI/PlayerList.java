package com.UI;

import java.awt.Color;

import com.core.State;
import com.entities.Rectangle;

public class PlayerList implements UIComponent {
    Rectangle rectangle;
    Menu menu;
    Text list[] = new Text[4];

    public PlayerList(Rectangle rectangle) {
        this.rectangle = rectangle;
        menu = new Menu(rectangle);
        Rectangle content = rectangle.clone();
        // left-right margin
        content.addX(5);
        content.setWidth(rectangle.getWidth() - 10);

        content.setHeight(28);
        menu.add(new Text("Score", content.clone(), Color.ORANGE));
        content.addY(30);

        for (int i = 0; i < 4; i++) {
            content.setHeight(18);
            list[i] = new Text("", content.clone());
            menu.add(list[i]);
            content.addY(20);
        }
    }

	@Override
	public void render() {
        var playerList = State.getInstance().getPlayers();
        playerList.sort((o1, o2) -> o2.getScore() - o1.getScore());
        for (int i = 0; i < 4; i++) {
            if (i < playerList.size() && playerList.get(i) != null) {
                var player = playerList.get(i);
                list[i].setValue(player.getName() + ":" + player.getScore());
            }
        }
        menu.render();
	}
}
