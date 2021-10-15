package com.commands;

import com.core.ArithmeticActions;
import com.core.PlayerColors;
import com.core.ServerState;
import com.core.State;
import com.entities.Player;
import com.entities.Position;
import com.utils.DefaultPlayerCreator;
import com.utils.PlayerCreator;
import com.utils.UtilityMethods;

public class ConnectedCommand implements Command {

    private final String color;
    private final State state = State.getInstance();
    private final Execution execution;

    public ConnectedCommand(String color, Execution execution) {
        this.color = color;
        this.execution = execution;
    }

    @Override
    public void execute() {
        PlayerColors playerColor = UtilityMethods.getPlayerColorOrDefault(color);

        PlayerCreator playerCreator = new DefaultPlayerCreator();
        Player player = playerCreator.createPlayer(playerColor);

        int playerCount = state.getPlayers().size() + 1;
        float playerDim = 1.5f *  player.getDimensions();

        float xPos = playerCount == 1 || playerCount == 4 ?
                UtilityMethods.preciseArithmetics(-1f, playerDim, ArithmeticActions.SUM)
                :
                UtilityMethods.preciseArithmetics(1f, playerDim, ArithmeticActions.MIN);

        float yPos = playerCount == 1 || playerCount == 3 ?
                UtilityMethods.preciseArithmetics(1f, playerDim, ArithmeticActions.MIN)
                :
                UtilityMethods.preciseArithmetics(-1f, playerDim, ArithmeticActions.SUM);

        player.setPosition(new Position(xPos, yPos));
        execution.onExecuted(player);
    }
}
