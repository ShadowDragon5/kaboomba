package com.core.memento;

import com.core.State;

public class MementoState {
    private State state;

	public MementoState(State currentState) {
        this.state = currentState;
    }

    public State getState() {
		return state;
	}
}
