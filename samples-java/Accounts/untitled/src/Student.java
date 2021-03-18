import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Student {
    private String id;
    private String name;
    private Date birthdate;

    public Student(String id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date convertDateString(String sDate1){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date= formatDate.parse(sDate1);
            return date;
        } catch (ParseException e) {
            System.out.println("Wrong date format, please enter again (dd/MM/yyyy)");;
        }
        return null;

    }


}
