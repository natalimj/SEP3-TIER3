package sep3.classes.Database;


import sep3.classes.Model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserData {

    private  DatabaseConnection db;
    private Connection connection;

    public UserData()  {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    
    public ArrayList<User> getAllUsers(){
        ArrayList<User> users =new ArrayList<User>();
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM USERS;");

            User user;
            while (rs.next())
            {
                user = new User(rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("user_type"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("gender"),
                        rs.getDate("birthday"),
                        rs.getString("tel_no"),
                        rs.getString("address"),
                        rs.getBoolean("validated"));
                users.add(user);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return users;
    }


    public User getUser(String id){
        ArrayList<User> users = new ArrayList<>();
        User user=null;

        try
        {
            Statement statement = connection.createStatement();
            connection.commit();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM USERS WHERE user_id='" + id + "'");
            while (rs.next())
            {
                user = new User(rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("user_type"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("gender"),
                        rs.getDate("birthday"),
                        rs.getString("tel_no"),
                        rs.getString("address"),
                        rs.getBoolean("validated"));

                users.add(user);
                user=users.get(0);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return user;
    }

    public void addUser(User user)
    {
        String sql ="INSERT INTO USERS (user_id,password,email,user_type,firstname,lastname,gender,birthday,tel_no,address,validated) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,user.getIdNr());
            pst.setString(2,user.getPassword());
            pst.setString(3,user.getEmail());
            pst.setString(4,user.getUserType());
            pst.setString(5,user.getFirstname());
            pst.setString(6,user.getLastname());
            pst.setString(7,user.getGender());
            pst.setDate(8, (Date) user.getBirthday());
            pst.setString(9,user.getTelNo());
            pst.setString(10,user.getAddress());
            pst.setBoolean(11,user.isValidated());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED: "+user.getIdNr());

    }
    public void deleteUser(String id){
        String sql = "DELETE FROM USERS WHERE user_id =?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED user id:"+id );
    }
    public void editUser(User user){
        String sql = "UPDATE USERS SET password=?,email=?,user_type=?,firstname=?,lastname=?,gender=?,birthday=?,tel_no=?," +
                "address=?,validated=?  WHERE user_id=?";

        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,user.getPassword());
            pst.setString(2,user.getEmail());
            pst.setString(3,user.getUserType());
            pst.setString(4,user.getFirstname());
            pst.setString(5,user.getLastname());
            pst.setString(6,user.getGender());
            pst.setDate(7, (Date) user.getBirthday());
            pst.setString(8,user.getTelNo());
            pst.setString(9,user.getAddress());
            pst.setBoolean(10,user.isValidated());
            pst.setString(11,user.getIdNr());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("EDITED: "+ user.getIdNr());
    }
}
