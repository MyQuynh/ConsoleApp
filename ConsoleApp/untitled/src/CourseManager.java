import Model.Course;
import Model.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    private ArrayList<Course> coursesList = new ArrayList<>();

    public CourseManager(){

        try {
            readFile("course.csv");
        } catch (IOException e) {
            System.out.println("Cannot read file");;
        }
    }

    public void add(Course course){
        this.coursesList.add(course);
    }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ArrayList<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public String validCourse(String semester){
        int flag = 0;
        String courseId = "";
        while (flag == 0){
            showCourseListBySemester(semester);
            System.out.print("Please enter the course ID: ");
            //courseId = Main.scanner.nextLine();
            courseId = Main.scanner.nextLine();
            System.out.println();

            for (Course course : coursesList){
                if (course.getId().equals(courseId) && course.getSemester().equals(semester)) {
                    flag = 1;
                    break;
                }
            }

            if(flag != 1){
                System.out.println("The course ID is not available in this semester");
                System.out.println("Please try again");
            }
        }
        return courseId;
    }

    public void showCourseListBySemester(String semester){
        System.out.println();
        System.out.println("---------------");
        System.out.println("LIST OF COURSE IN "+ semester);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));
        for (Course course : this.coursesList) {
            if(course.getSemester().equals(semester))
            System.out.println("| " + String.format("%1$-9s", course.getId()) + " | " + String.format("%1$-50s", course.getName()) + " | " + String.format("%1$18s", course.getNumber_of_credits()) + " | ");
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }

    public Course getCourseByIdAndSemester(String courseId, String semester){
        for(Course course : coursesList){
            if(course.getId().equals(courseId) && course.getSemester().equals(semester)){
                return course;
            }
        }
        return null;
    }

    public void showCoursesList(){
        System.out.println();
        System.out.println("---------------");
        System.out.println("LIST OF COURSE");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "COURSE_ID" + " | " + String.format("%1$-50s", "COURSE_NAME") + " | " + String.format("%1$18s", "COURSE_CREDITS") + " | "));
        for (Course course : this.coursesList) {
            System.out.println("| " + String.format("%1$-9s", course.getId()) + " | " + String.format("%1$-50s", course.getName()) + " | " + String.format("%1$18s", course.getNumber_of_credits()) + " | ");
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }

//    public void listOfCourses(){
//        // Creating a list of courses and add it to the Courses array
//
//        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12, "2021A");
//        Course course2 = new Course("COSC2429","Introduction to Programming", 12,"2021A");
//        Course course3 = new Course("ISY3414","Practical Database Concept", 12,"2021A");
//        Course course4 = new Course("COSC2500", "Intro to Computer Systems", 12,"2021B");
//        Course course5 = new Course("COSC2534","Building IT Systems", 12,"2021B");
//        Course course6 = new Course("COSC2652", "User-central Design", 12,"2021B");
//        Course course7 = new Course("COSC2430", "Web Programming", 12,"2021B");
//        Course course8 = new Course("COSC2081","Programming 1", 12,"2021C");
//        Course course9 = new Course("COSC2440", "Software Architecture: Design and Implementation", 12,"2021C");
//        Course course10 = new Course("COSC2539","Security in Computing and Information Technology", 12,"2021C");
//
//        this.coursesList.add(course1);
//        this.coursesList.add(course2);
//        this.coursesList.add(course3);
//        this.coursesList.add(course4);
//        this.coursesList.add(course5);
//        this.coursesList.add(course6);
//        this.coursesList.add(course7);
//        this.coursesList.add(course8);
//        this.coursesList.add(course9);
//        this.coursesList.add(course10);
//    }

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
            Course course = new Course(data[0], data[1], Integer.parseInt(data[2]), data[3]);
            this.coursesList.add(course);
            // do something with the data
        }
        csvReader.close();
    }

}
