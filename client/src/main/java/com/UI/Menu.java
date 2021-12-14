package com.UI;

import java.util.ArrayList;
import java.util.List;

import com.core.TextureFile;
import com.core.enums.Direction;
import com.entities.Rectangle;
import com.utils.TextureLoader;

public class Menu implements UIComponent {
    private List<UIComponent> elements = new ArrayList<>();
    Rectangle rectangle;

    public Menu(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

	@Override
	public void render() {
        renderBackground();

        for (var e : elements) {
            e.render();
        }
	}

    private void renderBackground() {
        int step = 8 * 2;
        var rect = rectangle.clone();
        rect.setWidth(step);
        rect.setHeight(step);

        TextureLoader.getTexture(TextureFile.MENU_TOP_LEFT).draw(rect);
        for (rect.addX(step); rect.getX() < rectangle.getSide(Direction.RIGHT) - step; rect.addX(step)) {
            TextureLoader.getTexture(TextureFile.MENU_TOP_MIDDLE).draw(rect);
        }
        rect.setX(rectangle.getSide(Direction.RIGHT) - step);
        TextureLoader.getTexture(TextureFile.MENU_TOP_RIGHT).draw(rect);
        rect.setX(rectangle.getX());

        for (rect.addY(step); rect.getY() < rectangle.getSide(Direction.DOWN) - step; rect.addY(step)) {
            TextureLoader.getTexture(TextureFile.MENU_MIDDLE_LEFT).draw(rect);
            for (rect.addX(step); rect.getX() < rectangle.getSide(Direction.RIGHT) - step; rect.addX(step)) {
                TextureLoader.getTexture(TextureFile.MENU_MIDDLE).draw(rect);
            }
            rect.setX(rectangle.getSide(Direction.RIGHT) - step);
            TextureLoader.getTexture(TextureFile.MENU_MIDDLE_RIGHT).draw(rect);
            rect.setX(rectangle.getX());
        }

        TextureLoader.getTexture(TextureFile.MENU_BOTTOM_LEFT).draw(rect);
        for (rect.addX(step); rect.getX() < rectangle.getSide(Direction.RIGHT) - step; rect.addX(step)) {
            TextureLoader.getTexture(TextureFile.MENU_BOTTOM_MIDDLE).draw(rect);
        }
        rect.setX(rectangle.getSide(Direction.RIGHT) - step);
        TextureLoader.getTexture(TextureFile.MENU_BOTTOM_RIGHT).draw(rect);
    }

    @Override
    public void add(UIComponent component) {
        elements.add(component);
    }
}

