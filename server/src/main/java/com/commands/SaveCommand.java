package com.commands;

import com.core.State;

import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;

public class SaveCommand implements UndoableCommand {
    private Deque<State> stateSaves;

    public SaveCommand(Deque<State> stateSaves) {
        this.stateSaves = stateSaves;
    }
    @Override
    public void execute() {
        if(stateSaves.size() >= 5)
        {
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
