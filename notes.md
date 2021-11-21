00:22 ERROR: [kryonet] Error updating connection: Connection 5
com.esotericsoftware.kryonet.KryoNetException: Invalid object length: 0
        at com.esotericsoftware.kryonet.TcpConnection.readObject(TcpConnection.java:116)
        at com.esotericsoftware.kryonet.Client.update(Client.java:255)
        at com.esotericsoftware.kryonet.Client.run(Client.java:338)
        at java.base/java.lang.Thread.run(Thread.java:829)
00:22  INFO: [kryonet] Connection 5 disconnected.
Exception in thread "Client" com.esotericsoftware.kryonet.KryoNetException: Invalid object length: 0
        at com.esotericsoftware.kryonet.TcpConnection.readObject(TcpConnection.java:116)
        at com.esotericsoftware.kryonet.Client.update(Client.java:255)
        at com.esotericsoftware.kryonet.Client.run(Client.java:338)

