package com.entities;

public class InitialPlayerConnection {
    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public InitialPlayerConnection(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
