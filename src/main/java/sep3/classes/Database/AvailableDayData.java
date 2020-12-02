package sep3.classes.Database;

import sep3.classes.Model.AvailableDay;
import java.sql.*;
import java.util.ArrayList;


public class AvailableDayData {
    private  DatabaseConnection db;
    private Connection connection;

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

                availableDay = new AvailableDay(rs.getInt("doctor_id"),
                        rs.getDate("available_date"),
                        rs.getDate("start_time"),
                        rs.getDate("end_time"),
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

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1,availableDay.getDoctorId());
            pst.setDate(2, new Date(availableDay.getAvailableDate().getTime()));
            pst.setDate(3, new Date(availableDay.getStartTime().getTime()) );
            pst.setDate(4, new Date(availableDay.getEndTime().getTime()) );
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
