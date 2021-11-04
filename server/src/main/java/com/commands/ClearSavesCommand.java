package com.commands;

import com.core.State;

import java.util.Deque;

public class ClearSavesCommand implements Command {
    private Deque<State> stateSaves;

    public ClearSavesCommand(Deque<State> stateSaves) {
        this.stateSaves = stateSaves;
    }
    @Override
    public void execute() {
        stateSaves.clear();
        System.out.println("All saves have been cleared!");
    }

}
