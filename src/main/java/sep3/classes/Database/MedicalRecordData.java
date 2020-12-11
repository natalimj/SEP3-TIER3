package sep3.classes.Database;

import sep3.classes.Model.MedicalRecord;


import java.sql.*;
import java.util.ArrayList;

public class MedicalRecordData {


    private  DatabaseConnection db;
    private Connection connection;

    public MedicalRecordData()  {
        try {
            db = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = db.getConnection();
    }

    public MedicalRecord getMedicalRecord(int id){
        ArrayList<MedicalRecord> medicalRecords=new ArrayList<>();
        MedicalRecord medicalRecord=null;

        try
        {
            Statement statement = connection.createStatement();
            connection.commit();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM MEDICALRECORDS WHERE patient_id='" + id + "'");
            while (rs.next())
            {
                medicalRecord=new MedicalRecord(rs.getInt("patient_id"),
                        rs.getBytes("content"));


                medicalRecords.add(medicalRecord);
                medicalRecord= medicalRecords.get(0);

            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return medicalRecord;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord)
    {
        String sql ="INSERT INTO MEDICALRECORDS (patient_id,content) VALUES (?,?);";
        PreparedStatement pst= null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,medicalRecord.getPatientId());
            pst.setBytes(2,medicalRecord.getContent());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.operation(pst);
        System.out.println("ADDED: medical record" +medicalRecord.getPatientId());

    }


    public void editMedicalRecord(MedicalRecord medicalRecord){
        byte[] existing = null;
        try {
            Statement statement = connection.createStatement();
            connection.commit();
            ResultSet rs = statement
                    .executeQuery("SELECT content FROM MEDICALRECORDS WHERE patient_id='" + medicalRecord.getPatientId() + "'");

            while (rs.next()) {
                existing = rs.getBytes("content");
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        //byte[] c = new byte[existing.length+medicalRecord.getContent().length];
        String content1 ="";
        if(existing!=null)
            if(existing.length>0){
            content1=new String(existing);
        }
        System.out.println(content1);
        String content2= new String(medicalRecord.getContent());
        System.out.println(content2);
        String finalCont = content1+"\n---------------"+content2;
        System.out.println(finalCont);

        String sql = "UPDATE MEDICALRECORDS SET content=? WHERE patient_id=?";

        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setBytes(1,finalCont.getBytes());
            pst.setInt(2,medicalRecord.getPatientId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.operation(pst);
        System.out.println("EDITED: medical record" +medicalRecord.getPatientId());
    }
}
