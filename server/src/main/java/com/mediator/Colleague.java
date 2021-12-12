package com.mediator;

public interface Colleague {
    Mediator getMediator();
    void sendMessage(String message);
    default void receiveMessage(String message){};
}
