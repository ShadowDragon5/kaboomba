package com.core;

import com.esotericsoftware.kryonet.Client;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;

public class InputController {

    public HashMap<Integer, Boolean> keysPressed = new HashMap<>();
    public ClientActionListener clientActionListener;

    public InputController(ClientActionListener clientActionListener) {
        this.clientActionListener = clientActionListener;
    }

    public void listenControls(long win){
        glfwSetKeyCallback(win, (window, key, scancode, action, mods) -> {
            switch (action){
                case GLFW_PRESS:
                    keysPressed.put(key, true);
                    break;
                case GLFW_RELEASE:
                    keysPressed.put(key, false);
            }
        });
    }

    public ClientAction selectClientAction(long window , int key) {
        switch (key) {
            case GLFW_KEY_ESCAPE:
                glfwSetWindowShouldClose(window, true);
                return ClientAction.QUIT;
            case GLFW_KEY_UP:
                return ClientAction.MOVE_UP;
            case GLFW_KEY_DOWN:
                return ClientAction.MOVE_DOWN;
            case GLFW_KEY_RIGHT:
                return ClientAction.MOVE_RIGHT;
            case GLFW_KEY_LEFT:
                return ClientAction.MOVE_LEFT;
            case GLFW_KEY_SPACE:
                keysPressed.put(key, false);
                return ClientAction.PLANT_BOMB;
            case GLFW_KEY_Z:
                keysPressed.put(key, false);
                return ClientAction.PLANT_SHIELD;
            case GLFW_KEY_X:
                keysPressed.put(key, false);
                return ClientAction.PLANT_PIT;
        }
        return ClientAction.NOOP;
    }

    public void keyActionHandler(long window) {
        keysPressed.keySet().forEach(it->{
            if(!keysPressed.get(it)){
                return;
            }

            clientActionListener.notifyWithClientAction(selectClientAction(window, it));
        });
    }
}
