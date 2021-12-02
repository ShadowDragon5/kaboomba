package com.chat;

import com.core.State;

public class ChatContext {
    private String playerId;
    private boolean isCommand;
    private String attribute;
    private String value;
    private String text;

    public ChatContext(String playerId) {
        this.playerId = playerId;
    }

    public void setCommand(boolean command) {
        isCommand = command;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String execute() {
        String message = "";
        var player = State.getInstance().getPlayer(playerId);
        if (isCommand) {
            switch (attribute) {
                case "P":
                    player.setBombPower(Integer.parseInt(value));
                    message = "Player bomb power set to " + value;
                    break;
                case "H":
                    player.setHealth(Integer.parseInt(value));
                    message = "Player health set to " + value;
                    break;
                case "A":
                    player.setBombAmmo(Integer.parseInt(value));
                    message = "Player bomb ammo set to " + value;
                    break;
                case "S":
                    player.setSpeed(Float.parseFloat(value));
                    message = "Player speed set to " + value;
                    break;
                default:
                    message = "Attribute " + attribute + " unrecognized.";
                    break;
            }

        } else {
            message = player.getName() + ": " + text;
        }

        return message;
    }
}
