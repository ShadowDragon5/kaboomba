package com.UI;

public interface UIComponent {
    void render();

    default void add(UIComponent component) {
        System.out.println("WARNING: Can't add component to " + this.getClass());
    }

    default void remove(UIComponent component) {
        System.out.println("WARNING: Can't remove component from " + this.getClass());
    }
}

