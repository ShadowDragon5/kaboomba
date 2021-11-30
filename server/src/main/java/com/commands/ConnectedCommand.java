package com.commands;

import com.core.enums.ArithmeticActions;
import com.core.enums.PlayerColors;
import com.core.State;
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

        int playerCount = state.getPlayers().size() + 1;
        float playerDim = 1.5f *  player.getDimensions();

        // TODO pick from spawnpoint
        float xPos = playerCount == 1 || playerCount == 4 ?
                UtilityMethods.preciseArithmetics(-1f, playerDim, ArithmeticActions.SUM)
                :
                UtilityMethods.preciseArithmetics(1f, playerDim, ArithmeticActions.MIN);

        float yPos = playerCount == 1 || playerCount == 3 ?
                UtilityMethods.preciseArithmetics(1f, playerDim, ArithmeticActions.MIN)
                :
                UtilityMethods.preciseArithmetics(-1f, playerDim, ArithmeticActions.SUM);

        player.setRectangle(new Rectangle(xPos, yPos));
        execution.onExecuted(player);
    }
}
