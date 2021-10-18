package com.core;

public class ServerState extends Subject {
    public State getState() {
        return State.getInstance();
    }
}
