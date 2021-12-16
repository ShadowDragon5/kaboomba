package com.UI;

import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import com.entities.Rectangle;
import com.utils.Sprite;
import com.utils.TextureLoader;

public class Text implements UIComponent {
    private String value;
    private Sprite texture;
    private Rectangle rectangle;
    private Color color;

    public Text(String value, Rectangle rectangle) {
        this(value, rectangle, Color.WHITE);
    }

    public Text(String value, Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.value = value;
        this.texture = null;
        this.color = color;
    }

	public void setValue(String value) {
		this.value = value;
	}

	@Override
    public void render() {
        // not confirmed if has any performance impact
        // if (texture == null) {
            try {
                var f = Font.createFont(TRUETYPE_FONT, new File("src/main/resources/fonts/ponde___.ttf"));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(f);
                var font = new Font(f.getName(), PLAIN, 32);
                BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = image.createGraphics();
                // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // for antialiasing
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics();
                g.dispose();

                /* Get char charWidth and charHeight */
                // int charWidth = metrics.charWidth('a');
                int charHeight = (int)Math.ceil(metrics.getHeight() * 1.5);

                /* Create image for holding the char */
                // image = new BufferedImage(charWidth * value.length(), charHeight, BufferedImage.TYPE_INT_ARGB);
                image = new BufferedImage((int)(rectangle.getWidth() * charHeight / rectangle.getHeight()), charHeight, BufferedImage.TYPE_INT_ARGB);
                g = image.createGraphics();

                // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // for antialiasing
                g.setFont(font);
                g.setColor(color);
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

