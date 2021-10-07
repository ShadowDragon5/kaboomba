package com.core;

public class ServerState extends Subject {
    private State state;

    public ServerState() {
        state = State.getInstance();
    }

    public State getState() {
        return state;
    }
}
