package com.core;

import com.google.gson.Gson;

public interface Observer {
    public abstract void update(Gson gson);
}
