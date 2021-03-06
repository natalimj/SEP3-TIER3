package sep3.classes.Database;

import sep3.classes.Model.Message;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MessageData {
    private  DatabaseConnection db;
    private Connection connection;
    private static final Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    public MessageData()  {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages =new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM MESSAGES;");

            Message message;
            while (rs.next())
            {
                Timestamp ts = rs.getTimestamp("time_sent",utc);

                LocalDateTime localDt = null;
                if (ts != null)
                    localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);

                message = new Message( rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        localDt,
                        rs.getString("message_type"),
                        rs.getString("text"));

                messages.add(message);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return messages;
    }

    public ArrayList<Message> getUserMessages(int userId){
        ArrayList<Message> messages =new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM MESSAGES WHERE receiver_id='" + userId + "'");

            Message message;
            while (rs.next())
            {
                Timestamp ts = rs.getTimestamp("time_sent",utc);

                LocalDateTime localDt = null;
                if (ts != null)
                    localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);

                message = new Message(
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        localDt,
                        rs.getString("message_type"),
                        rs.getString("text"));

                messages.add(message);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return messages;

    }

    public void addMessage(Message message){

        String sql ="INSERT INTO MESSAGES (sender_id,receiver_id,time_sent,message_type,text) VALUES (?,?,?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,message.getSenderId());
            pst.setInt(2,message.getReceiverId());
            Timestamp ts = new Timestamp(message.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli());
            pst.setTimestamp(3,ts,utc);
            pst.setString(4,message.getMessageType());
            pst.setString(5,message.getText());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED: "+message.getTimestamp());
    }

    public void deleteMessage(Message message){
        String sql = "DELETE FROM MESSAGES WHERE sender_id =? AND receiver_id=? AND time_sent=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,message.getSenderId());
            pst.setInt(2,message.getReceiverId());
            Timestamp ts = new Timestamp(message.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli());
            pst.setTimestamp(3,ts,utc);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED : msg" );
    }

}
