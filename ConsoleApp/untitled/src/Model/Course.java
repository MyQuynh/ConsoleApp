package Model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Course implements Model {
    private String id;
    private String name;
    private int number_of_credits;
    private String semester;

    public List<String> propertyCourse = Arrays.asList("COURSE_ID", "COURSE_NAME", "NUMBER_OF_CREDITS","SEMESTER");


    public Course(String id, String name, int number_of_credits, String semester) {
        this.id = id;
        this.name = name;
        this.number_of_credits = number_of_credits;
        this.semester = semester;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number_of_credits=" + number_of_credits +
                ", semester='" + semester + '\'' +
                '}';
    }

    @Override
    public String toCSV() {
        return this.getId()+","+this.getName()+","+this.getNumber_of_credits()+","+this.getSemester()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                number_of_credits == that.number_of_credits &&
                semester.equals(that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number_of_credits, semester);
    }
}
