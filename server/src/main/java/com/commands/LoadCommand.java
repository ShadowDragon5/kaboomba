package com.commands;

import com.core.State;
import com.core.memento.MementoState;

import java.util.Deque;

public class LoadCommand implements UndoableCommand {
    private Deque<MementoState> stateSaves;
    private State temporaryState;

    public LoadCommand(Deque<MementoState> stateSaves) {
        this.stateSaves = stateSaves;
    }

    @Override
    public void execute() {
        if(stateSaves.isEmpty())
            return;
        temporaryState = State.getInstance().clone();
        State.getInstance().restore(stateSaves.getLast());
        System.out.println("Save " + stateSaves.size() +  " LOADED");
    }

    @Override
    public void undo() {
        if(stateSaves.isEmpty() || temporaryState == null)
            return;
        State.getInstance().loadState(temporaryState);
        System.out.println("Save " + stateSaves.size() + " load UNDONE");
    }
}
