package sep3.classes.Database;


import sep3.classes.Model.*;

import java.util.ArrayList;

public interface DataHandler {

    public ArrayList<User> getAllUsers();
    public User getUser(int id);
    public void addUser(User user);
    public void deleteUser(int id);
    public void editUser(User user);

    public ArrayList<Hospital> getAllHospitals();
    public Hospital getHospital(int id);
    public void addHospital(Hospital hospital);
 //   public void deleteHospital(int id);
 //   public void editHospital(Hospital hospital);


   // public ArrayList<MedicalRecord> getAllMedicalRecordData();
    public MedicalRecord getMedicalRecord(int id);
    public void addMedicalRecord(MedicalRecord medicalRecord);
   // public void deleteMedicalRecord(int id);
    public void editMedicalRecord(MedicalRecord medicalRecord);

   // public ArrayList<Rating> getAllRatings();
    public Rating getRating(int id);
    public void addRating(Rating rating);
  //  public void deleteRating(int id);
    public void editRating(Rating rating);
    public double getAvgRating(int hospitalId);

    public ArrayList<Message> getAllMessages();
    public ArrayList<Message> getUserMessages(int userId);
    public void addMessage(Message message);  //send message
    public void deleteMessage(int msgId);

    //public ArrayList<Appointment> getAllAppointments();
    //public User getAppointment(int doctorId);
    //public void addAppointment(Appointment appointment);
    //public void deleteAppointment(Appointment appointment);
    //public void editAppointment(Appointment appointment);
}
