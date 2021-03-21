import java.util.Date;
import java.util.Scanner;

public class Main {

    // Creating the global scanner to use in different classes
    public static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        // Creating the object to later use the add, update,etc methods
        StudentEnrollment studentEnrollment = new StudentEnrollment();

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
                studentEnrollment.askingInfo();
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
                        studentEnrollment.add();
                    } else if (input.equals("2")) {
                        studentEnrollment.delete();
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

        // Creating a list of courses and add it to the Courses array
        CourseManager courseManager = new CourseManager();

        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12);
        Course course2 = new Course("COSC2429","Introduction to Programming", 12);
        Course course3 = new Course("ISY3414","Practical Database Concept", 12);
        Course course4 = new Course("COSC2500", "Intro to Computer Systems", 12);
        Course course5 = new Course("COSC2534","Building IT Systems", 12);
        Course course6 = new Course("COSC2652", "User-central Design", 12);
        Course course7 = new Course("COSC2430", "Web Programming", 12);
        Course course8 = new Course("COSC2081","Programming 1", 12);
        Course course9 = new Course("COSC2440", "Software Architecture: Design and Implementation", 12);
        Course course10 = new Course("COSC2539","Security in Computing and Information Technology", 12);

        courseManager.add(course1);
        courseManager.add(course2);
        courseManager.add(course3);
        courseManager.add(course4);
        courseManager.add(course5);
        courseManager.add(course6);
        courseManager.add(course7);
        courseManager.add(course8);
        courseManager.add(course9);
        courseManager.add(course10);

        System.out.println(courseManager.toString());

        // Making a list of student and store it in the student Array
        DateManager dateManager = new DateManager();
        StudentManager studentManager = new StudentManager();

        Student student1 = new Student("S3836322","Ngo My Quynh",dateManager.convertDateString("08/03/2001"));
        Student student2 = new Student("S3817852","Nguyen Thanh Thien",dateManager.convertDateString("06/11/2000"));
        Student student3 = new Student("S3836455","Nguyen Thanh Tuan",dateManager.convertDateString("14/12/1995"));
        Student student4 = new Student("S3818102","Pham Cong Minh",dateManager.convertDateString("05/05/2001"));
        Student student5 = new Student("S3818381","Vo Tran Truong Duy",dateManager.convertDateString("08/11/2001"));
        Student student6 = new Student("S3754105","Nguyen Dang Lam Phuong",dateManager.convertDateString("25/07/2000"));
        Student student7 = new Student("S3812649","Nguyen Pham Quoc Minh",dateManager.convertDateString("24/08/2001"));
        Student student8 = new Student("S3740853","Vu Phuong Thao",dateManager.convertDateString("18/09/1999"));
        Student student9 = new Student("S3804690","Nguyen Ngoc Dang Hung",dateManager.convertDateString("20/08/2000"));
        Student student10 = new Student("S3689902","Nguyen Cao Minh Duc",dateManager.convertDateString("21/02/1999"));

        studentManager.add(student1);
        studentManager.add(student2);
        studentManager.add(student3);
        studentManager.add(student4);
        studentManager.add(student5);
        studentManager.add(student6);
        studentManager.add(student7);
        studentManager.add(student8);
        studentManager.add(student9);
        studentManager.add(student10);

        System.out.println(studentManager.toString());

        //menu();
    }
}
