package com.core;

import java.util.ArrayList;

public abstract class Subject {
    protected ArrayList<Observer> observers = new ArrayList<>();

    public void attach(Observer p) {
        observers.add(p);
    }

    public void detach(Observer p) {
        observers.remove(p);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public abstract State getState();
}
