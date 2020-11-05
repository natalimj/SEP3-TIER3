package sep3.classes.Database;
import sep3.classes.Model.Hospital;
import sep3.classes.Model.User;

import java.sql.*;
import java.util.ArrayList;

public class HospitalData {
    private  DatabaseConnection db;
    private PreparedStatement pst;
    private Connection connection;

    public HospitalData() {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    public ArrayList<Hospital> getAllHospitals(){
        ArrayList<Hospital> hospitals =new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM HOSPITALS;");

            Hospital hospital;
            while (rs.next())
            {

                hospital = new Hospital(rs.getInt("hospital_id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getInt("post_code"),
                        rs.getString("address"),
                        rs.getInt("manager_id"),
                        rs.getDouble("avg_rating"),
                        rs.getBoolean("validated"),
                        rs.getString("info"));
                hospitals.add(hospital);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return hospitals;
    }
    public Hospital getHospital(int id) {
        ArrayList<Hospital> hospitals =new ArrayList<>();
        Hospital hospital=null;
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM HOSPITALS WHERE hospital_id='\" + id + \"'\"");


            while (rs.next())
            {

                hospital = new Hospital(rs.getInt("hospital_id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getInt("post_code"),
                        rs.getString("address"),
                        rs.getInt("manager_id"),
                        rs.getDouble("avg_rating"),
                        rs.getBoolean("validated"),
                        rs.getString("info"));
                hospitals.add(hospital);
                hospital=hospitals.get(0);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return hospital;

    }

    public void addHospital(Hospital hospital){

        String sql ="INSERT INTO HOSPITALS (name,address,post_code,info,avg_rating,type,manager_id,validated) " +
                "VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,hospital.getName());
            pst.setString(2,hospital.getAddress());
            pst.setInt(3,hospital.getPostalCode());
            pst.setString(4,hospital.getInfo());
            pst.setDouble(5,hospital.getAvgRating());
            pst.setString(6, hospital.getType());
            pst.setInt(7,hospital.getManagerId());
            pst.setBoolean(8,hospital.getValidated());

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED: hospital");

    }

}