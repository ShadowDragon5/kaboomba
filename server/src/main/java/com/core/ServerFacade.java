package com.core;

import com.commands.*;
import com.core.enums.ClientAction;
import com.core.enums.ServerAction;
import com.entities.GameMap;
import com.entities.GameObject;
import com.entities.players.Player;
import com.entities.tiles.Wall;
import com.esotericsoftware.kryonet.Connection;
import java.util.*;

public class ServerFacade {
    private final Deque<State> stateSaves = new ArrayDeque<>();
    private final Stack<UndoableCommand> undoableCommands = new Stack<>();
    private final Queue<Command> queuedCommands;
    private final ServerState serverState;
    private final GameMap gameMap;

    public Queue<Command> getQueuedCommands() {
        return queuedCommands;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public ServerFacade(Queue<Command> queuedCommands, ServerState serverState, GameMap gameMap) {
        this.queuedCommands = queuedCommands;
        this.serverState = serverState;
        this.gameMap = gameMap;
    }

    public void connectPlayer(HashMap<Connection, String> connections, Connection connection, Object object) {
        Player player = (Player) object;
        connections.put(connection, player.ID);
        serverState.attach(new Client(serverState, connection, player.ID));
        serverState.getState().addPlayer(player);
        addCommand(ClientAction.SAVE, player);

        String mapJson = String.format("%s;%s", ServerAction.MAP_INIT, Globals.gson.toJson(gameMap));
        connection.sendTCP(mapJson);
    }

    public void startEventLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                while (!queuedCommands.isEmpty()) {
                    var command = queuedCommands.poll();
                    command.execute();
                    if(command instanceof LoadCommand)
                        queuedCommands.clear();
                    if(command instanceof UndoableCommand)
                        undoableCommands.add((UndoableCommand) command);

                    // Check player collisions wit boxes and explosions
                    new ArrayList<GameObject>(serverState.getState().getPlayers()).forEach(player -> {
                        serverState.getState().getBoxes().forEach(player::collides);
                        serverState.getState().getExplosions().forEach(player::collides);
                        new ArrayList<GameObject>(serverState.getState().getPits()).forEach(player::collides);
                        gameMap.getGameObjects().stream().filter(it -> it instanceof Wall).forEach(player::collides);
                        new ArrayList<GameObject>(serverState.getState().getPowerups()).forEach(player::collides);
                    });
                }

                serverState.notifyObservers();
            }
        }, 0, 10);
    }

    public void addCommand(ClientAction clientAction, Player playerToUpdate) {
        Command command = null;
        switch (clientAction) {
            case MOVE_UP:
                command = new MoveUpCommand(playerToUpdate);
                break;
            case MOVE_DOWN:
                command = new MoveDownCommand(playerToUpdate);
                break;
            case MOVE_LEFT:
                command = new MoveLeftCommand(playerToUpdate);
                break;
            case MOVE_RIGHT:
                command = new MoveRightCommand(playerToUpdate);
                break;
            case PLANT_BOMB:
                command = new PlantBombCommand(playerToUpdate);
                break;
            case PLANT_PIT:
                command = new PlantPitCommand(playerToUpdate);
                break;
            case PLANT_SHIELD:
                command = new PlantShieldCommand(playerToUpdate);
                break;
            case SAVE:
                command = new SaveCommand(stateSaves);
                break;
            case LOAD:
                command = new LoadCommand(stateSaves);
                break;
            case UNDO:
                command = new UndoCommand(undoableCommands);
                break;
        }
        if (command != null)
            queuedCommands.add(command);
    }
}
