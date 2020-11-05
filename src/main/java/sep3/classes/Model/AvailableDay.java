package sep3.classes.Model;

import java.util.Date;

public class AvailableDay {
    private int doctorId;
    private Date availableDate;
    private Date startTime;
    private Date endTime;
    private int appointmentNr;

    public AvailableDay() {
    }

    public AvailableDay(int doctorId, Date availableDate, Date startTime, Date endTime, int appointmentNr) {
        this.doctorId = doctorId;
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentNr = appointmentNr;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getAppointmentNr() {
        return appointmentNr;
    }

    public void setAppointmentNr(int appointmentNr) {
        this.appointmentNr = appointmentNr;
    }

    @Override
    public String toString() {
        return "AvailableDay{" +
                "doctorId=" + doctorId +
                ", availableDate=" + availableDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", appointmentNr=" + appointmentNr +
                '}';
    }
}
