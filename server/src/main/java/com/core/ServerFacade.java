package com.core;

import com.commands.*;
import com.core.enums.ServerAction;
import com.entities.GameObject;
import com.entities.GameMap;
import com.entities.InitialServerResponse;
import com.entities.players.BossPlayer;
import com.entities.players.Player;
import com.entities.tiles.Wall;
import com.esotericsoftware.kryonet.Connection;
import com.mediator.Colleague;
import com.mediator.Client;
import com.mediator.Mediator;
import com.mediator.ServerMediator;

import java.util.*;

public class ServerFacade implements Colleague {
    private final Stack<UndoableCommand> undoableCommands = new Stack<>();
    private final Queue<Command> queuedCommands;
    private final ServerMediator serverMediator;
    private final ProxyCommandAggregator proxyCommandAggregator;

    public Queue<Command> getQueuedCommands() {
        return queuedCommands;
    }



    public ServerFacade(Queue<Command> queuedCommands) {
        this.queuedCommands = queuedCommands;
        this.serverMediator = new ServerMediator();

        CommandAggregator commandAggregator = new CommandAggregator(undoableCommands, queuedCommands);
        this.proxyCommandAggregator = new ProxyCommandAggregator(commandAggregator, serverMediator);
        serverMediator.addColleague(proxyCommandAggregator);
        serverMediator.addColleague(this);
    }

    public void connectPlayer(HashMap<Connection, String> connections, Connection connection, Object object) {
        Player player = (Player) object;
        connections.put(connection, player.ID);
        serverMediator.addColleague(new Client(serverMediator, connection, player.ID));
        serverMediator.getState().addPlayer(player);
        sendMessage("NEW_PLAYER_ADDED");
        
        String mapJson = String.format("%s;%s", ServerAction.GAME_INIT,
                Defaults.gson.toJson(new InitialServerResponse(GameMap.getInstance(), player.ID)));
        connection.sendTCP(mapJson);
    }

    public void startEventLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                executeQueueCommands();
                checkCollisions();

                serverMediator.getState().removeDeadPlayers();

                manageBossState();
                sendMessage("NOTIFY_OBSERVERS");
            }
        }, 0, 10);
    }

    public void manageBossState() {
        State.getInstance().getBosses().forEach(it->{
            ((BossPlayer) it).getBossState().handleBossActions();
        });
    }

    public void bossHandler(Player bossPlayer) {
    }

    public void handleBombBoss() {

    }

    public void checkCollisions() {
        // Check player collisions wit boxes and explosions
        new ArrayList<GameObject>(serverMediator.getState().getPlayers()).forEach(player -> {
            serverMediator.getState().getBoxes().forEach(player::collides);
            serverMediator.getState().getExplosions().forEach(player::collides);
            new ArrayList<GameObject>(serverMediator.getState().getPits()).forEach(player::collides);
            GameMap.getInstance().getGameObjects().stream().filter(it -> it instanceof Wall).forEach(player::collides);
            new ArrayList<GameObject>(serverMediator.getState().getPowerups()).forEach(player::collides);
        });

        // Portal collisions
        new ArrayList<GameObject>(serverMediator.getState().getPortals()).forEach(portal -> {
            serverMediator.getState().getPlayers().forEach(portal::collides);
        });

        // Boss collisions
        new ArrayList<GameObject>(serverMediator.getState().getBosses()).forEach(boss -> {
            serverMediator.getState().getExplosions().forEach(boss::collides);
            new ArrayList<GameObject>(serverMediator.getState().getPits()).forEach(boss::collides);
            GameMap.getInstance().getGameObjects().stream().filter(it -> it instanceof Wall).forEach(boss::collides);
        });
    }

    public void executeQueueCommands() {
        while (!queuedCommands.isEmpty()) {
            var command = queuedCommands.poll();
            command.execute();
            if(command instanceof LoadCommand)
                queuedCommands.clear();
            if(command instanceof UndoableCommand)
                undoableCommands.add((UndoableCommand) command);
        }
    }

    public ProxyCommandAggregator getProxyCommandAggregator() {
        return proxyCommandAggregator;
    }

    @Override
    public Mediator getMediator() {
        return serverMediator;
    }

    @Override
    public void sendMessage(String message) {
        getMediator().broadcast(this, message);
    }
}
