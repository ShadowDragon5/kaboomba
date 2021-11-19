package com.core;

import com.esotericsoftware.kryonet.Client;

public class ClientProxy extends Client {

    public ClientProxy(int writeBufferSize, int objectBufferSize) {
        super(writeBufferSize, objectBufferSize);
    }

    public void processSendTcp(String message) {
        logMessageSent(message);
        denyAccessIfRequired();
        this.sendTCP(message);
    }

    public void logMessageSent(String message) {
        System.out.println("Log");
    }

    public void denyAccessIfRequired() {
        if(this.getID() == 1) {
            System.out.println("User is blocked");
            return;
        }
    }
}
