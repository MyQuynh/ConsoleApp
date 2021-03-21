import java.util.Scanner;

public class Main {

    // Creating the global scanner to use in different classes
    public static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        // Creating the object to later use the add, update,etc methods
        StudentEnrollment studentEnrollment = new StudentEnrollment();

        // Making the interface menu
        System.out.println("--------------MAIN MENU---------------");
        System.out.println("1. Enroll a student for 1 semester");
        System.out.println("2. Update the enrollment for 1 semester");
        System.out.println("3. Get courses by student in 1 semester");
        System.out.println("4. Get all the students by course in 1 semester");
        System.out.println("5. Get all the course in 1 semester");
        System.out.println("---------------------------------------");
        String input = Main.scanner.next();
        if (input.equals("1")){
            studentEnrollment.askingInfo();
        } else if (input.equals("2")) {
            System.out.println("UPDATE ENROLLMENT MENU");
            System.out.println("1. Adding a new course for student");
            System.out.println("2. Delete a new course for student");
            System.out.print("Your option: ");
            input = Main.scanner.next();
            if (input.equals("1")){
                studentEnrollment.add();
            } else {
                studentEnrollment.delete();
            }
        } else if (input.equals("3")){
            studentEnrollment.update();
        } else if (input.equals("4")){
            studentEnrollment.getStudentByCourse();
        } else {
            studentEnrollment.getCoursesBySemester();
        }


    }

    public static void main(String[] args){
        menu();
    }
}
