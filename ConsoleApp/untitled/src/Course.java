import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private int number_of_credits;

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
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number_of_credits=" + number_of_credits +
                '}';
    }
}
