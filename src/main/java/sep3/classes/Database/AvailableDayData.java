package sep3.classes.Database;

import sep3.classes.Model.AvailableDay;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class AvailableDayData {
    private  DatabaseConnection db;
    private Connection connection;
    private static final Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    public AvailableDayData() {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }


    public ArrayList<AvailableDay> getAvailableDays(int doctorId){

        ArrayList<AvailableDay> availableDays=new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM AVAILABLEDAYS WHERE doctor_id='" + doctorId + "'");

            AvailableDay availableDay;
            while (rs.next())
            {

                Timestamp ts = rs.getTimestamp("start_time", utc);
                LocalDateTime localDt = null;
                if (ts != null)
                    localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
                java.util.Date start = java.util.Date.from(localDt.toInstant(ZoneOffset.UTC));

                ts = rs.getTimestamp("end_time", utc);
                localDt = null;
                if (ts != null)
                    localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
                java.util.Date end = java.util.Date.from(localDt.toInstant(ZoneOffset.UTC));

                availableDay = new AvailableDay(rs.getInt("doctor_id"),
                        rs.getDate("available_date"),
                        start, end,
                        rs.getInt("appointment_no"));

                availableDays.add(availableDay);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return availableDays;

    }

    public void addAvailableDay(AvailableDay availableDay){

        String sql ="INSERT INTO AVAILABLEDAYS (doctor_id,available_date,start_time,end_time,appointment_no) VALUES (?,?,?,?,?);";
        PreparedStatement pst= null;

        java.util.Date in = availableDay.getStartTime();
        Timestamp start = new Timestamp(in.toInstant().toEpochMilli());
        in = availableDay.getEndTime();
        Timestamp end = new Timestamp(in.toInstant().toEpochMilli());

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1,availableDay.getDoctorId());
            pst.setDate(2, new Date(availableDay.getAvailableDate().getTime()));
            pst.setTimestamp(3, start, utc);
            pst.setTimestamp(4, end, utc );
            pst.setInt(5,availableDay.getAppointmentNr());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED: available day ");
    }
    public void deleteAvailableDay(AvailableDay availableDay){

        String sql = "DELETE FROM AVAILABLEDAYS WHERE doctor_id=? AND available_date=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,availableDay.getDoctorId());
            pst.setDate(2, new Date(availableDay.getAvailableDate().getTime()));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED : available day" );
    }
}
