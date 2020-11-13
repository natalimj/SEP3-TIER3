package sep3.classes.Database;

/*

CREATE TABLE Appointments (patient_id DECIMAL(10),
						 doctor_id DECIMAL(10),
						 appointment_time TIME,
						 appointment_date DATE,

						  public Appointment(int patientId, int doctorId, Time appointmentTime, Date appointmentDate)
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
