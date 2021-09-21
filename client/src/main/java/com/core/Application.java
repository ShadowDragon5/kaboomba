package com.core;

import com.entities.Position;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.utils.ActionUtils;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String ...args){
        ServerConnection connection = ServerConnection.getInstance();
        Client client = connection.startListening();
        Position position = new Position();

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println(object);
                }
            }
        });

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input="";
        while (!input.equals("X"))  {
            try {
                input = buffer.readLine();
                input = input.trim();

                ClientAction action = ClientAction.valueOf(input);
                var actionObject = ActionUtils.createActionObject(action, position);
                client.sendTCP(actionObject);
            }
            catch (IOException e)  {
                System.out.println("An input eror has occured");
            }
            catch (NumberFormatException e)  {
                System.out.println("Please enter in a number");
            }

        }


    }
}
