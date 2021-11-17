package com.commands;

import com.core.State;
import java.util.Deque;

public class SaveCommand implements UndoableCommand {
    private Deque<State> stateSaves;

    public SaveCommand(Deque<State> stateSaves) {
        this.stateSaves = stateSaves;
    }
    @Override
    public void execute() {
        if (!stateSaves.isEmpty() &&
                State.getInstance().getPlayers().size() != stateSaves.getLast().getPlayers().size()) {
            stateSaves.clear();
            System.out.println("All state saves removed!");
        }
        if (stateSaves.size() >= 5) {
            System.out.println("State save capacity reached. Oldest save deleted.");
            stateSaves.pollFirst();
        }
        stateSaves.add(State.getInstance().clone());
        System.out.println("SAVE " + stateSaves.size());
    }

    @Override
    public void undo() {
        stateSaves.pollLast();
        System.out.println("SAVE "+ (stateSaves.size() + 1) + " UNDONE");
    }
}
