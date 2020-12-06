package sep3.classes.Database;

import sep3.classes.Model.Appointment;


import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class AppointmentData {
    private  DatabaseConnection db;
    private Connection connection;
    private static final Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

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
                Timestamp ts = rs.getTimestamp("appointment_time", utc);

                LocalDateTime localDt = null;
                if (ts != null)
                    localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
                //System.out.println(localDt.toString());
                java.util.Date date = java.util.Date.from(localDt.toInstant(ZoneOffset.UTC));
                //System.out.println(date.toString());

                appointment = new Appointment(rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        date,
                        rs.getDate("appointment_date"),
                        rs.getString("summary"));

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

        java.util.Date in = appointment.getAppointmentTime();
        /*LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        System.out.println(ldt.toString());*/
        Timestamp ts = new Timestamp(in.toInstant().toEpochMilli());
        //System.out.println(ts.toString());

        String sql ="INSERT INTO APPOINTMENTS (patient_id,doctor_id,appointment_time,appointment_date,summary) VALUES (?,?,?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,appointment.getPatientId());
            pst.setInt(2,appointment.getDoctorId());
            pst.setTimestamp(3, ts,utc);
            pst.setDate(4,new Date(appointment.getAppointmentDate().getTime()));
            pst.setString(5, appointment.getSummary());
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
            pst.setDate(3,new Date(appointment.getAppointmentTime().getTime()) );
            pst.setDate(4,new Date(appointment.getAppointmentDate().getTime()) );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED : appointment");
    }

}

