package com.core;

import com.entities.*;

import com.esotericsoftware.kryonet.Client;
import com.google.gson.Gson;
import com.utils.TextureLoader;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GameRenderer {

    private State state;

    private GameMap map;
    private Client client;
    private GameController gameController;

    public GameRenderer(Client client){
        this.client = client;
        this.gameController = new GameController();
    }

    public void setState(State state){
        this.state = state;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
    // The window handle
    private long window;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void DrawQuad(float x, float y, float width, float height, Color color) {
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        glBegin(GL_QUADS);

        glVertex2f(x-width/2, y-height/2);
        glVertex2f(x+width/2, y-height/2);

        glVertex2f(x+width/2, y+height/2);
        glVertex2f(x-width/2, y+height/2);

        glEnd();
    }

    public void DrawTexturedQuad(float x, float y, float width, float height, float color) {
        glColor3f(color, 255, 255);

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0); // top left
        glVertex2f(x-width/2, y-height/2);

        glTexCoord2f(0, 1); // bottom left
        glVertex2f(x+width/2, y-height/2);

        glTexCoord2f(1, 1); // bottom right
        glVertex2f(x+width/2, y+height/2);

        glTexCoord2f(1, 0); // top right
        glVertex2f(x-width/2, y+height/2);

        glEnd();
    }

    public void DrawTriangle(float x, float y, float size, Color color) {
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        glBegin(GL_QUADS);

        glVertex2f(x/2, y/2);

        glVertex2f(x+size/2, y+size/2);
        glVertex2f(x-size/2, y+size/2);

        glEnd();
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
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_FALSE);
        // Create the window
        window = glfwCreateWindow(600, 600, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        gameController.listenControls(client, window);

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
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.5f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            // Rendering map
            if(map != null){
                map.getGameObjects().forEach(it->{
                    boolean isWall = it instanceof Wall;
                    int textureId = TextureLoader.getTexture(it);

                    glEnable(GL_TEXTURE_2D);
                    glBindTexture(GL_TEXTURE_2D, textureId);

                    DrawTexturedQuad(
                            it.getPosition().getX(),
                            it.getPosition().getY(),
                            0.1f,
                            0.1f,
                            isWall ? 0.5f : 0.2f
                    );

                });
            }


            if(state != null) {
                //Rendering players
                state.getPlayers().forEach(it->{
                    DrawQuad(it.getPosition().getX(), it.getPosition().getY(), 0.1f, 0.1f, it.getColor());
                });

                //Rendering bombs
                state.getBombs().forEach(it->{
                    DrawQuad(it.getPosition().getX(), it.getPosition().getY(), 0.1f, 0.1f, it.getColor());
                });
            }

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public boolean isReady() {
        return map != null;
    }
}
