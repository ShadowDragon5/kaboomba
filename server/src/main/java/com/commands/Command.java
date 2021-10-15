package com.commands;

public interface Command {
    public void execute();
    public default void undo () {
        System.out.println("Undo");
    }
}
