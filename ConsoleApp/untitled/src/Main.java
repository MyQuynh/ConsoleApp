import java.util.Date;
import java.util.Scanner;

public class Main {

    // Creating the global scanner to use in different classes
    public static Scanner scanner = new Scanner(System.in);
    public static CourseManager courseManager = new CourseManager();
    public static StudentManager studentManager = new StudentManager();
    public static StudentEnrollment studentEnrollment = new StudentEnrollment();

    public static void menu(){
        // Creating the object to later use the add, update,etc methods
        //StudentEnrollment studentEnrollment = new StudentEnrollment();

        while (true){

            // Making the interface of main menu
            System.out.println("MAIN MENU");
            System.out.println("1. Enroll a student for 1 semester");
            System.out.println("2. Update the enrollment for 1 semester");
            System.out.println("3. Get courses by student in 1 semester");
            System.out.println("4. Get all the students by course in 1 semester");
            System.out.println("5. Get all the course in 1 semester");
            System.out.println("6. Exit");

            // Get the option from user
            System.out.print("Your option: ");
            String input = Main.scanner.nextLine();

            if (input.equals("1")){

                // Call out the adding student enrollment methods
                studentEnrollment.add();
            } else if (input.equals("2")) {
                while (true){

                    // Making the interface for update enrollment menu
                    System.out.println("UPDATE ENROLLMENT MENU");
                    System.out.println("1. Adding a new course for student");
                    System.out.println("2. Delete a new course for student");
                    System.out.println("3. Return to main menu");
                    System.out.print("Your option: ");

                    // Get the input from the users
                    input = Main.scanner.nextLine();
                    if (input.equals("1")){
                        studentEnrollment.addingCourseForStudent();
                    } else if (input.equals("2")) {
                        studentEnrollment.deleteCourseForStudent();
                    } else {
                        // Break the while loop to return back to the main menu
                        break;
                    }
                }
            } else if (input.equals("3")){
                studentEnrollment.update();
            } else if (input.equals("4")){
                studentEnrollment.getStudentByCourse();
            } else if (input.equals("5")) {
                studentEnrollment.getCoursesBySemester();
            } else {
                System.out.println("Thank you for using the system, have a good day");
                break;
            }
        }

    }

    public static void main(String[] args){
        System.out.println(studentEnrollment.toString());
        menu();
        //courseManager.showCoursesList();
        System.out.println(studentEnrollment.toString());
    }
}
