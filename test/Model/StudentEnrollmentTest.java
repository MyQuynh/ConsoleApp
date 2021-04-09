package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentEnrollmentTest {

    @Test
    public void getAndSetStudent() {
        Course course = new Course("test123","test123",12,"test123");
        Student student = new Student("test123","test123","test123");
        Student student2 = new Student("test","test","test");
        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
        studentEnrollment.setStudent(student2);
        assertEquals(studentEnrollment.getStudent(), student2);
    }

    @Test
    public void getAndSetCourses() {
        Course course = new Course("test123","test123",12,"test123");
        Student student = new Student("test123","test123","test123");
        Course course1 = new Course("test","test",12,"test");
        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
        studentEnrollment.setCourses(course1);
        assertEquals(studentEnrollment.getCourses(), course1);
    }

    @Test
    public void getAndSetSemester() {
        String semester = "test113";
        Course course = new Course("test123","test123",12,"test123");
        Student student = new Student("test123","test123","test123");
        Course course1 = new Course("test","test",12,"test");
        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
        studentEnrollment.setSemester(semester);
        assertEquals(studentEnrollment.getSemester(),semester);
    }


    @Test
    public void toCSV() {
        Course course = new Course("test123","test123",12,"test123");
        Student student = new Student("test123","test123","test123");
        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
        String csv = studentEnrollment.toCSV();
        assertEquals(csv,student.getId()+","+course.getId()+","+"2021A\n");
    }

    @Test
    public void testEqualsAndHashCode() {
        Course course = new Course("test123","test123",12,"test123");
        Student student = new Student("test123","test123","test123");
        StudentEnrollment studentEnrollment = new StudentEnrollment(student, course, "2021A");
        StudentEnrollment studentEnrollment1 = new StudentEnrollment(student, course, "2021A");
        assertEquals(studentEnrollment, studentEnrollment1);
        assertEquals(studentEnrollment.hashCode(), studentEnrollment1.hashCode());
    }
}