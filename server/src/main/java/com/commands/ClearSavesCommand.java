package com.commands;

import com.core.memento.MementoState;

import java.util.Deque;

public class ClearSavesCommand implements Command {
    private Deque<MementoState> stateSaves;

    public ClearSavesCommand(Deque<MementoState> stateSaves) {
        this.stateSaves = stateSaves;
    }

    @Override
    public void execute() {
        stateSaves.clear();
        System.out.println("All saves have been cleared!");
    }

}
