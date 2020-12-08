package sep3.classes.Database;

import sep3.classes.Model.Appointment;


import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
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
                Timestamp ts = rs.getTimestamp("startTime",utc);
                LocalDateTime start = null;
                if (ts != null)
                    start = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);


                Timestamp ts2 = rs.getTimestamp("endTime",utc);
                LocalDateTime end = null;
                if(ts2!=null)
                   end = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts2.getTime()),ZoneOffset.UTC);

                appointment = new Appointment(rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        start,
                        end,
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

        Timestamp ts = new Timestamp(appointment.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(ts.toString());
        Timestamp ts2 = new Timestamp(appointment.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(ts2.toString());

        String sql ="INSERT INTO appointments VALUES (?,?,?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,appointment.getPatientId());
            pst.setInt(2,appointment.getDoctorId());
            pst.setTimestamp(3, ts,utc);
            pst.setTimestamp(4, ts2,utc);
            pst.setString(5, appointment.getSummary());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        db.operation(pst);
        System.out.println("ADDED appointment");
    }

    public void deleteAppointment(Appointment appointment){

        String sql = "DELETE FROM APPOINTMENTS WHERE appointment_id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,appointment.getAppointmentId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED : appointment");
    }

}

