package sep3.classes.Model;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String summary;

    public Appointment() {
    }


    public Appointment(int appointmentId, int patientId, int doctorId, LocalDateTime startTime, LocalDateTime endTime, String summary) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.summary = summary;
    }

    public Appointment(int patientId, int doctorId, LocalDateTime startTime, LocalDateTime endTime, String summary) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.summary = summary;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}


