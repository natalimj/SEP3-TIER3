package sep3.classes.Database;

import sep3.classes.Model.Appointment;


import java.sql.*;
import java.util.ArrayList;


public class AppointmentData {
    private  DatabaseConnection db;
    private PreparedStatement pst;
    private Connection connection;

    public AppointmentData()  {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    public ArrayList<Appointment> getAllAppointments(){


        ArrayList<Appointment> appointments =new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM APPOINTMENTS;");


            Appointment appointment;
            while (rs.next())
            {
                appointment = new Appointment(rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_time"),
                        rs.getDate("appointment_date"));

                appointments.add(appointment);

            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return appointments;


    }

    public void addAppointment(Appointment appointment){

        String sql ="INSERT INTO APPOINTMENTS (patient_id,doctor_id,appointment_time,appointment_date) VALUES (?,?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,appointment.getPatientId());
            pst.setInt(2,appointment.getDoctorId());
            pst.setDate(3, (java.sql.Date) appointment.getAppointmentTime());
            pst.setDate(4, (java.sql.Date) appointment.getAppointmentDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED appointment");
    }
    public void deleteAppointment(Appointment appointment){
        String sql = "DELETE FROM APPOINTMENTS WHERE patient_id=? AND doctor_id=? AND appointment_time=? AND appointment_date=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,appointment.getPatientId());
            pst.setInt(2,appointment.getDoctorId());
            pst.setDate(3, (Date) appointment.getAppointmentTime());
            pst.setDate(4, (java.sql.Date) appointment.getAppointmentDate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED : appointment");
    }

}
