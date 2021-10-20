package com.commands;

import com.core.State;

import java.util.ArrayList;

public class SaveCommand implements Command {
    private ArrayList<State> stateSaves;

    public SaveCommand(ArrayList<State> stateSaves) {
        this.stateSaves = stateSaves;
    }
    @Override
    public void execute() {
        stateSaves.clear();
        stateSaves.add(State.getInstance().clone());
        System.out.println("State SAVED.");
    }

    @Override
    public void undo() {
        stateSaves.clear();
        System.out.println("State save UNDONE.");
    }
}
