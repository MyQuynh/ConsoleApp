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

    public void askingInfo(){
        System.out.println("Enter the id:");
        String id = Main.scanner.next();

        System.out.println("Enter the semester:");
        String semester = Main.scanner.next();

        int courseCheck = 0;
//        Course course[] = new Co;
        while(courseCheck != 0){
            System.out.println("Enter the course:");
            String course = Main.scanner.next();

            System.out.println("Do you want to add another course ? Y/n");
            String input = Main.scanner.next();
            if (input == "Y"){
                
            } else {
                courseCheck = 1;
            }
        }

    }
}
