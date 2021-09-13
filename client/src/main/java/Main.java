import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class Main {
    public static void main(String ...args) {
        Client client = new Client();
        client.start();
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

        while(true){

        }
    }
}
