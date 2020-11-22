package sep3.classes.Model;

public class HospitalDoctor {

    private int doctorId;
    private int hospitalId;
    private String deptName;

    public HospitalDoctor() {
    }

    public HospitalDoctor(int doctorId, int hospitalId, String deptName) {
        this.doctorId = doctorId;
        this.hospitalId = hospitalId;
        this.deptName = deptName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "HospitalDoctor{" +
                "doctorId=" + doctorId +
                ", hospitalId=" + hospitalId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
