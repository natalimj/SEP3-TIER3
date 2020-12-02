package sep3.classes.Database;

import sep3.classes.Model.*;


import java.util.ArrayList;

public class DataHandlerImpl implements DataHandler
{
    private UserData userData;
    private HospitalData hospitalData;
    private MedicalRecordData medicalRecordData;
    private RatingData ratingData;
    private MessageData messageData;
    private AppointmentData appointmentData;
    private AvailableDayData availableDayData;
    private HospitalDoctorData hospitalDoctorData;

    public DataHandlerImpl()  {
           userData = new UserData();
           hospitalData=new HospitalData();
           medicalRecordData=new MedicalRecordData();
           ratingData=new RatingData();
           messageData=new MessageData();
           appointmentData=new AppointmentData();
           availableDayData=new AvailableDayData();
           hospitalDoctorData=new HospitalDoctorData();
    }

    public ArrayList<User> getAllUsers() {
        return userData.getAllUsers();
    }

    public User getUser(int id) {
       return userData.getUser(id);
    }

    public void addUser(User user) {
        userData.addUser(user);
    }

    public void deleteUser(int id) {
        userData.deleteUser(id);
    }

    public void editUser(User user) {
        userData.editUser(user);
    }

    @Override
    public ArrayList<Hospital> getAllHospitals() {
        return hospitalData.getAllHospitals();
    }

    @Override
    public Hospital getHospital(int id) {
        return hospitalData.getHospital(id);
    }

    @Override
    public void addHospital(Hospital hospital) {
      hospitalData.addHospital(hospital);
    }

    @Override
    public void deleteHospital(int id) {
        hospitalData.deleteHospital(id);
    }

    @Override
    public MedicalRecord getMedicalRecord(int id) {
        return medicalRecordData.getMedicalRecord(id);
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
      medicalRecordData.addMedicalRecord(medicalRecord);
    }

    @Override
    public void editMedicalRecord(MedicalRecord medicalRecord) {
       medicalRecordData.editMedicalRecord(medicalRecord);
    }


    @Override
    public Rating getRating(int id) {

        return ratingData.getRating(id);
    }

    @Override
    public void addRating(Rating rating) {
     ratingData.addRating(rating);
    }


    @Override
    public void editRating(Rating rating) {
      ratingData.editRating(rating);
    }

    @Override
    public double getAvgRating(int hospitalId) {
        return ratingData.getAvgRating(hospitalId);
    }

    @Override
    public ArrayList<Message> getAllMessages() {
        return messageData.getAllMessages();
    }

    @Override
    public ArrayList<Message> getUserMessages(int userId) {
        return messageData.getUserMessages(userId);
    }

    @Override
    public void addMessage(Message message) {
    messageData.addMessage(message);
    }

    @Override
    public void deleteMessage(Message message) {
      messageData.deleteMessage(message);
    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        return appointmentData.getAllAppointments();
    }

    @Override
    public void addAppointment(Appointment appointment) {
      appointmentData.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
     appointmentData.deleteAppointment(appointment);
    }

    @Override
    public ArrayList<AvailableDay> getAvailableDays(int doctorId) {
        return availableDayData.getAvailableDays(doctorId);
    }

    @Override
    public void addAvailableDay(AvailableDay availableDay) {
     availableDayData.addAvailableDay(availableDay);
    }

    @Override
    public void deleteAvailableDay(AvailableDay availableDay) {
     availableDayData.deleteAvailableDay(availableDay);
    }

    @Override
    public ArrayList<HospitalDoctor> getHospitalDoctor(int doctorId) {
        return hospitalDoctorData.getHospitalDoctor(doctorId);
    }

    @Override
    public void addHospitalDoctor(HospitalDoctor hospitalDoctor) {
          hospitalDoctorData.addHospitalDoctor(hospitalDoctor);
    }

    @Override
    public void deleteHospitalDoctor(HospitalDoctor hospitalDoctor) {
         hospitalDoctorData.deleteHospitalDoctor(hospitalDoctor);
    }

    @Override
    public ArrayList<String> getDepartmentsOfHospital(int hospitalId) {
        return hospitalDoctorData.getDepartmentsOfHospital(hospitalId);
    }


}





