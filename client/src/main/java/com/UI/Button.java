package com.UI;

import com.core.TextureFile;
import com.core.enums.Direction;
import com.entities.Rectangle;
import com.utils.TextureLoader;

public class Button implements UIComponent {
    UIComponent title;
    Rectangle rectangle;

    public Button(String title, Rectangle rectangle) {
        this.rectangle = rectangle;

        Rectangle content = rectangle.clone();
        // left-right margin
        content.addX(5);
        content.setWidth(rectangle.getWidth() - 10);
        this.title = new Text(title, content);
    }

	@Override
	public void render() {
        renderBackground();
        title.render();
        checkClicked();
	}

    private void checkClicked() {
    }

    private void renderBackground() {
        int step = 8 * 2;
        var rect = rectangle.clone();
        rect.setWidth(step);
        rect.setHeight(step);

        TextureLoader.getTexture(TextureFile.BUTTON_TOP_LEFT).draw(rect);
        for (rect.addX(step); rect.getX() < rectangle.getSide(Direction.RIGHT) - step; rect.addX(step)) {
            TextureLoader.getTexture(TextureFile.BUTTON_TOP_MIDDLE).draw(rect);
        }
        rect.setX(rectangle.getSide(Direction.RIGHT) - step);
        TextureLoader.getTexture(TextureFile.BUTTON_TOP_RIGHT).draw(rect);
        rect.setX(rectangle.getX());

        for (rect.addY(step); rect.getY() < rectangle.getSide(Direction.DOWN) - step; rect.addY(step)) {
            TextureLoader.getTexture(TextureFile.BUTTON_MIDDLE_LEFT).draw(rect);
            for (rect.addX(step); rect.getX() < rectangle.getSide(Direction.RIGHT) - step; rect.addX(step)) {
                TextureLoader.getTexture(TextureFile.BUTTON_MIDDLE).draw(rect);
            }
            rect.setX(rectangle.getSide(Direction.RIGHT) - step);
            TextureLoader.getTexture(TextureFile.BUTTON_MIDDLE_RIGHT).draw(rect);
            rect.setX(rectangle.getX());
        }

        TextureLoader.getTexture(TextureFile.BUTTON_BOTTOM_LEFT).draw(rect);
        for (rect.addX(step); rect.getX() < rectangle.getSide(Direction.RIGHT) - step; rect.addX(step)) {
            TextureLoader.getTexture(TextureFile.BUTTON_BOTTOM_MIDDLE).draw(rect);
        }
        rect.setX(rectangle.getSide(Direction.RIGHT) - step);
        TextureLoader.getTexture(TextureFile.BUTTON_BOTTOM_RIGHT).draw(rect);
    }
}
