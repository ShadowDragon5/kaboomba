package com.commands;

import com.core.State;
import com.core.memento.MementoState;

import java.util.Deque;

public class SaveCommand implements UndoableCommand {
    private Deque<MementoState> stateSaves;

    public SaveCommand(Deque<MementoState> stateSaves) {
        this.stateSaves = stateSaves;
    }
    @Override
    public void execute() {
        var state = State.getInstance();
        if (!stateSaves.isEmpty() &&
                state.getPlayers().size() != stateSaves.getLast().getState().getPlayers().size()) {
            stateSaves.clear();
            System.out.println("All state saves removed!");
        }
        if (stateSaves.size() >= 5) {
            System.out.println("State save capacity reached. Oldest save deleted.");
            stateSaves.pollFirst();
        }
        stateSaves.add(state.save());
        System.out.println("SAVE " + stateSaves.size());
    }

    @Override
    public void undo() {
        stateSaves.pollLast();
        System.out.println("SAVE "+ (stateSaves.size() + 1) + " UNDONE");
    }
}
