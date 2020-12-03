package sep3.classes.Model;


import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    private int patientId;
    private int doctorId;
    private Date appointmentTime;
    private Date appointmentDate;
    private String summary;

    public Appointment() {
    }

    public Appointment(int patientId, int doctorId, Date appointmentTime, Date appointmentDate, String summary) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.summary = summary;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}


