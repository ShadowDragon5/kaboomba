package com.commands;

public interface UndoableCommand extends Command {
    public default void undo () {
        System.out.println("Undo");
    }
}
