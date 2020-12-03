package sep3.classes.Model;

import java.io.Serializable;

public class MedicalRecord implements Serializable {

    private int patientId;
    private byte[] content;

    public MedicalRecord() {
    }

    public MedicalRecord(int patientId, byte[] content) {
        this.patientId = patientId;
        this.content = content;
    }

    public MedicalRecord(byte[] content) {
        this.content = content;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "patientId=" + patientId +
                ", content=" + content +
                '}';
    }
}
