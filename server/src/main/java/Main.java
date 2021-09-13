import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class Main {
    public static void main(String ...args){
        Server server = new Server();
        server.start();
        try {
            server.bind(54555);
        } catch (IOException e) {
            System.out.println("Unable to start server");
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                System.out.println("Connected" + connection.getID());
                if (object instanceof String) {
                    System.out.println(object);
                    connection.sendTCP("Thanks");
                }
            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("Disconnected" + connection.getID());
            }
        });
    }
}
