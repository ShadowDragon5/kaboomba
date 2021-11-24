package com.core;

import com.commands.*;
import com.core.enums.ClientAction;
import com.core.enums.ServerAction;
import com.entities.GameObject;
import com.entities.GameMap;
import com.entities.InitialServerResponse;
import com.entities.boss.BombBoss;
import com.entities.players.BossPlayer;
import com.entities.players.Player;
import com.entities.tiles.Wall;
import com.esotericsoftware.kryonet.Connection;
import com.filters.BlockListRequestHandler;
import com.filters.LogRequestHandler;
import com.filters.ProcessRequestHandler;
import com.filters.ValidationRequestHandler;

import java.util.*;

public class ServerFacade {
    private final Deque<State> stateSaves = new ArrayDeque<>();
    private final Stack<UndoableCommand> undoableCommands = new Stack<>();
    private final Queue<Command> queuedCommands;
    private final ServerState serverState;
    private final ProxyCommandAggregator proxyCommandAggregator;

    public Queue<Command> getQueuedCommands() {
        return queuedCommands;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public ServerFacade(Queue<Command> queuedCommands, ServerState serverState) {
        this.queuedCommands = queuedCommands;
        this.serverState = serverState;

        CommandAggregator commandAggregator = new CommandAggregator(stateSaves, undoableCommands, queuedCommands);
        this.proxyCommandAggregator = new ProxyCommandAggregator(commandAggregator);
    }

    public void connectPlayer(HashMap<Connection, String> connections, Connection connection, Object object) {
        Player player = (Player) object;
        connections.put(connection, player.ID);
        serverState.attach(new Client(serverState, connection, player.ID));
        serverState.getState().addPlayer(player);
        proxyCommandAggregator.addCommand("CLEAR_SAVES");
        proxyCommandAggregator.addCommand("SAVE");

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

                serverState.getState().removeDeadPlayers();

                manageBossState();
                serverState.notifyObservers();
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
        new ArrayList<GameObject>(serverState.getState().getPlayers()).forEach(player -> {
            serverState.getState().getBoxes().forEach(player::collides);
            serverState.getState().getExplosions().forEach(player::collides);
            new ArrayList<GameObject>(serverState.getState().getPits()).forEach(player::collides);
            GameMap.getInstance().getGameObjects().stream().filter(it -> it instanceof Wall).forEach(player::collides);
            new ArrayList<GameObject>(serverState.getState().getPowerups()).forEach(player::collides);
            serverState.getState().getBosses().forEach(player::collides);
        });

        new ArrayList<GameObject>(serverState.getState().getPortals()).forEach(portal -> {
            serverState.getState().getPlayers().forEach(portal::collides);
        });

        new ArrayList<GameObject>(serverState.getState().getBosses()).forEach(boss -> {
            serverState.getState().getPlayers().forEach(boss::collides);
            serverState.getState().getExplosions().forEach(boss::collides);
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
}
