package com.UI;

import com.entities.Rectangle;

public class Button implements UIComponent {
    UIComponent button;
    Rectangle rectangle;

    public Button(String title, Rectangle rectangle) {
        this.rectangle = rectangle;
        button = new Menu(rectangle);

        Rectangle content = rectangle.clone();
        // left-right margin
        content.addX(5);
        content.setWidth(rectangle.getWidth() - 10);
        button.add(new Text(title, content));
    }

	@Override
	public void render() {
        button.render();
        checkClicked();
	}

    private void checkClicked() {
    }
}
