import Model.Course;
import Model.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class StudentManagerTest {

    @Before
    public void resetScanner() {
        Main.scanner = new Scanner(System.in);
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void getAndSetStudent() {
        StudentManager studentManager = new StudentManager();
        ArrayList<Student> studentArrayList = new ArrayList<>();
        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
        Student student2 = new Student("S3817852","Nguyen Thanh Thien","06/11/2000");

        studentArrayList.add(student1);
        studentArrayList.add(student2);
        studentManager.setStudents(studentArrayList);
        assertEquals(studentArrayList, studentManager.getStudents());

    }

    @Test
    public void add() {
        StudentManager studentManager = new StudentManager();
        int lengthOfListBefore = studentManager.getStudents().size();
        Student student1 = new Student("test","test","test");
        studentManager.add(student1);
        int lengthOfListAfter = studentManager.getStudents().size();
        assertEquals(lengthOfListBefore, lengthOfListAfter-1);
    }

    @Test
    public void validStudent() {
        StudentManager studentManager = new StudentManager();
        systemInMock.provideLines("wrongId","S3836322");
        String studentId = studentManager.validStudent();
        assertEquals("S3836322", studentId);
    }

    @Test
    public void getStudentById() {
        // Case 1: Invalid student will return null value
        StudentManager studentManager = new StudentManager();
        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
        Student invalidStudent = studentManager.getStudentById("23");
        Student validStudent = studentManager.getStudentById("S3836322");
        // Check if it null
        assertNull(invalidStudent);
        assertEquals(validStudent,student1);
    }

    @Test
    public void showStudentList() {
        StudentManager studentManager = mock(StudentManager.class);
        studentManager.showStudentList();
        verify(studentManager,times(1)).showStudentList();
    }

    @Test
    public void readFile() {
        StudentManager studentManager = new StudentManager();
        try {
            studentManager.readFile("student.csv");
        } catch (IOException e) {
            fail();
        }
    }

}