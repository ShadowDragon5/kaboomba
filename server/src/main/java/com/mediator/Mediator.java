package com.mediator;

import com.core.State;

public interface Mediator {
    State getState();
    void addColleague(Colleague c);
    void broadcast(Colleague c, String message);
}
