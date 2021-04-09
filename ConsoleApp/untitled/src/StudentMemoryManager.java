import Model.Model;
import Model.Course;
import Model.Student;
import Model.StudentEnrollment;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentMemoryManager implements StudentEnrollmentManager, Valid {

    protected ArrayList<StudentEnrollment> studentEnrollments = new ArrayList<>();
    protected CourseManager courseManager = new CourseManager();
    protected StudentManager studentManager = new StudentManager();


    Menu menu = new Menu();

    public void execute(){
        // Menu
        askingLoad();
        while (true){
            int input = menu.mainMenu();
            if (input == 1){
                add();
            } else if(input == 2){
                update();
            } else if(input == 3){
                display();
            } else if (input == 4) {
                getOne();
            } else if (input == 5){
                getAll();
            } else if (input == 6){
                delete();
            } else {
                System.out.println("Console is shutting down!!!");
                break;
            }

        }

    }


    // Adding student enrollment for 1 semester
    // Case 1: Check if the student enrollment with that semester has been already in the system (Done)
    // Case 2: Checking the adding course is already has been added (Done)
    // Case 3: Asking if the user want to add another student enrollment (Done)
    @Override
    public void add() {

        while(true){
            getAll();
            ArrayList<Course> studentCourse = new ArrayList<>();

            // Check if the validation of user input
            String studentID = studentManager.validStudent();
            String semester = validSemester();
            String courseId = courseManager.validCourse(semester);

            // Check if the student enrollment is already in system
            while (checkStudentEnrollment(studentID, semester, courseId)){
                System.out.println("This enrollment is already on the system");
                System.out.println("Please try again");
                studentID = studentManager.validStudent();
                semester = validSemester();
                courseId = courseManager.validCourse(semester);
            }

            // Asking if the user want to for another course for the same semester
            while (true){
                studentCourse.add(courseManager.getCourseByIdAndSemester(courseId, semester));
                System.out.print("Do you want to add another course ? (Y/n) ");
                String input = Main.scanner.nextLine();
                System.out.println();
                while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
                    System.out.print("Invalid input, please enter only Y/y or N/n");
                    input = Main.scanner.nextLine();
                    System.out.println();
                }
                if (input.equalsIgnoreCase("n")){
                    break;
                }

                while(true){
                    int flag = 0;
                    semester = validSemester();
                    courseId = courseManager.validCourse(semester);
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

            System.out.print("Do you want to add another student enrollment ? (Y/n) ");
            String option = Main.scanner.nextLine();
            System.out.println();
            while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                System.out.print("Invalid input, please enter only Y/y or N/n");
                option = Main.scanner.nextLine();
            }
            if (option.equalsIgnoreCase("n")){
                break;
            }
        }

    }

    // Delete the a student enrollment
    // Case 1: The student enrollment is not recognized by the system
    // Case 2: Asking if the user want to delete another student enrollment (Done)
    // Case 3: If the student enrollment list is empty break the loop and return to the main menu
    @Override
    public void delete() {

        while(true){
            // Show the list of the student enrollment
            getAll();

            // The student enrollment represent by the index in the student Enrollment list
            int input = checkInput(0, studentEnrollments.size()-1);

            // If the student enrollment list is empty break the loop and return to the main menu
            if (studentEnrollments.size() <= 0){
                System.out.println("The list of student enrollment is empty");
                System.out.println("Return to the main menu");
                break;
            }

            // Remove by the index of it
            studentEnrollments.remove(input);

            // Asking if the user want to repeat the action, else return to the main menu
            System.out.print("Do you want to delete another student enrollment ? (Y/n) ");
            String option = Main.scanner.nextLine();
            while(!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")){
                System.out.print("Invalid input, please enter only Y/y or N/n");
                option = Main.scanner.nextLine();
            }
            if (option.equalsIgnoreCase("n")){
                break;
            }
        }

    }

    @Override
    public void update() {
        while (true){
            int input = menu.updateMenu();
            if(input == 1){
                addingCourseForStudent();
            } else if(input ==2) {
                deleteCourseForStudent();
            } else {
                break;
            }
        }
    }

    // Adding Model.Course for Model.Student in Specific Semester
    // Case 1: Check if the course had been enrolled before (Done)
    // Case 2: Check if the the student have already enroll that semester
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

        // Display the list of course that student enroll in that semster
        displayCoursesOfStudent(studentId, semester);

        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse(semester);

            // Checking if the student had already enrolled the course before
            while (checkStudentEnrollment(studentId, semester, courseStudent)) {
                System.out.println("The course have been already enrolled");
                System.out.println("Please try again");
                courseStudent = courseManager.validCourse(semester);
            }

            studentEnrollments.add(new StudentEnrollment(studentManager.getStudentById(studentId),courseManager.getCourseByIdAndSemester(courseStudent, semester), semester));

            // Asking if the user want to repeat the action, else return to the main menu
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
        getAll();
        String studentId = studentManager.validStudent();
        String semester = validSemester();

        // check if the student enrollment is on the system
        while(!checkStudentEnrollment(studentId, semester,"")){
            System.out.println("This student enrollment is not recognized by the system");
            System.out.println("Please try again");
            studentId = getStudentById();
            semester = validSemester();
        }

        // Display a list of courses by student in that semester
        displayCoursesOfStudent(studentId, semester);

        while (true){
            // Checking if the course id is in the system
            String courseStudent = courseManager.validCourse(semester);

            // Checking if the student had already enrolled the course before
            while (!checkStudentEnrollment(studentId, semester,courseStudent)) {
                System.out.println("Student does not enroll this course before");
                System.out.println("Please try again");
                courseStudent = courseManager.validCourse(semester);
            }

            // Looping through the list of student enrollment and get the macthed
            StudentEnrollment studentEnrollmentRemove = new StudentEnrollment();
            for(StudentEnrollment studentEnrollment: this.studentEnrollments){
                if(studentEnrollment.getStudent().getId().equals(studentId) && studentEnrollment.getSemester().equals(semester) && studentEnrollment.getCourses().getId().equals(courseStudent)){
                    studentEnrollmentRemove = studentEnrollment;
                }
            }
            studentEnrollments.remove(studentEnrollmentRemove);

            // Asking if the user want to repeat the action, else return to the main menu
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
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | " + String.format("%1$18s", "SEMESTER") + " | "));
        for (StudentEnrollment studentEnrollment : this.studentEnrollments) {
            if(studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester) && studentEnrollment.getCourses().getSemester().equalsIgnoreCase(semester)){
                System.out.println("| " + String.format("%1$-9s", studentEnrollment.getCourses().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getCourses().getName()) + " | " + String.format("%1$18s", studentEnrollment.getCourses().getNumber_of_credits())+ " | " + String.format("%1$18s", studentEnrollment.getCourses().getSemester()) + " | ");
            }
        }
    }

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
                if (studentEnrollment.getStudent().getId().equalsIgnoreCase(studentId) && studentEnrollment.getSemester().equalsIgnoreCase(semester) && studentEnrollment.getCourses().getId().equals(courseId) && studentEnrollment.getCourses().getSemester().equals(semester)){
                    return true;
                }
            }
        }

        return false;
    }

    // Display the student by the given course
    // Must included semester and allowed to save CSV (Done)
    public void getStudentByCourse(){
        // DateManager dateManager = new DateManager();
        ArrayList<Student> students = new ArrayList<>();

        // Check if any student has enroll this course
        String studentCourse = checkCourse();
        String semester = validSemester();

        System.out.println("---------------------------------------------------------------");
        System.out.println("LIST OF STUDENTS ENROLL "+ studentCourse.toUpperCase()+" COURSE IN SEMESTER "+ semester);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "STUDENT_NAME") + " | " + String.format("%1$18s", "STUDENT_DOB") + " | "));

        // Looping through the list of student enrollment and print it out the match condition
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
    // Must included semester, check the list is empty (Done)
    public void getCourseByStudent(){
        String studentId = getStudentById();
        String semester = checkSemester();

        ArrayList<Course> courses = new ArrayList<>();

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("LIST OF COURSES THAT STUDENT WITH ID "+studentId.toUpperCase()+" ENROLLED IN SEMESTER " + semester);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));

        // Looping through the list of student enrollment and print it out the match condition
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

    public void ImportToCSV(ArrayList<?> list, String textFileName, List<String> listOfProperties) {
        try {
            FileWriter fileWriter = new FileWriter(new File(textFileName));

            // Creating name of columns
            String nameCol = String.join(",",listOfProperties);
            fileWriter.append(nameCol);
            fileWriter.append("\n");

            // With the list of items, append it to the csv file
            for (Object element: list){
                if(element instanceof Student || element instanceof Course){
                    fileWriter.append(((Model) element).toCSV());
                }
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Cannot create file");
        }
    }

    // Display all the course in the semester
    // TODO: Case 1: There will be more than one student enrolled in the course (already done but not check)
    // Allowed to save to csv file (done)
    public void getCourseBySemester(){
        // Check if in that semester have any course enrollment
        String semester = checkSemester();
        ArrayList<Course> courses = new ArrayList<>();
        System.out.println("-----------------------------------------------------");
        System.out.println("LIST OF COURSES IN SEMESTER "+semester.toUpperCase());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));

        List<Course> listCourses= new ArrayList<>();

        for (StudentEnrollment studentEnrollment : studentEnrollments){
            if(studentEnrollment.getSemester().equals(semester)){
                listCourses.add(studentEnrollment.getCourses());
            }
        }

        // Generate to get unique value
        HashSet<Course> uniqueCourseList = new HashSet(listCourses);
        //uniqueCourseList.toArray()
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
    // Bring all the menu of display in the Main.class to here (done)
    public void display() {
        // Get student (list all the course by student) or course (list all the student by course)
        while (true){
            int input = menu.displayMenu();
            if(input == 1){
                getCourseByStudent();
            } else if(input == 2){
                getStudentByCourse();
            } else if (input == 3){
                getCourseBySemester();
            } else {
                break;
            }
        }
    }

    @Override
    public void getOne() {

        getAll();
        // Check if the validation of user input
        String studentID = studentManager.validStudent();
        String semester = validSemester();
        String courseId = courseManager.validCourse(semester);

        // Check if the student enrollment is already in system
        while (!checkStudentEnrollment(studentID, semester, courseId)){
            System.out.println("This enrollment is not in the system");
            System.out.println("Please try again");
            studentID = studentManager.validStudent();
            semester = validSemester();
            courseId = courseManager.validCourse(semester);
        }

        StudentEnrollment studentEnrollment = new StudentEnrollment(studentManager.getStudentById(studentID),courseManager.getCourseByIdAndSemester(courseId, semester), semester);

        System.out.println("-----------------------------------------");
        System.out.println("INFORMATION ABOUT THE STUDENT ENROLLMENT");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.println("STUDENT INFORMATION");
        System.out.println("| " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "STUDENT_NAME") + " | " + String.format("%1$18s", "STUDENT_DOB") + " |"));
        System.out.println("| " + String.format("%1$-10s", studentEnrollment.getStudent().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getStudent().getName()) + " | " + String.format("%1$18s", studentEnrollment.getStudent().getBirthdate()) + " |");
        System.out.println("COURSE INFORMATION");
        System.out.println("| " + String.format("%1$106s", "COURSE_ID " + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | " + String.format("%1$18s", "SEMESTER") +" |"));
        System.out.println("| " + String.format("%1$-10s", studentEnrollment.getCourses().getId()) + " | " + String.format("%1$-50s", studentEnrollment.getCourses().getName()) + " | " + String.format("%1$18s", studentEnrollment.getCourses().getNumber_of_credits()) +" | " + String.format("%1$18s", studentEnrollment.getCourses().getSemester()) + " |");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }

    // Modified to make it user friendly (Not yet checked) (Done)
    @Override
    public void getAll() {
        int count = 0;
        System.out.println("-------------------------------");
        System.out.println("LIST OF ALL STUDENT ENROLLMENTS");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "ENROLL_ID" +" | " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "COURSE_ID") + " | " + String.format("%1$18s", "SEMESTER") + " | ")));

        for (StudentEnrollment studentEnrollment: this.studentEnrollments){

            System.out.println("| " + String.format("%1$99s", count +" | " + String.format("%1$87s", studentEnrollment.getStudent().getId() + " | " + String.format("%1$-50s", studentEnrollment.getCourses().getId()) + " | " + String.format("%1$18s", studentEnrollment.getSemester()) + " | ")));
            count++;
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
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
                System.out.println("The student ID is not enrolled yet in the system");
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
                System.out.println("The course was not enroll in this semester yet");
                System.out.println("Please try again");
            }

        }
        return studentCourse;
    }

    public String validSemester(){

        while(true){
//            Scanner scannerSemester = new Scanner(System.in);
            System.out.print("Please enter the semester: ");
            String semester = Main.scanner.nextLine();
            System.out.println();
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

    public void readFile(String fileName) throws IOException {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the file");
        }
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            StudentEnrollment studentEnrollment = new StudentEnrollment(studentManager.getStudentById(data[0]), courseManager.getCourseByIdAndSemester(data[1], data[2]), data[2]);
            this.studentEnrollments.add(studentEnrollment);
            // do something with the data
        }
        csvReader.close();
    }

    public void askingLoad(){
        String fileName = "studentenroll.csv";
        System.out.print("Do you want to use the default list of student enrollment (Y/n)? ");
        String input = Main.scanner.nextLine();
        //System.out.println();
        while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")){
            System.out.print("Invalid input, please enter only Y/y or N/n");
            input = Main.scanner.nextLine();
            System.out.println();
        }
        if (input.equalsIgnoreCase("y")){
            try {
                readFile(fileName);
            } catch (IOException e) {
                System.out.println("Cannot read the file");;
            }
        } else {
            System.out.print("Please enter the file that you want to use: ");
            fileName = Main.scanner.nextLine();
            try {
                readFile(fileName);
            } catch (IOException e) {
                System.out.println("Cannot read the file, please try again");;
            }
        }
    }

    public void createList(){
        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
        Student student2 = new Student("S3817852","Nguyen Thanh Thien","06/11/2000");
        Student student3 = new Student("S3836455","Nguyen Thanh Tuan","14/12/1995");

        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12, "2021A");
        Course course2 = new Course("COSC2429","Introduction to Programming", 12,"2021A");
        Course course3 = new Course("ISY3414","Practical Database Concept", 12,"2021A");
        Course course4 = new Course("COSC2500", "Intro to Computer Systems", 12, "2021B");

        StudentEnrollment studentEnrollment1 = new StudentEnrollment(student1, course3, "2021A");
        StudentEnrollment studentEnrollment2 = new StudentEnrollment(student1, course4, "2021B");
        StudentEnrollment studentEnrollment3 = new StudentEnrollment(student2, course1, "2021A");
        StudentEnrollment studentEnrollment4 = new StudentEnrollment(student2, course2, "2021A");
        StudentEnrollment studentEnrollment5 = new StudentEnrollment(student2, course3, "2021A");
        StudentEnrollment studentEnrollment6 = new StudentEnrollment(student2, course4, "2021B");
        StudentEnrollment studentEnrollment7 = new StudentEnrollment(student3, course1, "2021A");
        StudentEnrollment studentEnrollment8 = new StudentEnrollment(student3, course2, "2021A");
        StudentEnrollment studentEnrollment9 = new StudentEnrollment(student3, course3, "2021A");
        StudentEnrollment studentEnrollment10 = new StudentEnrollment(student3, course4, "2021B");


        this.studentEnrollments.add(studentEnrollment1);
        this.studentEnrollments.add(studentEnrollment2);
        this.studentEnrollments.add(studentEnrollment3);
        this.studentEnrollments.add(studentEnrollment4);
        this.studentEnrollments.add(studentEnrollment5);
        this.studentEnrollments.add(studentEnrollment6);
        this.studentEnrollments.add(studentEnrollment7);
        this.studentEnrollments.add(studentEnrollment8);
        this.studentEnrollments.add(studentEnrollment9);
        this.studentEnrollments.add(studentEnrollment10);
    }

    // Getter and setter
    public ArrayList<StudentEnrollment> getStudentEnrollments() {
        return studentEnrollments;
    }

}
