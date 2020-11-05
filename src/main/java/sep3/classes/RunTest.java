package sep3.classes;

import sep3.classes.Database.DataHandler;
import sep3.classes.Database.DatabaseHandler;
import sep3.classes.Model.Appointment;
import sep3.classes.Model.Rating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;



public class RunTest {
    public static void main(String[] args) {
        DataHandler dataHandler=new DatabaseHandler();

/*
        Appointment appointment= null;
        try {

            appointment = new Appointment(9,10,new Date(), new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataHandler.addAppointment(appointment);
        */

       // Rating rating=new Rating(5,10,1);
        //dataHandler.addRating(rating);
       // dataHandler.editRating(rating);

        System.out.println(dataHandler.getAvgRating(1));

    }
}
