package com.mediator;

import com.core.ProxyCommandAggregator;
import com.core.ServerFacade;
import com.core.State;

import java.util.ArrayList;

public class ServerMediator implements Mediator {
    private final ArrayList<Colleague> clients = new ArrayList<>();
    private ProxyCommandAggregator proxyCommandAggregator;
    private ServerFacade serverFacade;

    @Override
    public void addColleague(Colleague c) {
        if(c instanceof Client) {
            clients.add(c);
        } else if (c instanceof ProxyCommandAggregator) {
            this.proxyCommandAggregator = (ProxyCommandAggregator) c;
        } else {
            this.serverFacade = (ServerFacade) c;
        }
    }

    @Override
    public void broadcast(Colleague c, String message) {
        if(c instanceof ServerFacade && message.equals("NEW_PLAYER_ADDED")) {
            proxyCommandAggregator.receiveMessage("INITIATE_STATE_SAVE");
            return;
        }

        if(message.equals("NOTIFY_OBSERVERS")) {
            clients.forEach(it->{
                it.receiveMessage(message);
            });
        }
    }

    @Override
    public State getState() {
        return State.getInstance();
    }
}
