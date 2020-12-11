package sep3.classes.Network;



import sep3.classes.Database.DataHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDBServer  {
    private DataHandler dataHandler;

    public SocketDBServer(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2910);

            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                new Thread(new SocketHandler(connectionSocket,dataHandler)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
