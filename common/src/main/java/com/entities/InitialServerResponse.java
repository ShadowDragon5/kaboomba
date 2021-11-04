package com.entities;

public class InitialServerResponse {
    private GameMap gameMap;
    private String playerId;

    public GameMap getGameMap() {
        return gameMap;
    }

    public String getPlayerId() {
        return playerId;
    }

    public InitialServerResponse(GameMap gameMap, String playerId) {
        this.gameMap = gameMap;
        this.playerId = playerId;
    }
}
