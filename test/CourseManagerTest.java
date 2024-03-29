import Model.Course;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class CourseManagerTest {

    @Before
    public void resetScanner() {
        Main.scanner = new Scanner(System.in);
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();


    @Test
    // Testing the adding course into the list
    public void add() {
        Course course = new Course("Test123","Test123",22,"2021A");
        CourseManager courseManager = new CourseManager();
        int lastLengthOfList = (courseManager.getCoursesList()).size();
        courseManager.add(course);
        assertEquals(lastLengthOfList, courseManager.getCoursesList().size() - 1);

    }

    @Test
    public void getAndSetCoursesList() {
        CourseManager courseManager = new CourseManager();
        ArrayList<Course> courseArrayList = new ArrayList<>();
        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12, "2021A");
        Course course2 = new Course("COSC2429","Introduction to Programming", 12,"2021A");

        courseArrayList.add(course1);
        courseArrayList.add(course2);
        courseManager.setCoursesList(courseArrayList);
        assertEquals(courseArrayList, courseManager.getCoursesList());

    }

    @Test
    public void validCourse() {
        CourseManager courseManager = new CourseManager();
        String validCourseId = "COSC2083";
        String invalidCourseId = "test0123";
        systemInMock.provideLines(invalidCourseId, validCourseId);
        String returnCourseId = courseManager.validCourse("2021A");
        assertEquals(returnCourseId, validCourseId);
    }

    @Test
    public void showCourseListBySemester() {
        CourseManager courseManager = new CourseManager();
        courseManager.showCoursesList();
    }

    @Test
    public void getCourseByIdAndSemester() {
        CourseManager courseManager = new CourseManager();
        Course course = new Course("COSC2083","Introduction to Information Technology", 12, "2021A");
        Course courseReturn = courseManager.getCourseByIdAndSemester("COSC2083", "2021A");
        assertEquals(course, courseReturn);
    }

    @Test
    public void showCoursesList() {
        CourseManager courseManager = mock(CourseManager.class);
        courseManager.showCoursesList();
        verify(courseManager,times(1)).showCoursesList();
    }

    @Test
    public void readFile() {
        CourseManager courseManager = new CourseManager();
        try {
            courseManager.readFile("course.csv");
            assertTrue(true);
        } catch (IOException e) {
            fail();
        }
    }
}