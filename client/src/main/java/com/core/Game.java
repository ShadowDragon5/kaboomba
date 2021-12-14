package com.core;

import com.core.enums.ClientAction;
import com.esotericsoftware.kryonet.Client;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Scanner;

import com.UI.GameMenu;
import com.entities.Rectangle;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private final InputController inputController;
    private final GameRenderer gameRenderer;
    private final Client appClient;
    private GameMenu gameMenu;

    // The window handle
    private long window;

    public Game(InputController inputController, GameRenderer gameRenderer, Client appClient) {
        this.inputController = inputController;
        this.gameRenderer = gameRenderer;
        this.appClient = appClient;
        this.gameMenu = new GameMenu(new Rectangle(-160, 0, 160, 320));
    }

    public void setPlayerId(String playerId) {
        gameMenu.setPlayerId(playerId);
    }

    private void startInterpreter() {
        Scanner sc = new Scanner(System.in);

        Runnable runnable = () -> {
            System.out.println("Scanner starts");

            while (true) {
                String message = sc.nextLine();
                if (message != null && !message.isEmpty()) {
                    appClient.sendTCP(String.format("%s;%s", ClientAction.CHAT, message));
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    public void run() {
        startInterpreter();

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_FALSE);

        // Create the window
        window = glfwCreateWindow(960, 600, "KABOOMBA!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
			}
        });

        glfwSetWindowAspectRatio(window, 960, 600);

        inputController.listenControls(window);

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            gameRenderer.render(window);

            gameMenu.render();

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(-160, 320, 320, 0, -1, 1);

            glfwSwapBuffers(window); // swap the color buffers

            glfwPollEvents();
            inputController.keyActionHandler(window);
        }
    }
}
