package com.core;

import com.esotericsoftware.kryonet.Client;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;

public class GameController {
    public void listenControls(Client client, long win){
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(win, (window, key, scancode, action, mods) -> {
            if(action == GLFW_PRESS) {
                switch (key){
                    case GLFW_KEY_ESCAPE:
                        glfwSetWindowShouldClose(window, true);
                        break;
                    case GLFW_KEY_UP:
                        client.sendTCP(ClientAction.MOVE_UP + ";");
                        break;
                    case GLFW_KEY_DOWN:
                        client.sendTCP(ClientAction.MOVE_DOWN + ";");
                        break;
                    case GLFW_KEY_RIGHT:
                        client.sendTCP(ClientAction.MOVE_RIGHT + ";");
                        break;
                    case GLFW_KEY_LEFT:
                        client.sendTCP(ClientAction.MOVE_LEFT + ";");
                        break;
                    case GLFW_KEY_SPACE:
                        client.sendTCP(ClientAction.PLANT_BOMB + ";");
                        break;
                    case GLFW_KEY_Z:
                        client.sendTCP(ClientAction.PLANT_SHIELD + ";");
                        break;
                    case GLFW_KEY_X:
                        client.sendTCP(ClientAction.PLANT_PIT + ";");
                        break;
                }
            }
        });
    }
}
