package com.UI;

import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.entities.Rectangle;
import com.utils.Sprite;
import com.utils.TextureLoader;

public class Text implements UIComponent {
    private String value;
    private Sprite texture;
    Rectangle rectangle;

    public Text(String value, Rectangle rectangle) {
        this.rectangle = rectangle;
        this.value = value;
        this.texture = null;
    }

	public void setValue(String value) {
		this.value = value;
	}

	@Override
    public void render() {
        // not confirmed if has any performance impact
        // if (texture == null) {
            try {
                // var font = java.awt.Font.createFont(TRUETYPE_FONT, new FileInputStream("src/main/resources/fonts/BlueScreenPersonalUseRegular-0W1M9.ttf"));
                var font = new java.awt.Font(MONOSPACED, PLAIN, 32);
                BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = image.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // for antialiasing
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics();
                g.dispose();

                /* Get char charWidth and charHeight */
                // int charWidth = metrics.charWidth('a');
                int charHeight = metrics.getHeight();

                /* Create image for holding the char */
                // image = new BufferedImage(charWidth * value.length(), charHeight, BufferedImage.TYPE_INT_ARGB);
                image = new BufferedImage((int)(rectangle.getWidth() * charHeight / rectangle.getHeight()), charHeight, BufferedImage.TYPE_INT_ARGB);
                g = image.createGraphics();

                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // for antialiasing
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString(value, 0, metrics.getAscent());
                g.dispose();

                texture = new Sprite(TextureLoader.loadTexture(image));
                texture.draw(rectangle);

            } catch(Exception e) {
                System.out.println("UI: " + e.getMessage());
            }
        // }
        // else {
        // }
    }
}

