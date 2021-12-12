package com.core;

import com.commands.*;
import com.esotericsoftware.kryonet.Server;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class ServerApplication {
    private static final Queue<Command> queuedCommands = new ArrayBlockingQueue<Command>(2000);

    public static void main(String... args) throws Exception {

        Level level = new HardLevel();
        level.loadLevel();

        Server server = new Server(1000000, 1000000);

        server.start();
        server.bind(54555);

        ServerFacade facade = new ServerFacade(queuedCommands);
        facade.startEventLoop();

        server.addListener(new ServerListener(facade));
    }
}
