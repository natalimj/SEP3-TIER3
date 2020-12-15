package sep3.classes.Database;

import sep3.classes.Model.AvailableDay;
import sep3.classes.Model.HospitalDoctor;

import java.sql.*;
import java.util.ArrayList;

public class HospitalDoctorData {

    private  DatabaseConnection db;
    private Connection connection;

    public HospitalDoctorData() {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    public ArrayList<HospitalDoctor> getHospitalDoctor(int doctorId){

        ArrayList<HospitalDoctor> hospitalDoctors=new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT * FROM HOSPITALDOCTOR WHERE doctor_id='" + doctorId + "'");

            HospitalDoctor hospitalDoctor;
            while (rs.next())
            {
                hospitalDoctor = new HospitalDoctor(rs.getInt("doctor_id"),
                        rs.getInt("hospital_id"),
                        rs.getString("department_name"));

                hospitalDoctors.add(hospitalDoctor);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return hospitalDoctors;
    }

    public void addHospitalDoctor(HospitalDoctor hospitalDoctor){
        String sql ="INSERT INTO HOSPITALDOCTOR (doctor_id,hospital_id,department_name) VALUES (?,?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,hospitalDoctor.getDoctorId());
            pst.setInt(2,hospitalDoctor.getHospitalId());
            pst.setString(3,hospitalDoctor.getDeptName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("ADDED: hospital-doctor");
    }

    public void deleteHospitalDoctor(HospitalDoctor hospitalDoctor){
        String sql = "DELETE FROM HOSPITALDOCTOR WHERE doctor_id=? AND hospital_id=? AND department_name=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,hospitalDoctor.getDoctorId());
            pst.setInt(2,hospitalDoctor.getHospitalId());
            pst.setString(3,hospitalDoctor.getDeptName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("DELETED : hospital-doctor" );
    }

    public ArrayList<String> getDepartmentsOfHospital(int hospitalId){

        ArrayList<String> departments=new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            connection.commit();

          ResultSet rs = statement.executeQuery("SELECT DISTINCT department_name FROM HOSPITALDOCTOR WHERE hospital_id='" + hospitalId + "'");

            while (rs.next())
            {
              String department= rs.getString("department_name");
                departments.add(department);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return departments;
    }

    public ArrayList<Integer> getAllDoctorForDept(String hospitalName,String departmentName) {

        ArrayList<Integer> doctorIdList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            connection.commit();

            ResultSet rs = statement.executeQuery("SELECT doctor_id FROM HOSPITALDOCTOR WHERE hospital_id='" + hospitalName + "'AND department_name='" + departmentName + "'");

            int doctorId;
            while (rs.next()) {
                doctorId = rs.getInt("doctor_id");

                doctorIdList.add(doctorId);

            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return doctorIdList;
    }


}
