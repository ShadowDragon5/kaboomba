package com.core;

import com.entities.*;

import com.esotericsoftware.kryonet.Client;
import com.utils.TextureLoader;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.awt.*;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static java.lang.Math.*;
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

    public GameRenderer(Client client) {
        this.client = client;
        this.gameController = new GameController();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
    // The window handle
    private long window;

    public void run() {
        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void DrawQuad(Position position, float width, float height, Color color) {
        float x = position.getX();
        float y = position.getY();

        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1f);

        glBegin(GL_QUADS);

        glVertex2f(x - width / 2, y - height / 2);
        glVertex2f(x + width / 2, y - height / 2);

        glVertex2f(x + width / 2, y + height / 2);
        glVertex2f(x - width / 2, y + height / 2);

        glEnd();
    }

    public void DrawTexturedQuad(Position position, float width, float height, int textureId) {
        float x = position.getX();
        float y = position.getY();

        glColor4f(1f, 1f, 1f, 1f);

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0); // top left
        glVertex2f(x - width / 2, y + height / 2);

        glTexCoord2f(0, 1); // bottom left
        glVertex2f(x - width / 2, y - height / 2);

        glTexCoord2f(1, 1); // bottom right
        glVertex2f(x + width / 2, y - height / 2);

        glTexCoord2f(1, 0); // top right
        glVertex2f(x + width / 2, y + height / 2);

        glDisable(GL_TEXTURE_2D);
        glDeleteTextures(textureId);
        glEnd();
    }

    public void DrawTriangle(Position position, float size, Color color) {
        float x = position.getX();
        float y = position.getY();

        GL11.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);

        glBegin(GL_POLYGON);

        glVertex2f(x, y+size/2);

        glVertex2f(x+size/2, y-size/2);
        glVertex2f(x-size/2, y-size/2);

        glEnd();
    }

    public void DrawCircle(Position position, float radius, Color color){
        GL11.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);

        glBegin(GL_POLYGON);

        double ori_x = position.getX();
        double ori_y = position.getY();
        float r = radius / 2;
        for (int i = 0; i <= 300; i++) {
            double angle = 2 * PI * i / 300;
            double x = cos(angle) * r;
            double y = sin(angle) * r;
            glVertex2d(ori_x + x, ori_y + y);
        }
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
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizablE
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_FALSE);
        // Create the window
        window = glfwCreateWindow(600, 600, "KABOOMBA", NULL, NULL);
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


        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            if(map != null){
                renderMap();
            }

            if(state != null) {
                drawTexturedElements(state.getBombs());
                drawTexturedElements(state.getShields());
                drawTexturedElements(state.getPits());
                drawTexturedElements(state.getPlayers());
            }

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public void drawTexturedElements(ArrayList<? extends GameObject> objects){
        objects.forEach(it->{
            int textureId = TextureLoader.getTexture(it);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            DrawTexturedQuad(it.getPosition(), it.getDimensions(), it.getDimensions(), textureId);
            glDisable(GL_BLEND);
        });
    }

    public void renderMap() {
        map.getGameObjects().forEach(it-> {
            int textureId = TextureLoader.getTexture(it);
            DrawTexturedQuad(
                    it.getPosition(),
                    it.getDimensions(),
                    it.getDimensions(),
                    textureId
            );
        });
    }

    public boolean isReady() {
        return map != null;
    }
}
