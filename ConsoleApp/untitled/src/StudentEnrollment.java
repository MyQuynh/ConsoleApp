import java.util.ArrayList;

public class StudentEnrollment implements StudentEnrollmentManager {
    private Student student;
    private ArrayList<Course> courses;
    private String semester;

    protected ArrayList<StudentEnrollment> studentEnrollments = new ArrayList<>();

    public StudentEnrollment(){}

    public StudentEnrollment(Student student, ArrayList<Course> courses, String semester) {
        this.student = student;
        this.courses = courses;
        this.semester = semester;
    }

    StudentManager studentManager = Main.studentManager;
    CourseManager courseManager = Main.courseManager;

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

//    public void askingInfo(){
//
//        DateManager dateManager = new DateManager();
//
//        System.out.print("Enter the id:");
//        String studentId = Main.scanner.nextLine();
//
//        System.out.print("Enter the name");
//        String studentName = Main.scanner.nextLine();
//
//        System.out.print("Enter the dateOfBirth");
//        String dateOfBirth = Main.scanner.nextLine();
//
//
//        Student student = new Student(studentId,studentName,dateManager.convertDateString(dateOfBirth));
//
//        System.out.print("Enter the semester:");
//        String semester = Main.scanner.nextLine();
//
//
//        int courseCheck = 0;
//        ArrayList<Course> courses = new ArrayList<>();
//        while(courseCheck == 0){
//            System.out.println("Enter the course");
//            Course course;
//            System.out.print("Please enter the  course id:");
//            String courseId = Main.scanner.nextLine();
//            System.out.print("Enter the name of the course: ");
//            String courseName = Main.scanner.nextLine();
//            System.out.print("Enter the numbers of credits:");
//            int numberOfCredits = Main.scanner.nextInt();
//            course = new Course(courseId,courseName,numberOfCredits);
//
//            Main.scanner.nextLine();
//
//            courses.add(course);
//
//            System.out.print("Do you want to add another course ? Y/n");
//            String input = Main.scanner.nextLine();
//            while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
//                System.out.print("Invalid input, please enter only Y/y or N/n");
//                input = Main.scanner.nextLine();
//            }
//
//            if (input.equalsIgnoreCase("N")){
//                courseCheck = 1;
//            }
//        }
//        StudentEnrollment studentEnrollment = new StudentEnrollment(student, courses, semester);
//        this.studentEnrollments.add(studentEnrollment);
//
//        Main.scanner.close();
//    }

    // Adding student enrollment for 1 semester
    @Override
    public void add() {

        // Check if the student is on the system
        String studentID = studentManager.validStudent();
        ArrayList<Course> studentCourse = new ArrayList<>();

        Main.scanner.nextLine();

        System.out.print("Please enter the semester you want to enroll: ");
        String semester = Main.scanner.nextLine();

        // Check if the course is available in the system
        while (true){
            String courseId = courseManager.validCourse();
            studentCourse.add(courseManager.getCourseById(courseId));
            System.out.print("Do you want to add another course ? (Y/n) ");
            String input = Main.scanner.nextLine();
            while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
                System.out.print("Invalid input, please enter only Y/y or N/n");
                input = Main.scanner.nextLine();
            }
            if (input.equalsIgnoreCase("n")){
                break;
            }
        }

        StudentEnrollment studentEnrollment = new StudentEnrollment(studentManager.getStudentById(studentID), studentCourse, semester);
        this.studentEnrollments.add(studentEnrollment);
    }

    // Adding Course for Student in Specific Semester
    public void addingCourseForStudent(){
        String studentId = studentManager.validStudent();
        System.out.println("Please enter the semester: ");
        String semester = Main.scanner.nextLine();
        displayCoursesOfStudent(studentId, semester);
        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse();

            // Checking if the student had already enrolled the course before
            while (checkStudentRecord(studentId, courseStudent)) {
                System.out.println("The course have been already enrolled");
                System.out.println("Please try again");
                courseStudent = courseManager.validCourse();
            }

            for(StudentEnrollment studentEnrollment: this.studentEnrollments){
                if(studentEnrollment.getStudent().getId().equals(studentId) && studentEnrollment.getSemester().equals(semester)){
                    studentEnrollment.courses.add(courseManager.getCourseById(courseStudent));
                }
            }

            System.out.print("Do you want to add another course (Y/n) ?: ");
            String input = Main.scanner.nextLine();
            while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
                System.out.print("Invalid input, please enter only Y/y or N/n");
                input = Main.scanner.nextLine();
            }
            if (input.equalsIgnoreCase("n")){
                break;
            }

        }
    }

    // Delete courses for student
    public void deleteCourseForStudent(){
        String studentId = studentManager.validStudent();
        System.out.println("Please enter the semester: ");
        String semester = Main.scanner.nextLine();
        displayCoursesOfStudent(studentId, semester);
        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse();

            // Checking if the student had already enrolled the course before
            while (checkStudentRecord(studentId, courseStudent)) {
                System.out.println("Student does not enroll this course before");
                System.out.println("Please try again");
                courseStudent = courseManager.validCourse();
            }

            for(StudentEnrollment studentEnrollment: this.studentEnrollments){
                if(studentEnrollment.getStudent().getId().equals(studentId) && studentEnrollment.getSemester().equals(semester)){
                    studentEnrollment.courses.remove(courseManager.getCourseById(courseStudent));
                }
            }

            System.out.print("Do you want to remove another course (Y/n) ?: ");
            String input = Main.scanner.nextLine();
            while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
                System.out.print("Invalid input, please enter only Y/y or N/n");
                input = Main.scanner.nextLine();
            }
            if (input.equalsIgnoreCase("n")){
                break;
            }

        }

    }

    // Display course list of that Student in specific semester
    public void displayCoursesOfStudent(String studentId, String semester){
        System.out.println("STUDENT ENROLLMENT IN " + semester.toUpperCase()+"  OF "+studentId.toUpperCase());
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));
        for (StudentEnrollment studentEnrollment : this.studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
                for (Course course : studentEnrollment.getCourses()) {
                    System.out.println("| " + String.format("%1$-9s", course.getId()) + " | " + String.format("%1$-50s", course.getName()) + " | " + String.format("%1$18s", course.getNumber_of_credits()) + " | ");
                }
            }
        }
    }

    // Check if the course is in the student enrollment with the same semester
    public boolean checkStudentRecord(String studentId, String courseId){
        for (StudentEnrollment studentEnrollment : this.studentEnrollments){
            if (studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId)){
                for(Course course : studentEnrollment.getCourses()){
                    if(course.getId().equals(courseId)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void update() {
        // Check if the student ID is exist
        String studentId = getStudentById();
        String semester = checkSemester();
        int flag = 0;

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

    public void getStudentByCourse(){
       String studentCourse = checkCourse();
       String semester = checkSemester();
       int flag = 0;

        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            for (Course studentCourseI : studentEnrollment.getCourses()){
                if (studentCourseI.getId().equals(studentCourse) && studentEnrollment.getSemester().equals(semester)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1){
                System.out.println(studentEnrollment.getStudent());
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
            System.out.print("Please enter the student id: ");
            studentId = Main.scanner.nextLine();

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

    public String checkSemester(){
        int flag = 0;
        String studentSemester = "";
        while (flag == 0){
            System.out.println("Please enter the student id");
            studentSemester = Main.scanner.next();

            for (StudentEnrollment studentEnrollment : studentEnrollments){
                if ((studentEnrollment.getSemester().equals(studentSemester))) {
                    flag = 1;
                    break;
                }
            }

            if(flag != 1){
                System.out.println("Please enter again");
            }
        }
        return studentSemester;
    }

    public String checkCourse(){
        int flag = 0;
        String studentCourse = "";
        while(flag == 0){
            System.out.println("Please enter the student course");
            studentCourse = Main.scanner.next();

            for (StudentEnrollment studentEnrollment : studentEnrollments){
                for(Course studentCourseI : studentEnrollment.getCourses()){
                    if(studentCourseI.getId().equals(studentCourse)){
                        flag = 1;
                        break;
                    }
                }
            }

            if(flag != 1){
                System.out.println("Please enter again");
            }

        }
        return studentCourse;
    }

    public void getCoursesBySemester(){
        String semester = checkSemester();
        int flag = 0;

        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            for (Course studentCourseI : studentEnrollment.getCourses()){
                if (studentEnrollment.getSemester().equals(semester)) {
                    System.out.println(studentCourseI);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "studentEnrollments=" + this.studentEnrollments;
    }

//    public StudentEnrollment displayStudentEnrollment(String studentId, String semester, String courseId){
//        StudentEnrollment studentEnrollment = new StudentEnrollment();
//        // Display all the student enrollment list
//        if (studentId.equals("") && semester.equals("") && courseId.equals("")){
//            System.out.println("huhu");
//        }
//        // Display the student Enrollment by student Id
//        else if (semester.equals("") && courseId.equals("")){
//            System.out.println("hwdh");
//        }
//        // Display the student enrollment by semester
//        else if (studentId.equals("") && courseId.equals("")){
//            System.out.println("dhdjheu");
//        }
//        // Display the student enrollment by student Id and semester
//        else if (courseId.equals("")){
//            for (StudentEnrollment studentEnrollment1 : this.studentEnrollments){
//                if(studentEnrollment1.getStudent().getId().equals(studentId) && studentEnrollment1.getSemester().equals(semester)){
//                    studentEnrollment = studentEnrollment1;
//                }
//            }
//        }
//        return studentEnrollment;
//    }


}
