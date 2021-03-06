package com.commands;

import com.core.enums.PlayerColors;
import com.core.Defaults;
import com.core.State;
import com.entities.GameMap;
import com.entities.InitialPlayerConnection;
import com.entities.players.Player;
import com.entities.Rectangle;
import com.factories.player.DefaultPlayerCreator;
import com.factories.player.PlayerCreator;
import com.utils.UtilityMethods;

public class ConnectedCommand implements Command {

    private final InitialPlayerConnection playerConnection;
    private final State state = State.getInstance();
    private final Execution execution;

    public ConnectedCommand(InitialPlayerConnection playerConnection, Execution execution) {
        this.playerConnection = playerConnection;
        this.execution = execution;
    }

    @Override
    public void execute() {
        PlayerColors playerColor = UtilityMethods.getPlayerColorOrDefault(playerConnection.getColor());

        PlayerCreator playerCreator = new DefaultPlayerCreator();
        Player player = playerCreator.createPlayer(playerColor);
        String name = playerConnection.getName();
        if (name.equals("")) {
            name = "Player" + (state.getPlayers().size() + 1);
        }
        player.setName(name);

        var spawnPoints = GameMap.getInstance().getSpawnPoints();
        var point = spawnPoints.get(state.getPlayers().size() % spawnPoints.size()).snap();
        float xPos = point.getX();
        float yPos = point.getY();

        player.setRectangle(new Rectangle(xPos, yPos, Defaults.playerWidth, Defaults.playerHeight));
        execution.onExecuted(player);
    }
}
