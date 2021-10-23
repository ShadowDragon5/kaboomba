package com.core;

import com.core.enums.ClientAction;

public interface ClientActionListener {
    void notifyWithClientAction(ClientAction action);
}
