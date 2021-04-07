import Model.Course;
import Model.Student;
import Model.StudentEnrollment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    // Creating the global scanner to use in different classes
    //public static final InputStream STDIN = System.in;
    public static Scanner scanner = new Scanner(System.in);


//    public static void menu(){
//        // Creating the object to later use the add, update,etc methods
//        //Model.StudentEnrollment studentEnrollment = new Model.StudentEnrollment();
//
//        while (true){
//
//            // Making the interface of main menu
//            System.out.println("MAIN MENU");
//            System.out.println("1. Adding a student enrollment");
//            System.out.println("2. Update the student enrollment");
//            System.out.println("3. Display the student enrollment");
//            System.out.println("4. Delete a student enrollment");
//            System.out.println("5. Exit");
//
//            // Get the option from user
//            // TODO: Check if the option is valid (Not done)
//            System.out.print("Your option: ");
//            String input = Main.scanner.nextLine();
//
//            if (input.equals("1")){
//                // Call out the adding student enrollment methods
//                studentMemoryManager.add();
//            } else if (input.equals("2")) {
//                while (true){
//
//                    // Making the interface for update enrollment menu
//                    System.out.println("UPDATE ENROLLMENT MENU");
//                    System.out.println("1. Adding a new course for student");
//                    System.out.println("2. Delete a new course for student");
//                    System.out.println("3. Return to main menu");
//                    System.out.print("Your option: ");
//
//                    // Get the input from the users
//                    input = Main.scanner.nextLine();
//                    if (input.equals("1")){
//                        studentMemoryManager.addingCourseForStudent();
//                    } else if (input.equals("2")) {
//                        studentMemoryManager.deleteCourseForStudent();
//                    } else {
//                        // Break the while loop to return back to the main menu
//                        break;
//                    }
//                }
//            } else if (input.equals("3")){
//                while(true){
//                    // Making the interface for update enrollment menu
//                    System.out.println("DISPLAY ENROLLMENT MENU");
//                    System.out.println("1. Display all courses by student");
//                    System.out.println("2. Display all students by course");
//                    System.out.println("3. Display all the course in the semester");
//                    System.out.println("4. Return to main menu");
//                    System.out.print("Your option: ");
//
//                    // Get the input from the users
//                    input = Main.scanner.nextLine();
//                    if (input.equals("1")){
//                        studentMemoryManager.getCourseByStudent();
//                    } else if (input.equals("2")) {
//                        studentMemoryManager.getStudentByCourse();
//                    } else if (input.equals("3")){
//                        studentMemoryManager.getCourseBySemester();
//                    }
//                    else {
//                        // Break the while loop to return back to the main menu
//                        break;
//                    }
//                }
//
//            } else if (input.equals("4")){
//                studentMemoryManager.getAll();
//            }
//            else {
//                System.out.println("Thank you for using the system, have a good day");
//                break;
//            }
//        }
//
//    }

    public static void main(String[] args) {


        StudentManager studentManager = new StudentManager();
        //System.out.println(studentManager.validStudent());




        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();

        //studentMemoryManager.getAll();
        //studentMemoryManager.createList();
        studentMemoryManager.execute();

        //courseManager.showCoursesList();
        //studentMemoryManager.getAll();
       // Main.scanner.close();
    }
}
