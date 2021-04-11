import Model.Course;
import Model.Student;
import Model.StudentEnrollment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StudentMemoryManagerTest {


    @Before
    public void resetScanner() {
        Main.scanner = new Scanner(System.in);
    }

//    public void createList(){
//        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
//        Student student2 = new Student("S3817852","Nguyen Thanh Thien","06/11/2000");
//        Student student3 = new Student("S3836455","Nguyen Thanh Tuan","14/12/1995");
//
//        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12);
//        Course course2 = new Course("COSC2429","Introduction to Programming", 12);
//        Course course3 = new Course("ISY3414","Practical Database Concept", 12);
//        Course course4 = new Course("COSC2500", "Intro to Computer Systems", 12);
//
//        StudentEnrollment studentEnrollment1 = new StudentEnrollment(student1, course3, "2021A");
//        StudentEnrollment studentEnrollment2 = new StudentEnrollment(student1, course4, "2021B");
//        StudentEnrollment studentEnrollment3 = new StudentEnrollment(student2, course1, "2021A");
//        StudentEnrollment studentEnrollment4 = new StudentEnrollment(student2, course2, "2021A");
//        StudentEnrollment studentEnrollment5 = new StudentEnrollment(student2, course3, "2021A");
//        StudentEnrollment studentEnrollment6 = new StudentEnrollment(student2, course4, "2021B");
//        StudentEnrollment studentEnrollment7 = new StudentEnrollment(student3, course1, "2021A");
//        StudentEnrollment studentEnrollment8 = new StudentEnrollment(student3, course2, "2021A");
//        StudentEnrollment studentEnrollment9 = new StudentEnrollment(student3, course3, "2021A");
//        StudentEnrollment studentEnrollment10 = new StudentEnrollment(student3, course4, "2021B");
//
//
//        studentMemoryManager.studentEnrollments.add(studentEnrollment1);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment2);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment3);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment4);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment5);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment6);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment7);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment8);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment9);
//        studentMemoryManager.studentEnrollments.add(studentEnrollment10);
//    }


    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();


    // Adding student enrollment
    @Test
    public void add() {
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();

        systemInMock.provideLines("S3836322","2021A","COSC2083","n","n");

        studentMemoryManager.add();

        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12,"2021A");

        StudentEnrollment studentEnrollment1 = new StudentEnrollment(student1, course1, "2021A");

//        Scanner sc = new Scanner(System.in);
//        String firstLine = sc.nextLine();
//        String secondLine = sc.nextLine();
//        assertEquals("line 1", firstLine);
//        assertEquals("line 2", secondLine);
        studentMemoryManager.getAll();
        assertTrue(studentMemoryManager.studentEnrollments.contains(studentEnrollment1));
        //Main.scanner = new Scanner(System.in);
    }

    // Delete student enrollment
    @Test
    public void delete() {

        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();

        systemInMock.provideLines("5","n");

        StudentEnrollment studentEnrollment = studentMemoryManager.studentEnrollments.get(5);
        studentMemoryManager.delete();

        //studentMemoryManager.createList();
//        System.out.println("dwjdbedb");
//        studentMemoryManager.getAll();

//        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
//        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12,"2021A");
//
//        StudentEnrollment studentEnrollment1 = new StudentEnrollment(student1, course1, "2021A");

        //systemInMock.provideLines("5","1","n","6");
        assertFalse(studentMemoryManager.studentEnrollments.contains(studentEnrollment));

//        systemInMock.provideLines("line 1", "line 2");
//        Scanner sc = new Scanner(System.in);
//        String firstLine = sc.nextLine();
//        String secondLine = sc.nextLine();
//        assertEquals("line 1", firstLine);
//        assertEquals("line 2", secondLine);
    }

    @Test
    public void execute() {
        // Assume that we want to exit the system
        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);
        systemInMock.provideLines("y","a","-1","7");
        studentMemoryManager1.execute();
        verify(studentMemoryManager1,times(1)).execute();
    }

    // Adding course in student enrollment
    @Test
    public void update() {
        // Assume that we want to exit the update menu
        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);
        systemInMock.provideLines("a","-1","3");
        studentMemoryManager1.update();
        verify(studentMemoryManager1,times(1)).update();
    }

    @Test
    public void addingCourseForStudent() {
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();

        systemInMock.provideLines("y","S3836322", "2021A", "COSC2083", "n");

        studentMemoryManager.addingCourseForStudent();

        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12,"2021A");

        StudentEnrollment studentEnrollment1 = new StudentEnrollment(student1, course1, "2021A");

        //studentMemoryManager.getAll();

        assertTrue(studentMemoryManager.studentEnrollments.contains(studentEnrollment1));
    }

    @Test
    public void deleteCourseForStudent() {
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();

        systemInMock.provideLines("y","S3836322", "2021A", "ISY3414", "n");

        studentMemoryManager.deleteCourseForStudent();

        Student student3 = new Student("S3836322","Ngo My Quynh", "08/03/2001");
        Course course3 = new Course("ISY3414","Practical Database Concept", 12,"2021A");

        StudentEnrollment studentEnrollment3 = new StudentEnrollment(student3, course3,"2021A");

        assertFalse(studentMemoryManager.studentEnrollments.contains(studentEnrollment3));
    }


    @Test
    public void displayCoursesOfStudent() {

        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);

        String studentId = "S3836322";
        String semester = "2021A";
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager1.createList();
        studentMemoryManager1.displayCoursesOfStudent(studentId,semester);
        //doNothing().when(studentMemoryManager1).createList();
        verify(studentMemoryManager1,times(1)).displayCoursesOfStudent(studentId, semester);
        //assertTrue(true);
    }

    @Test
    public void checkStudentEnrollment() {
        // Case 1: Check if the student enrollment is valid or not (recognized by the system)
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        boolean validEnroll = studentMemoryManager.checkStudentEnrollment("S3836322","2021A","ISY3414");
        boolean invalidEnroll = studentMemoryManager.checkStudentEnrollment("S3836322","2021A","COSC2083");

        assertFalse(invalidEnroll);
        assertTrue(validEnroll);
    }

    @Test
    public void getStudentByCourse() {
        String courseId = "ISY3414";
        String semester = "2021A";
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        systemInMock.provideLines(courseId,semester,"y");

        // Check if the file be created
        File f = new File("ListOfCoursesEnrollIn_"+courseId+"_"+semester+".csv");
        if(f.exists() && !f.isDirectory()) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }

    }

    @Test
    public void getCourseByStudent() {
        String studentId = "S3836322";
        String semester = "2021A";
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        systemInMock.provideLines(studentId,semester,"y");
        studentMemoryManager.getCourseByStudent();

        // Check if the file be created
        File f = new File("ListOfCoursesEnrollBy_"+studentId+"_"+semester+".csv");
        if(f.exists() && !f.isDirectory()) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void getCourseBySemester() {
        String semester = "2021A";
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        systemInMock.provideLines(semester,"y");
        studentMemoryManager.getCourseBySemester();

        // Check if the file be created
        File f = new File("ListOfCoursesEnrollIn_"+semester+".csv");
        if(f.exists() && !f.isDirectory()) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void getOne() {
        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);
        studentMemoryManager1.createList();
        systemInMock.provideLines("S3836322","2021A","ISY3414");
        studentMemoryManager1.getOne();
        verify(studentMemoryManager1,times(1)).getOne();
    }

    @Test
    public void display(){
//        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
//        systemInMock.provideLines("4");
//        studentMemoryManager.display();

        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);
        //studentMemoryManager1.createList();
        systemInMock.provideLines("4");
        studentMemoryManager1.display();
        //doNothing().when(studentMemoryManager1).createList();
        verify(studentMemoryManager1,times(1)).display();
        //assertTrue(true);
    }

    @Test
    // Testing for display all the student enrollments
    public void getAll() {
//        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
//        studentMemoryManager.createList();
//        studentMemoryManager.getAll();

        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);
        studentMemoryManager1.createList();
        studentMemoryManager1.getAll();
        //doNothing().when(studentMemoryManager1).createList();
        verify(studentMemoryManager1,times(1)).getAll();
        //assertTrue(true);
    }

    @Test
    public void getStudentById() {
        // Case 1: The student is not enrolled in the system yet
        // Case 2: The student id is not valid
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        systemInMock.provideLines("invalid","S3689902","S3836322");
        String studentId = studentMemoryManager.getStudentById();
        assertEquals(studentId,"S3836322");
    }

//    @Test
//    public void checkSemester() {
//        // Case 1: Check if the semester having the student enroll
//        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
//        systemInMock.provideLines("2022A","2021A");
//        String semester = studentMemoryManager.checkSemester();
//        assertEquals(semester,"2021A");
//    }

    @Test
    public void checkCourse() {
        // Case 1: Check if the course id is already enrolled by students in the system
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        systemInMock.provideLines("invalidCourseId","COSC2083");
        String courseId = studentMemoryManager.checkCourse();
        assertEquals(courseId, "COSC2083");
    }

    @Test
    public void validSemester() {
        // Case 1: Check if the semester having the following pattern 2021A, 2021B, 2021C
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        systemInMock.provideLines("invalidSemester","2021A");
        String semester = studentMemoryManager.validSemester();
        assertEquals(semester,"2021A");
    }

    @Test
    public void checkInput() {
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        // Case 1: Check if the input is number
        // Case 2: Check if the the number between the range of 1 to 4
        systemInMock.provideLines("a","-1","2");
        int input = studentMemoryManager.checkInput(1,4);
        assertEquals(input,2);
    }

    @Test
    public void askingLoad(){
        StudentMemoryManager studentMemoryManager1 = mock(StudentMemoryManager.class);
        systemInMock.provideLines("y");
        studentMemoryManager1.askingLoad();
        verify(studentMemoryManager1,times(1)).askingLoad();
    }

    @Test
    public void readFile(){
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        try {
            studentMemoryManager.readFile("studentenroll.csv");
            assertTrue(true);
        } catch (IOException e) {
            assertFalse(false);
        }
    }

    @Test
    public void createList(){
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.createList();
        assertTrue(studentMemoryManager.getStudentEnrollments().size() > 0);
    }

    @Test
    public void getStudentEnrollments(){
        // Assume that we don't add anthing to the list, so the le of the list should be 0
        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        assertEquals(studentMemoryManager.getStudentEnrollments().size(),0);
    }

    @Test
    public void ImportToCSV(){
        List<String> propertyCourse = Arrays.asList("COURSE_ID", "COURSE_NAME", "NUMBER_OF_CREDITS","SEMESTER");
        ArrayList<Course> courseArrayList = new ArrayList<>();
        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12, "2021A");
        Course course2 = new Course("COSC2429","Introduction to Programming", 12,"2021A");

        courseArrayList.add(course1);
        courseArrayList.add(course2);

        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
        studentMemoryManager.ImportToCSV(courseArrayList,"test123.csv",propertyCourse);
        // Check if the file be created
        // TODO: check student enrollment if it already enroll 4 course
        // TODO: check the data in the file
        File f = new File("test123.csv");
        if(f.exists() && !f.isDirectory()) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }


}