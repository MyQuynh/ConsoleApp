package Model;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Student implements AdditionService {
    private String id;
    private String name;
    private String birthdate;

    public List<String> propertyStudent = Arrays.asList("STUDENT_ID", "STUDENT_NAME", "DATE_OF_BIRTH");


    public Student(String id, String name, String birthdate) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Model.Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    @Override
    public String toCSV() {
        return this.getId()+","+this.getName()+","+this.getBirthdate()+"\n";
    }
}
