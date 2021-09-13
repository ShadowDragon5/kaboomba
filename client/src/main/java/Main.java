import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.BufferedReader;ah
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String ...args) {
        Client client = new Client();
        client.start();con
        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println(object);
                }
            }
        });

        try {
            client.connect(5000, "localhost", 54555);

        } catch (IOException e) {
            e.printStackTrace();
        }

        client.sendTCP("Here is the request");

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String input="";
        while (true)  {

            try {

                input = buffer.readLine();
                input.trim();

                client.sendTCP(input);
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
