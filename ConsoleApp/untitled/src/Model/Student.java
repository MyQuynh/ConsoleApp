package Model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Student implements Model {
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

//    @Override
//    public String toString() {
//        return "Model.Student{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", birthdate=" + birthdate +
//                '}';
//    }

    @Override
    public String toCSV() {
        return this.getId()+","+this.getName()+","+this.getBirthdate()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                birthdate.equals(that.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate);
    }
}
