import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;

public class StudentEnrollment implements StudentEnrollmentManager {
    private Student student;
    private ArrayList<Course> courses;
    private String semester;

    protected ArrayList<StudentEnrollment> studentEnrollments;

    public StudentEnrollment(Student student, ArrayList<Course> courses, String semester) {
        this.student = student;
        this.courses = courses;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void askingInfo(){
        StudentEnrollment studentEnrollment;
        Student student = null;
        System.out.println("Enter the id:");
        String studentId = Main.scanner.next();

        System.out.println("Enter the name");
        String studentName = Main.scanner.next();

        System.out.println("Enter the dateOfBirth");
        String dateOfBirth = Main.scanner.next();


        assert false;
        student = new Student(studentId,studentName,student.convertDateString(dateOfBirth));

        System.out.println("Enter the semester:");
        String semester = Main.scanner.next();


        int courseCheck = 0;
        ArrayList<Course> courses = new ArrayList<>();
        while(courseCheck == 0){
            System.out.println("Enter the course");
            Course course;
            System.out.println("Please enter the  course id:");
            String courseId = Main.scanner.next();
            System.out.println("Enter the name of the course: ");
            String courseName = Main.scanner.next();
            System.out.println("Enter the numbers of credits:");
            int numberOfCredits = Main.scanner.nextInt();
            course = new Course(courseId,courseName,numberOfCredits);

            courses.add(course);

            System.out.println("Do you want to add another course ? Y/n");
            String input = Main.scanner.next();
            while(!input.equalsIgnoreCase("Y") || !input.equalsIgnoreCase("N")){
                System.out.println("Invalid input, please enter only Y/y or N/n");
                input = Main.scanner.next();
            }

            if (input.equalsIgnoreCase("N")){
                courseCheck = 1;
            }
        }
        studentEnrollment = new StudentEnrollment(student, courses, semester);
        this.studentEnrollments.add(studentEnrollment);

    }

    @Override
    public void add() {

    }

    @Override
    public void update() {
        // Check if the student ID is exist
        String studentId = getStudentById();
        int flag = 0;
        System.out.println("Asking for the semester");
        String semester = Main.scanner.next();
        System.out.println("Student ID" + studentId);
        System.out.println("Semester " + semester);
        System.out.println("Course list");
        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            if (studentEnrollment.getStudentById().equals(studentId) && studentEnrollment.getSemester().equals(semester)){
                System.out.println(studentEnrollment.getCourses());
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("List course is empty, please try again");
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }

    public String getStudentById(){
        int flag = 0;
        String studentId = "";
        while (flag == 0){
            System.out.println("Please enter the student id");
            studentId = Main.scanner.next();

            for (StudentEnrollment studentEnrollment : studentEnrollments){
                if ((studentEnrollment.getStudent().getId().equals(studentId))) {
                    flag = 1;
                    break;
                }
            }

            if(flag != 1){
                System.out.println("Please enter again");
            }
        }
        return studentId;
    }
}
