import Model.AdditionService;
import Model.Course;
import Model.Student;
import Model.StudentEnrollment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class StudentMemoryManager implements StudentEnrollmentManager {

    ArrayList<StudentEnrollment> studentEnrollments = new ArrayList<>();
    CourseManager courseManager = new CourseManager();
    StudentManager studentManager = new StudentManager();


    // Adding student enrollment for 1 semester
    // Case 1: Check if the student enrollment with that semester has been already in the system (Done)
    // Case 2: Checking the adding course is already has been added (Done)
    // TODO: Case 3: Asking if the user want to add another student enrollment
    @Override
    public void add() {

        ArrayList<Course> studentCourse = new ArrayList<>();

        // Check if the validation of user input
        String studentID = studentManager.validStudent();
        String semester = validSemester();
        String courseId = courseManager.validCourse();

        // Check if the student enrollment is already in system
        while (checkStudentEnrollment(studentID, semester, courseId)){
            System.out.println("This enrollment is already on the system");
            System.out.println("Please try again");
            studentID = studentManager.validStudent();
            semester = validSemester();
            courseId = courseManager.validCourse();
        }

        // Asking if the user want to for another course for the same semester
        while (true){
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

            while(true){
                int flag = 0;
                courseId = courseManager.validCourse();
                for(Course course : studentCourse){
                    if(course.getId().equals(courseId)){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1){
                    System.out.println("This course have already been added in this semester");
                    System.out.println("Please try again");
                } else {
                    break;
                }
            }



        }

        for (Course course : studentCourse){
            StudentEnrollment studentEnrollment = new StudentEnrollment(studentManager.getStudentById(studentID), course, semester);
            this.studentEnrollments.add(studentEnrollment);
        }


    }

    // Delete the a student enrollment
    // Case 1: The student enrollment is not recognized by the system
    // TODO: Case 2: Asking if the user want to delete another student enrollment
    @Override
    public void delete() {

        String studentId = getStudentById();
        String semester = validSemester();
        String courseId = courseManager.validCourse();

        // check if the student enrollment is on the system
        while(!checkStudentEnrollment(studentId, semester, courseId)){
            System.out.println("This student enrollment is not recognized by the system");
            System.out.println("Please try again");
            studentId = getStudentById();
            semester = validSemester();
            courseId = courseManager.validCourse();
        }

        for (StudentEnrollment studentEnrollment : this.studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester) && studentEnrollment.getCourses().getId().equals(courseId)){
                this.studentEnrollments.remove(studentEnrollment);
            }
        }

    }

    // Adding Model.Course for Model.Student in Specific Semester
    // Case 1: Check if the course had been enrolled before (Done)
    // TODO : CASE 2: Check if the the student have already enroll that semester
    public void addingCourseForStudent(){

        String studentId = studentManager.validStudent();
        String semester = validSemester();


        // check if the student enrollment is on the system
        while(!checkStudentEnrollment(studentId, semester, "")){
            System.out.println("This student enrollment is not recognized by the system");
            System.out.println("Please try again");
            studentId = getStudentById();
            semester = validSemester();
        }

        displayCoursesOfStudent(studentId, semester);

        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse();

            // Checking if the student had already enrolled the course before
            while (checkStudentEnrollment(studentId, semester, courseStudent)) {
                System.out.println("The course have been already enrolled");
                System.out.println("Please try again");
                courseStudent = courseManager.validCourse();
            }

            studentEnrollments.add(new StudentEnrollment(studentManager.getStudentById(studentId),courseManager.getCourseById(courseStudent), semester));

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
        String semester = validSemester();

        // check if the student enrollment is on the system
        while(!checkStudentEnrollment(studentId, semester,"")){
            System.out.println("This student enrollment is not recognized by the system");
            System.out.println("Please try again");
            studentId = getStudentById();
            semester = validSemester();
        }

        displayCoursesOfStudent(studentId, semester);
        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse();

            // Checking if the student had already enrolled the course before
            while (!checkStudentEnrollment(studentId, semester,courseStudent)) {
                System.out.println("Model.Student does not enroll this course before");
                System.out.println("Please try again");
                courseStudent = courseManager.validCourse();
            }

            for(StudentEnrollment studentEnrollment: this.studentEnrollments){
                if(studentEnrollment.getStudent().getId().equals(studentId) && studentEnrollment.getSemester().equals(semester)){
                    studentEnrollments.remove(studentEnrollment);
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

    // Display course list of that Model.Student in specific semester
    public void displayCoursesOfStudent(String studentId, String semester){
        System.out.println("STUDENT ENROLLMENT IN " + semester.toUpperCase()+" OF "+studentId.toUpperCase());
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));
        for (StudentEnrollment studentEnrollment : this.studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
                System.out.println("| " + String.format("%1$-9s", studentEnrollment.getCourses().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getCourses().getName()) + " | " + String.format("%1$18s", studentEnrollment.getCourses().getNumber_of_credits()) + " | ");
            }
        }
    }

//    // Check if the course is in the student enrollment
//    public boolean checkStudentRecord(String studentId, String courseId){
//        for (Model.StudentEnrollment studentEnrollment : this.studentEnrollments){
//            if (studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId)){
//                for(Model.Course course : studentEnrollment.getCourses()){
//                    if(course.getId().equals(courseId)){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    // Check if the student enrollment(by id and semester) is already in the system
    public boolean checkStudentEnrollment(String studentId, String semester, String courseId){

        if (courseId.equals("")){
            for (StudentEnrollment studentEnrollment : this.studentEnrollments){
                if (studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester)){
                    return true;
                }
            }
        }
        else {
            for (StudentEnrollment studentEnrollment : this.studentEnrollments){
                if (studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester) && studentEnrollment.getCourses().getId().equals(courseId)){
                    return true;
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

        System.out.println("Model.Student ID" + studentId);
        System.out.println("Semester " + semester);
        System.out.println("Model.Course list");
        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            if (studentEnrollment.getStudent().getId().equals(studentId) && studentEnrollment.getSemester().equals(semester)){
                System.out.println(studentEnrollment.getCourses());
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("List course is empty, please try again");
        }

    }

    // Display the student by the given course
    // TODO: Must included semester and allowed to save CSV
    public void getStudentByCourse(){
        DateManager dateManager = new DateManager();
        ArrayList<Student> students = new ArrayList<>();

        // Check if any student has enroll this course
        String studentCourse = checkCourse();
        String semester = validSemester();

        System.out.println("---------------------------------------------------------------");
        System.out.println("LIST OF STUDENTS ENROLL "+ studentCourse.toUpperCase()+" COURSE IN SEMESTER "+ semester);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "STUDENT_NAME") + " | " + String.format("%1$18s", "STUDENT_DOB") + " | "));

        for (StudentEnrollment studentEnrollment: studentEnrollments) {

            if (studentEnrollment.getCourses().getId().equals(studentCourse) && studentEnrollment.getSemester().equals(semester)) {
                System.out.println("| " + String.format("%1$-10s", studentEnrollment.getStudent().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getStudent().getName()) + " | " + String.format("%1$18s", studentEnrollment.getStudent().getBirthdate()) + " | ");
                students.add(studentEnrollment.getStudent());
            }

        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Do you want to save the record in to csv file (Y/n) ?");
        String input = Main.scanner.nextLine();
        if(input.equalsIgnoreCase("Y")){
            String textFileName = "ListOfStudentsEnrollIn_"+studentCourse+"_"+semester+".csv";
            ImportToCSV(students, textFileName, students.get(0).propertyStudent);
        }

    }

    // Display the course by student
    // TODO: Must included semester, check the list is empty
    public void getCourseByStudent(){
        String studentId = getStudentById();
        String semester = checkSemester();

        ArrayList<Course> courses = new ArrayList<>();

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("LIST OF COURSES THAT STUDENT WITH ID"+studentId.toUpperCase()+" ENROLLED IN SEMESTER " + semester);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));

        for (StudentEnrollment studentEnrollment: studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equals(studentId) && studentEnrollment.getSemester().equals(semester)){
                courses.add(studentEnrollment.getCourses());
                System.out.println("| " + String.format("%1$-9s", studentEnrollment.getCourses().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getCourses().getName()) + " | " + String.format("%1$18s", studentEnrollment.getCourses().getNumber_of_credits()) + " | ");
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Do you want to save the record in to csv file (Y/n) ?");
        String input = Main.scanner.nextLine();
        if(input.equalsIgnoreCase("Y")){
            String textFileName = "ListOfCoursesEnrollBy_"+studentId+"_"+semester+".csv";
            ImportToCSV(courses, textFileName, courses.get(0).propertyCourse);
        }


    }

    private void ImportToCSV(ArrayList<?> list, String textFileName, List<String> listOfProperties) {
        try {
            FileWriter fileWriter = new FileWriter(new File(textFileName));

            String nameCol = String.join(",",listOfProperties);
            fileWriter.append(nameCol);
            fileWriter.append("\n");

            for (Object element: list){
                if(element instanceof Student || element instanceof Course){
                    fileWriter.append(((AdditionService) element).toCSV());
                }
            }

            fileWriter.flush();
            fileWriter.close();


        } catch (IOException e) {
            System.out.println("Cannot create file");;
        }
    }

    // Display all the course in the semester
    // TODO: Case 1: The will be more than one student enrolled in the course (already done but not check)
    // TODO: Allowed to save to csv file
    public void getCourseBySemester(){
        // Check if in that semester have any course enrollment
        String semester = checkSemester();
        ArrayList<Course> courses = new ArrayList<>();
        System.out.println("-----------------------------------------------------");
        System.out.println("LIST OF COURSES IN SEMESTER "+semester.toUpperCase());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));

        ArrayList<Course> listCourses= new ArrayList<>();

        for (StudentEnrollment studentEnrollment : studentEnrollments){
            if(studentEnrollment.getSemester().equals(semester)){
                listCourses.add(studentEnrollment.getCourses());
            }
        }

        // Generate to get unique value
        HashSet<Course> uniqueCourseList = new HashSet(listCourses);
        for(Course course : uniqueCourseList){
            courses.add(course);
            System.out.println("| " + String.format("%1$-9s", course.getId()) + " | " + String.format("%1$-50s", course.getName()) + " | " + String.format("%1$18s", course.getNumber_of_credits()) + " | ");
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Do you want to save the record in to csv file (Y/n) ?");
        String input = Main.scanner.nextLine();
        if(input.equalsIgnoreCase("Y")){
            String textFileName = "ListOfCoursesEnrollIn_"+semester+".csv";
            ImportToCSV(courses, textFileName, courses.get(0).propertyCourse);
        }

    }

    // Two option: List the course by student and List the student by Model.Course
    // TODO: Bring all the menu of display in the Main.class to here
    @Override
    public void getOne() {
        // Get student (list all the course by student) or course (list all the student by course)
        getStudentByCourse();
        getCourseByStudent();
        getCourseBySemester();
    }

    // TODO: Modified to make it user friendly (Not yet checked)
    @Override
    public void getAll() {
        System.out.println("-------------------------------");
        System.out.println("LIST OF ALL STUDENT ENROLLMENTS");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "COURSE_ID") + " | " + String.format("%1$18s", "SEMESTER") + " | "));

        for (StudentEnrollment studentEnrollment: this.studentEnrollments){

            System.out.println("| " + String.format("%1$87s", studentEnrollment.getStudent().getId() + " | " + String.format("%1$-50s", studentEnrollment.getCourses().getId()) + " | " + String.format("%1$18s", studentEnrollment.getSemester()) + " | "));

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

    // Check if the semester appear in the enrollment list
    public String checkSemester(){
        return validSemester();
    }

    // Check if the course id is in the enrollment list
    public String checkCourse(){
        int flag = 0;
        String studentCourse = "";
        while(flag == 0){
            System.out.print("Please enter the student course: ");
            studentCourse = Main.scanner.nextLine();

            for (StudentEnrollment studentEnrollment : studentEnrollments){

                if(studentEnrollment.getCourses().getId().equals(studentCourse)){
                    flag = 1;
                    break;
                }

            }

            if(flag != 1){
                System.out.println("Please enter again");
            }

        }
        return studentCourse;
    }

    public String validSemester(){

        while(true){
            System.out.print("Please enter the semester: ");
            String semester = Main.scanner.nextLine();
            Pattern patternSemester = Pattern.compile("2021(A|B|C)");

            // If it matches the Pattern below, it will stop the loop and return the email
            if (patternSemester.matcher(semester).matches()) {
                return semester;


            }
            // If the input semester is empty, notify to the users and make the user write the email again
            else if (semester.equals("")) {
                System.out.println("Cannot be empty");
                System.out.println("Please try again");


            }
            // The user not match the below patter, notify to the user and make the user write the semester again
            else {
                System.out.println("Invalid Semester: The semester should have format 2021A/2021B/2021C");
                System.out.println("Please try again");
            }
        }

    }

}
