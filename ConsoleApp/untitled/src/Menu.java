import java.io.IOException;
import java.util.InputMismatchException;

public class Menu implements Valid {

    @Override
    public int checkInput(int min, int max) {
        int option = 0;

        while (true) {
            try {
                System.out.print("Please enter your option: ");
                option = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (option < min || option > max) {
                    System.out.println("The option has to be between "+ min+" and "+max);
                    System.out.println("Please try again");
                } else if (String.valueOf(option).equalsIgnoreCase("")){
                    System.out.println("The option cannot be empty");
                    System.out.println("Please try again");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                Main.scanner.nextLine();
                System.out.println("Please enter only number");
                System.out.println("Please try again");
            }
        }
        return option;
    }

    public int updateMenu(){
            // Making the interface for update enrollment menu
        System.out.println("---------------------------------------");
        System.out.println("UPDATE ENROLLMENT MENU");
        System.out.println("1. Adding a new course for student");
        System.out.println("2. Delete a new course for student");
        System.out.println("3. Return to main menu");

        // Get the input from the users
        return checkInput(1, 3);
    }

    public int displayMenu(){
            // Making the interface for update enrollment menu
        System.out.println("---------------------------------------");
        System.out.println("DISPLAY ENROLLMENT MENU");
        System.out.println("1. Display all courses by student");
        System.out.println("2. Display all students by course");
        System.out.println("3. Display all the course in the semester");
        System.out.println("4. Return to main menu");

        // Get the input from the users
        return checkInput(1,4);
    }

    public int mainMenu(){
        // Creating the object to later use the add, update,etc methods
        //Model.StudentEnrollment studentEnrollment = new Model.StudentEnrollment();

        // Making the interface of main menu
        System.out.println("---------------------------------------");
        System.out.println("MAIN MENU");
        System.out.println("1. Adding a student enrollment");
        System.out.println("2. Update the student enrollment");
        System.out.println("3. Display the student enrollment in many ways");
        System.out.println("4. Display a student enrollment");
        System.out.println("5. Display all student enrollments");
        System.out.println("6. Delete a student enrollment");
        System.out.println("7. Exit");

        return checkInput(1,7);

    }

}
