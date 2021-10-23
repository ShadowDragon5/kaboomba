package com.commands;

import java.util.Stack;

public class UndoCommand implements Command {
    private Stack<UndoableCommand> unduableCommands;
    public UndoCommand(Stack<UndoableCommand> unduableCommands) {
        this.unduableCommands = unduableCommands;
    }
    @Override
    public void execute() {
        if(!unduableCommands.isEmpty())
            unduableCommands.pop().undo();
    }
}
