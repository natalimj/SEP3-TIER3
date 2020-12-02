package sep3.classes.Network;



import sep3.classes.Database.DataHandler;
import sep3.classes.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
                    case "getAllHospitals":
                        outToClient.writeObject(new Request("getAllHospitals", dataHandler.getAllHospitals()));
                        break;
                    case "getHospital":
                        outToClient.writeObject(new Request("getHospital", dataHandler.getHospital((Integer) request.getArg())));
                        break;
                    case "addHospital":
                        dataHandler.addHospital((Hospital) request.getArg());
                        break;
                    case "deleteHospital":
                        dataHandler.deleteHospital((Integer) request.getArg());
                        break;
                    case "editHospital":
                        dataHandler.editHospital((Hospital) request.getArg());
                        break;
                    case "getMedicalRecord":
                        outToClient.writeObject(new Request("getMedicalRecord", dataHandler.getMedicalRecord((Integer) request.getArg())));
                        break;
                    case "addMedicalRecord":
                        dataHandler.addMedicalRecord((MedicalRecord) request.getArg());
                        break;
                    case "editMedicalRecord":
                        dataHandler.editMedicalRecord((MedicalRecord) request.getArg());
                        break;
                    case "getRating":
                        outToClient.writeObject(new Request("getRating", dataHandler.getRating((Integer) request.getArg())));
                        break;
                    case "addRating":
                        dataHandler.addRating((Rating) request.getArg());
                        break;
                    case "editRating":
                        dataHandler.editRating((Rating) request.getArg());
                        break;
                    case "getAvgRating":
                        outToClient.writeObject(new Request("getAvgRating", dataHandler.getAvgRating((Integer) request.getArg())));
                        break;
                    case "getAllMessages":
                        outToClient.writeObject(new Request("getAllMessages", dataHandler.getAllMessages()));
                        break;
                    case "getUserMessages":
                        outToClient.writeObject(new Request("getUserMessages", dataHandler.getUserMessages((Integer) request.getArg())));
                        break;
                    case "addMessage":
                        dataHandler.addMessage((Message) request.getArg());
                        break;
                    case "deleteMessage":
                        dataHandler.deleteMessage((Message) request.getArg());
                        break;
                    case "getAllAppointments":
                        outToClient.writeObject(new Request("getAllAppointments", dataHandler.getAllAppointments()));
                        break;
                    case "addAppointment":
                        dataHandler.addAppointment((Appointment) request.getArg());
                        break;
                    case "deleteAppointment":
                        dataHandler.deleteAppointment((Appointment) request.getArg());
                        break;
                    case "getAvailableDays":
                        outToClient.writeObject(new Request("getAvailableDays", dataHandler.getAvailableDays((Integer) request.getArg())));
                        break;
                    case "addAvailableDay":
                        dataHandler.addAvailableDay((AvailableDay) request.getArg());
                        break;
                    case "deleteAvailableDay":
                        dataHandler.deleteAvailableDay((AvailableDay) request.getArg());
                        break;
                    case "getHospitalDoctor":
                        outToClient.writeObject(new Request("getHospitalDoctor", dataHandler.getHospitalDoctor((Integer) request.getArg())));
                        break;
                    case "addHospitalDoctor":
                        dataHandler.addHospitalDoctor((HospitalDoctor) request.getArg());
                        break;
                    case "deleteHospitalDoctor":
                        dataHandler.deleteHospitalDoctor((HospitalDoctor) request.getArg());
                        break;
                    case "getDepartmentsOfHospital":
                        outToClient.writeObject(new Request("getDepartmentsOfHospital", dataHandler.getDepartmentsOfHospital((Integer) request.getArg())));
                        break;

        }

            }catch (IOException e){
                if(i==0){
                    System.out.println("Client disconnected");
                    i++;
                    close();
                }
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    public void close(){
        try {
            outToClient.close();
            socket.close();
            inFromClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

