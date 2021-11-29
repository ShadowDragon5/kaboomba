package com.core;

import com.commands.*;
import com.core.enums.ClientAction;
import com.entities.players.Player;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class CommandAggregator implements ICommandAggregator {
    private final Deque<State> stateSaves = new ArrayDeque<>();
    private final Stack<UndoableCommand> undoableCommands;
    private final Queue<Command> queuedCommands;

    public CommandAggregator(Stack<UndoableCommand> undoableCommands, Queue<Command> queuedCommands) {
        this.undoableCommands = undoableCommands;
        this.queuedCommands = queuedCommands;
    }

    @Override
    public void addCommand(ClientAction clientAction, Player playerToUpdate) {
        Command command = null;
        switch (clientAction) {
            case MOVE_UP:
                command = new MoveUpCommand(playerToUpdate);
                break;
            case MOVE_DOWN:
                command = new MoveDownCommand(playerToUpdate);
                break;
            case MOVE_LEFT:
                command = new MoveLeftCommand(playerToUpdate);
                break;
            case MOVE_RIGHT:
                command = new MoveRightCommand(playerToUpdate);
                break;
            case PLANT_BOMB:
                command = new PlantBombCommand(playerToUpdate);
                break;
            case PLANT_PIT:
                command = new PlantPitCommand(playerToUpdate);
                break;
            case PLANT_SHIELD:
                command = new PlantShieldCommand(playerToUpdate);
                break;
            case SAVE:
                command = new SaveCommand(stateSaves);
                break;
            case LOAD:
                command = new LoadCommand(stateSaves);
                break;
            case UNDO:
                command = new UndoCommand(undoableCommands);
                break;
        }
        if (command != null)
            queuedCommands.add(command);
    }

    @Override
    public void addCommand(String action) {
        Command command = null;
        switch (action) {
            case "CLEAR_SAVES":
                command = new ClearSavesCommand(stateSaves);
                break;
            case "SAVE":
                command = new SaveCommand(stateSaves);
                break;
            case "LOAD":
                command = new LoadCommand(stateSaves);
                break;
            case "UNDO":
                command = new UndoCommand(undoableCommands);
                break;
        }
        if (command != null)
            queuedCommands.add(command);
    }
}
