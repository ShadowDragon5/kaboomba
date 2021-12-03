package com.UI;

import com.entities.Rectangle;

public interface UIComponent {
    void render(Rectangle rectangle);
    void add(UIComponent component);
}

