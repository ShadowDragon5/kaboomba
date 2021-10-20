package com.commands;

import com.core.State;

import java.util.ArrayList;

public class LoadCommand implements Command {
    private ArrayList<State> stateSaves;

    public LoadCommand(ArrayList<State> stateSaves) {
        this.stateSaves = stateSaves;
    }
    @Override
    public void execute() {
        if(stateSaves.isEmpty())
            return;
        stateSaves.add(State.getInstance().clone());
        State.getInstance().loadState(stateSaves.get(stateSaves.size() - 2));
        System.out.println("State LOADED.");
    }

    @Override
    public void undo() {
        if(stateSaves.isEmpty())
            return;
        State.getInstance().loadState(stateSaves.get(stateSaves.size() - 1));
        stateSaves.remove(stateSaves.size() - 1);
        System.out.println("State load UNDONE.");
    }
}
