package sep3.classes;

import sep3.classes.Database.DataHandler;
import sep3.classes.Database.DatabaseHandler;
import sep3.classes.Model.User;

import java.sql.Date;


public class RunTest {
    public static void main(String[] args) {
        DataHandler dataHandler=new DatabaseHandler();

        User user= null;
            user = new User("aaaaaaa","ddd","dddd","oewfkjpoe","kfjoe2fk","kvpij","kfjræ", Date.valueOf("1984-12-01"),"fvjælv","lfmælevl",true);
        dataHandler.addUser(user);
    }

}
