package com.core;

import java.util.ArrayList;
import com.google.gson.Gson;

public abstract class Subject {
    protected ArrayList<Observer> observers = new ArrayList<>();

    public void attach(Observer p) {
        observers.add(p);
    }

    public void detach(Observer p) {
        observers.remove(p);
    }

    public void notifyObservers(Gson gson) {
        for (Observer o : observers) {
            o.update(gson);
        }
    }

    public abstract State getState();
}
