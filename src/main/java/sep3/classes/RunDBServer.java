package sep3.classes;

import sep3.classes.Database.DataHandler;
import sep3.classes.Database.DatabaseHandler;
import sep3.classes.Network.SocketDBServer;



public class RunDBServer {

    public static void main(String[] args) {

        DataHandler dataHandler=new DatabaseHandler();
        SocketDBServer socketDBServer=new SocketDBServer(dataHandler);
        socketDBServer.startServer();
    }
}
