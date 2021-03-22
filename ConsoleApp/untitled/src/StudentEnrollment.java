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
    // Case 1: Check if the student enrollment with that semester has been already in the system (Done)
    // Case 2: Checking the adding course is already has been added (Done)
    // TODO: Case 3: Asking if the user want to add another student enrollment
    @Override
    public void add() {

        // Check if the student is on the system
        String studentID = studentManager.validStudent();
        ArrayList<Course> studentCourse = new ArrayList<>();

        //Main.scanner.nextLine();

        System.out.print("Please enter the semester you want to enroll: ");
        String semester = Main.scanner.nextLine();

        // Check if the student enrollment is already in system
        while (checkStudentEnrollment(studentID, semester)){
            System.out.println("This enrollment is already on the system");
            System.out.println("Please try again");
            studentID = studentManager.validStudent();
            System.out.print("Please enter the semester you want to enroll: ");
            semester = Main.scanner.nextLine();
        }

        // Check if the course is available in the system
        while (true){
            String courseId = courseManager.validCourse();

            // Checking if the student had already enrolled the course before
            while(studentCourse.contains(courseManager.getCourseById(courseId))){
                System.out.println("The course has been already enrolled");
                System.out.println("Please try again");
                courseId = courseManager.validCourse();
            }

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

    // Delete the a student enrollment
    // Case 1: The student enrollment is not recognized by the system
    // TODO: Case 2: Asking if the user want to delete another student enrollment
    @Override
    public void delete() {
        String studentId = getStudentById();
        System.out.println("Please enter the semester");
        String semester = Main.scanner.nextLine();

        // check if the student enrollment is on the system
        while(!checkStudentEnrollment(studentId, semester)){
            System.out.println("This student enrollment is not recognized by the system");
            System.out.println("Please try again");
            studentId = getStudentById();
            System.out.println("Please enter the semester");
            semester = Main.scanner.nextLine();
        }

        for (StudentEnrollment studentEnrollment : this.studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
                this.studentEnrollments.remove(studentEnrollment);
            }
        }

    }

    // Adding Course for Student in Specific Semester
    // Case 1: Check if the course had been enrolled before (Done)
    public void addingCourseForStudent(){
        String studentId = studentManager.validStudent();
        System.out.print("Please enter the semester: ");
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

    // Delete courses for student in Specific Semester
    // Case 1: Check if the course had been already enrolled (Done)
    public void deleteCourseForStudent(){
        String studentId = studentManager.validStudent();
        System.out.print("Please enter the semester: ");
        String semester = Main.scanner.nextLine();
        displayCoursesOfStudent(studentId, semester);
        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse();

            // Checking if the student had already enrolled the course before
            while (!checkStudentRecord(studentId, courseStudent)) {
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
        System.out.println("STUDENT ENROLLMENT IN " + semester.toUpperCase()+" OF "+studentId.toUpperCase());
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));
        for (StudentEnrollment studentEnrollment : this.studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
                for (Course course : studentEnrollment.getCourses()) {
                    System.out.println("| " + String.format("%1$-9s", course.getId()) + " | " + String.format("%1$-50s", course.getName()) + " | " + String.format("%1$18s", course.getNumber_of_credits()) + " | ");
                }
            }
        }
    }

    // Check if the course is in the student enrollment
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

    // Check if the student enrollment(by id and semester) is already in the system
    public boolean checkStudentEnrollment(String studentId, String semester){
        for (StudentEnrollment studentEnrollment : this.studentEnrollments){
            if (studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
                return true;
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

    // Display the student by the given course
    public void getStudentByCourse(){
        DateManager dateManager = new DateManager();

        // Check if any student has enroll this course
        String studentCourse = checkCourse();

        System.out.println("---------------------------------------------------------------");
        System.out.println("LIST OF STUDENTS ENROLL "+ studentCourse.toUpperCase()+" COURSE");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "STUDENT_NAME") + " | " + String.format("%1$18s", "STUDENT_DOB") + " | "));

        int flag = 0;
        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            for (Course studentCourseI : studentEnrollment.getCourses()){
                if (studentCourseI.getId().equals(studentCourse)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1){
                System.out.println("| " + String.format("%1$-10s", studentEnrollment.getStudent().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getStudent().getName()) + " | " + String.format("%1$18s", dateManager.convertDateToString(studentEnrollment.getStudent().getBirthdate())) + " | ");
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }

    // Display the course by student
    public void getCourseByStudent(){
        String studentId = getStudentById();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("LIST OF COURSES THAT STUDENT WITH ID"+studentId.toUpperCase()+" ENROLLED");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));

        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equals(studentId)){
                for (Course course : studentEnrollment.getCourses()){
                    System.out.println("| " + String.format("%1$-9s", course.getId()) + " | " + String.format("%1$-50s", course.getName()) + " | " + String.format("%1$18s", course.getNumber_of_credits()) + " | ");
                }
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }

    // Two option: List the course by student and List the student by Course
    @Override
    public void getOne() {
        // Get student (list all the course by student) or course (list all the student by course)
       getStudentByCourse();
       getCourseByStudent();
    }

    // TODO: Modified to make it user friendly
    @Override
    public void getAll() {
        for (StudentEnrollment studentEnrollment: this.studentEnrollments){
            System.out.println(studentEnrollment);
        }
    }

    // Check if the student is in the enrollment list
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
                System.out.println("The student ID is not recognized by the system");
                System.out.println("Please enter again");
            }
        }
        return studentId;
    }

    // Check if in the student's enrollment have the semester
//    public boolean checkSemesterInStudentEnrollment(String studentId, String semester){
//        for (StudentEnrollment studentEnrollment: this.studentEnrollments){
//            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
//                return true;
//            }
//        }
//        return false;
//    }

    // Check if the semester appear in the enrollment list
    public String checkSemester(){
        int flag = 0;
        String studentSemester = "";
        while (flag == 0){
            System.out.print("Please enter the student id");
            studentSemester = Main.scanner.nextLine();

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

    // Check if the course id is in the enrollment list
    public String checkCourse(){
        int flag = 0;
        String studentCourse = "";
        while(flag == 0){
            System.out.print("Please enter the student course: ");
            studentCourse = Main.scanner.nextLine();

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

//    // Display the list of student enrollments (might similar to getAll())
//    public void displayStudentEnrollments() {
//        for (StudentEnrollment studentEnrollment: this.studentEnrollments){
//            System.out.println(studentEnrollment);
//        }
//    }

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
