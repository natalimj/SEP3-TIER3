package sep3.classes.Network;



import sep3.classes.Database.DataHandler;
import sep3.classes.Model.Request;
import sep3.classes.Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketHandler implements Runnable{

    private Socket socket;
    private DataHandler dataHandler;

    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;


    public SocketHandler(Socket socket, DataHandler dataHandler) {
        this.socket = socket;
        this.dataHandler = dataHandler;

        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        System.out.println("Client connected");
        int i=0;
        while (true){
        try {
            Request request = (Request) inFromClient.readObject();
            switch(request.getType()){
                case "AddUser":
                    dataHandler.addUser((User) request.getArg());
                    break;
                case "GetAllUsers":
                    outToClient.writeObject(new Request("GetAllUsers", dataHandler.getAllUsers()));
                    break;
                case "GetUser":
                    outToClient.writeObject(new Request("GetUser", dataHandler.getUser((Integer) request.getArg())));
                    break;
                case "EditUser":
                    dataHandler.editUser((User) request.getArg());
                    break;
                case "DeleteUser":
                    dataHandler.deleteUser((Integer) request.getArg());
                    break;
            }
        }catch (IOException e){
            if(i==0){
                System.out.println("Client disconnected");
                i++;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        }
    }
}

