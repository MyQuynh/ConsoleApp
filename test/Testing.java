//import Model.Course;
//import Model.Student;
//import Model.StudentEnrollment;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Testing {
//
//    @Rule
//    TextFromStandardInputStream textFromStandardInputStream = new TextFromStandardInputStream();
//
//
//    private static final InputStream DEFAULT_STDIN = System.in;
//
//
//
//    ArrayList<Boolean> result = new ArrayList<Boolean>();
//
//    public static StudentMemoryManager studentMemoryManager = new StudentMemoryManager();
//
//    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private static final PrintStream originalOut = System.out;
//
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
//        StudentEnrollment studentEnrollment2 = new StudentEnrollment(student1, course4, "2021A");
//        StudentEnrollment studentEnrollment3 = new StudentEnrollment(student2, course1, "2021A");
//        StudentEnrollment studentEnrollment4 = new StudentEnrollment(student2, course2, "2021A");
//        StudentEnrollment studentEnrollment5 = new StudentEnrollment(student2, course3, "2021A");
//        StudentEnrollment studentEnrollment6 = new StudentEnrollment(student2, course4, "2021A");
//        StudentEnrollment studentEnrollment7 = new StudentEnrollment(student3, course1, "2021A");
//        StudentEnrollment studentEnrollment8 = new StudentEnrollment(student3, course2, "2021A");
//        StudentEnrollment studentEnrollment9 = new StudentEnrollment(student3, course3, "2021A");
//        StudentEnrollment studentEnrollment10 = new StudentEnrollment(student3, course4, "2021A");
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
//
//
//
////    @BeforeEach
////    public void setUp() {
////        studentMemoryManager = new StudentMemoryManager();
////    }
////
////    @AfterEach
////    public void tearDown() {
////        studentMemoryManager = null;
////    }
//
//
//    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setIn(DEFAULT_STDIN);
//    }
//
//    public void rollbackChangesToStdin() {
//        System.setOut(originalOut);
//        System.setIn(DEFAULT_STDIN);
//    }
//
////    private final InputStream systemIn = System.in;
////    private final PrintStream systemOut = System.out;
////
//    private ByteArrayInputStream testIn;
//    private ByteArrayOutputStream testOut;
//
////    @BeforeEach
////    public void setUpOutput() {
////        testOut = new ByteArrayOutputStream();
////        System.setOut(new PrintStream(testOut));
////    }
////
//    private void provideInput(String data) {
//        testIn = new ByteArrayInputStream(data.getBytes());
//        System.setIn(testIn);
//    }
//
////
////    private String getOutput() {
////        return testOut.toString();
////    }
////
////    @AfterEach
////    public void restoreSystemInputOutput() {
////        System.setIn(systemIn);
////        System.setOut(systemOut);
////    }
//
//
//    // Testing for adding student enrollment
//    @Test
//    public void Testing1() throws IOException {
//
//        //System.out.println(System.in.available());
//        createList();
//
//        InputStream stdin = System.in;
//
//        StudentEnrollment studentEnrollmentStored = studentMemoryManager.studentEnrollments.get(4);
//        //System.out.println(studentEnrollmentStored);
//
//        //setUpStreams();
//        try {
//            ByteArrayInputStream in = new ByteArrayInputStream((
//                    // Adding student enrollment
//                    "1"+System.lineSeparator()+"S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2083"+System.lineSeparator()+"n"+System.lineSeparator()+ "n" + System.lineSeparator()+
//                    // Updating student enrollment
//                    // Adding course for student in one semester
//                    "2"+System.lineSeparator()+"1"+System.lineSeparator()+"S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2429"+System.lineSeparator()+"n"+System.lineSeparator()+
//                    // Delete course for student in one semester
//                    "2"+System.lineSeparator()+"S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "ISY3414"+System.lineSeparator()+"n"+System.lineSeparator()+ "3"+System.lineSeparator()+
//                    // Display the student enrollment
//                    // Display course by student
//                    "3"+System.lineSeparator()+"1"+System.lineSeparator()+"S3817852"+System.lineSeparator()+ "2021A"+System.lineSeparator()+"y"+System.lineSeparator()+
//                    // Display student by course
//                    "2"+System.lineSeparator()+"COSC2500"+System.lineSeparator()+ "2021A"+System.lineSeparator()+"y"+System.lineSeparator()+
//                    // Display all course in one semester
//                    "3"+System.lineSeparator()+"2021A"+System.lineSeparator()+"y"+System.lineSeparator()+"4"+System.lineSeparator()+
//                    // Display all the student enrollment
//                    "4"+System.lineSeparator()+
//                    // Delete the student enrollment
//                    "5"+System.lineSeparator()+"3"+System.lineSeparator()+"n"+System.lineSeparator()+
//                    // Exit the program
//                    "6").getBytes());
////        provideInput("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2083"+System.lineSeparator()+"n"+System.lineSeparator()+ "n");
//            //systemInMock.provideLines(String.valueOf(in));
//            System.setIn(in);
//
//
//            studentMemoryManager.execute();
//
////            ByteArrayInputStream iin = new ByteArrayInputStream(("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2083"+System.lineSeparator()+"n"+System.lineSeparator()+ "n").getBytes());
//////        provideInput("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2083"+System.lineSeparator()+"n"+System.lineSeparator()+ "n");
////            System.setIn(iin);
//
//
////            studentMemoryManager.add();
//            //System.setIn(in);
//            // Adding the student enrollment
//            Student student = new Student("S3836322","Ngo My Quynh","08/03/2001");
//            Course course = new Course("COSC2083","Introduction to Information Technology", 12);
//            StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
//
//            result.add(studentMemoryManager.studentEnrollments.contains(studentEnrollment));
//
//            // Adding the course
//            Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
//            Course course1 = new Course("COSC2429","Introduction to Programming", 12);
//            StudentEnrollment studentEnrollment2 = new StudentEnrollment(student1, course1, "2021A");
//
//            result.add(studentMemoryManager.studentEnrollments.contains(studentEnrollment2));
//
//            // Delete the course
//            Student student2 = new Student("S3836322","Ngo My Quynh","08/03/2001");
//            Course course2 = new Course("ISY3414","Practical Database Concept", 12);
//            StudentEnrollment studentEnrollment3 = new StudentEnrollment(student2, course2, "2021A");
//
//            result.add(!studentMemoryManager.studentEnrollments.contains(studentEnrollment3));
//
//            // Delete the student enrollment
//            Student student3 = new Student("S3836322","Ngo My Quynh","08/03/2001");
//            Course course3 = new Course("ISY3414","Practical Database Concept", 12);
//            StudentEnrollment studentEnrollment4 = new StudentEnrollment(student2, course2, "2021A");
//
//            result.add(!studentMemoryManager.studentEnrollments.contains(studentEnrollment3));
//
//
//            // Delete the course
//            result.add(!studentMemoryManager.studentEnrollments.contains(studentEnrollmentStored));
//
//            System.out.println(result.toString());
//
//            //System.out.println(result.contains(false));
//            Assertions.assertFalse(result.contains(false));
//
//            //System.out.println(Arrays.toString(System.in.readAllBytes()));
//            //System.out.println(in.readAllBytes());
//
//
//        } finally {
//            //System.out.println(System.in.available());
//            System.setIn(stdin);
//            //System.out.println(Arrays.toString(System.in.readAllBytes()));;
//
//        }
//
//
//
////        try {
////            ByteArrayInputStream in = new ByteArrayInputStream(("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2083"+System.lineSeparator()+"n"+System.lineSeparator()+ "n").getBytes());
//////        provideInput("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2083"+System.lineSeparator()+"n"+System.lineSeparator()+ "n");
////            System.setIn(in);
////            studentMemoryManager.addingCourseForStudent();
////
////            Student student2 = new Student("S3836322","Ngo My Quynh","08/03/2001");
////            Course course2 = new Course("COSC2429","Introduction to Programming", 12);
////            StudentEnrollment studentEnrollment2 = new StudentEnrollment(student2, course2, "2021A");
////
////            System.out.println(studentMemoryManager.studentEnrollments.contains(studentEnrollment2));
////
////            Assertions.assertTrue(studentMemoryManager.studentEnrollments.contains(studentEnrollment2));
////        }
////        finally {
////            System.setIn(DEFAULT_STDIN);
////        }
////
////
//////        System.setIn(DEFAULT_STDIN);
//////
//////        System.setIn(new ByteArrayInputStream(("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2429"+System.lineSeparator()+"n").getBytes()));
////
////
////
//
//    }
//
//
//    // Testing for addingCourse
//    @Test
//    public void Testing2() throws IOException {
//
//
//        InputStream stdin = System.in;
//
//
//        //systemInMock.provideLines(("3"+"1"+"S3836322"+ "2021A"+ "COSC2429"+"n"));
//        studentMemoryManager.execute();
//
//
//
//
////        provideInput("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2429"+System.lineSeparator()+"n");
//
//
//        Student student = new Student("S3836322","Ngo My Quynh","08/03/2001");
//        Course course2 = new Course("COSC2429","Introduction to Programming", 12);
//        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course2, "2021A");
//
//        System.out.println(studentMemoryManager.studentEnrollments.contains(studentEnrollment));
//
//        Assertions.assertTrue(studentMemoryManager.studentEnrollments.contains(studentEnrollment));
//
//        System.setIn(DEFAULT_STDIN);;
//
//    }
////
//    // Testing for delete course
//    @Test
//    public void Testing3(){
//        System.setIn(new ByteArrayInputStream(("S3836322"+System.lineSeparator()+ "2021A"+System.lineSeparator()+ "COSC2429"+System.lineSeparator()+"n").getBytes()));
//
//        studentMemoryManager.addingCourseForStudent();
//
//
//        Student student = new Student("S3836322","Ngo My Quynh","08/03/2001");
//        Course course2 = new Course("COSC2429","Introduction to Programming", 12);
//        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course2, "2021A");
//
//        System.out.println(studentMemoryManager.studentEnrollments.contains(studentEnrollment));
//
//        Assertions.assertTrue(studentMemoryManager.studentEnrollments.contains(studentEnrollment));
//
//    }
//
//    // Testing for deleting
//    @Test
//    public void Testing4(){
//
//        //InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in123 = new ByteArrayInputStream(("0"+System.lineSeparator()+"n").getBytes());
//        System.setIn(in123);
//
//        studentMemoryManager.delete();
//
//        //System.setIn(in);
//
//
//        Student student = new Student("S3836322","Ngo My Quynh","08/03/2001");
//        Course course = new Course("COSC2083","Introduction to Information Technology", 12);
//        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
//
//        System.out.println();
//
//        Assertions.assertTrue(!studentMemoryManager.studentEnrollments.contains(studentEnrollment));
//
//    }
//
////    // Testing for display student by courses
////    @Test
////    public void Testing5(){
////
////    }
////
////    // Testing for display course by student
////    @Test
////    public void Testing6(){
////
////    }
////
////    // Testing for display all course in semester
////    @Test
////    public void Testing7(){
////
////    }
////
////    // Testing for display all the student enrollment
////    @Test
////    public void Testing8(){
////        studentMemoryManager.getAll();
////    }
//}
