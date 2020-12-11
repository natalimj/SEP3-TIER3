package sep3.classes.Database;

import sep3.classes.Model.Hospital;
import sep3.classes.Model.Rating;

import java.sql.*;
import java.util.ArrayList;

public class RatingData {
    private  DatabaseConnection db;
    private Connection connection;

    public RatingData()  {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    public ArrayList<Rating> getAllRatings(){
        ArrayList<Rating> ratings =new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM RATES;");

            Rating rating;
            while (rs.next())
            {

                rating = new Rating(
                        rs.getInt("rating"),
                        rs.getInt("user_id"),
                        rs.getInt("hospital_id")
                );
                ratings.add(rating);
            }

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return ratings;
    }

    public ArrayList<Rating>  getRating(int userId){
        ArrayList<Rating> ratings = new ArrayList<>();
        Rating rating=null;

        try
        {
            Statement statement = connection.createStatement();
            connection.commit();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM RATES WHERE user_id='" + userId + "'");
            while (rs.next())
            {
                rating = new Rating(rs.getInt("rating"),
                        rs.getInt("user_id"),
                        rs.getInt("hospital_id"));

                ratings.add(rating);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ratings;
    }

    public void addRating(Rating rating)
    {
        String sql ="INSERT INTO RATES (hospital_id,user_id,rating) VALUES (?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,rating.getHospitalId());
            pst.setInt(2,rating.getIdNr());
            pst.setInt(3,rating.getRating());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED: "+rating.getIdNr());

        try{
            Statement statement = connection.createStatement();
            connection.commit();
            double avgRating = 0;
            ResultSet rs = statement
                    .executeQuery("SELECT AVG(rating) AS average FROM RATES WHERE hospital_id='" + rating.getHospitalId() + "'");
            if(rs.next()){
                avgRating = rs.getDouble("average");
            }
            sql = "UPDATE HOSPITALS SET avg_rating=? WHERE hospital_id=?";
            PreparedStatement pst2 = null;

            pst2 = connection.prepareStatement(sql);
            pst2.setDouble(1,avgRating);
            pst2.setInt(2,rating.getHospitalId());

            db.operation(pst2);

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void editRating(Rating rating){
        String sql = "UPDATE RATES SET rating=? WHERE user_id=? AND hospital_id=?";

        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,rating.getRating());
            pst.setInt(2,rating.getIdNr());
            pst.setInt(3,rating.getHospitalId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("EDITED: rating");

        try{
            Statement statement = connection.createStatement();
            connection.commit();
            double avgRating = 0;
            ResultSet rs = statement
                    .executeQuery("SELECT AVG(rating) AS average FROM RATES WHERE hospital_id='" + rating.getHospitalId() + "'");
            if(rs.next()){
                avgRating = rs.getDouble("average");
            }
            sql = "UPDATE HOSPITALS SET avg_rating=? WHERE hospital_id=?";
            PreparedStatement pst2 = null;

            pst2 = connection.prepareStatement(sql);
            pst2.setDouble(1,avgRating);
            pst2.setInt(2,rating.getHospitalId());

            db.operation(pst2);

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public double getAvgRating(int hospitalId){
        double average=0;
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();
            ResultSet rs = statement
                    .executeQuery("SELECT AVG(rating)AS avgRate FROM RATES WHERE hospital_id='" + hospitalId + "'");
            while (rs.next())
            {
            average= rs.getDouble("avgRate");
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return average;
    }
}
