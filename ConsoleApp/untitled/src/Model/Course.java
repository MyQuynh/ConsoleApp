package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course implements AdditionService {
    private String id;
    private String name;
    private int number_of_credits;

    public List<String> propertyCourse = Arrays.asList("COURSE_ID", "COURSE_NAME", "NUMBER_OF_CREDITS");

    public Course(){}

    public Course(String id, String name, int number_of_credits) {
        this.id = id;
        this.name = name;
        this.number_of_credits = number_of_credits;
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

    public int getNumber_of_credits() {
        return number_of_credits;
    }

    public void setNumber_of_credits(int number_of_credits) {
        this.number_of_credits = number_of_credits;
    }


    @Override
    public String toString() {
        return "Model.Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number_of_credits=" + number_of_credits +
                '}';
    }

    @Override
    public String toCSV() {
        return this.getId()+","+this.getName()+","+this.getNumber_of_credits()+"\n";
    }


}
