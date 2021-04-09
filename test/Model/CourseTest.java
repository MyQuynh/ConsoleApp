package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void getIdAndSet() {
        String courseId = "test666";
        Course course = new Course("test123","test123",12,"test123");
        course.setId(courseId);
        assertEquals(courseId, course.getId());
    }

    @Test
    public void getNameAndSet() {
        String courseName = "test666";
        Course course = new Course("test123","test123",12,"test123");
        course.setName(courseName);
        assertEquals(courseName, course.getName());
    }

    @Test
    public void getNumber_of_credits() {
        int courseCredits = 666;
        Course course = new Course("test123","test123",12,"test123");
        course.setNumber_of_credits(courseCredits);
        assertEquals(courseCredits, course.getNumber_of_credits());
    }


    @Test
    public void getAndSetSemester() {
        String semester = "test666";
        Course course = new Course("test123","test123",12,"test123");
        course.setSemester(semester);
        assertEquals(semester, course.getSemester());
    }



    @Test
    public void toCSV() {
        Course course = new Course("test123","test123",12,"test123");
        String csv1 = course.toCSV();
        System.out.println(csv1);
        assertEquals(csv1,"test123,test123,12,test123\n");
    }

    @Test
    public void testEqualsAndHashCode() {
        Course course = new Course("test123","test123",12,"test123");
        Course course1 = new Course("test123","test123",12,"test123");
        assertEquals(course, course1);
        assertEquals(course.hashCode(), course1.hashCode());
    }
}