package sep3.classes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost/SEP3";
    private String username = "postgres";
    private String password = "134679Da";

    private DatabaseConnection() throws SQLException
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Database connected");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }
    public Connection getConnection()
    {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new DatabaseConnection();
        }
        else if (instance.getConnection().isClosed())
        {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public void operation(PreparedStatement pst)
    {
        try
        {
            pst.executeUpdate();
            pst.close();
            connection.commit();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}
