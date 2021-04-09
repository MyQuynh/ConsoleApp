package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void getAndSetId() {
        String studentId = "test666";
        Student student1 = new Student("test123","test123","test123");
        student1.setId(studentId);
        assertEquals(studentId, student1.getId());
    }


    @Test
    public void getAndSetName() {
        String studentName = "test666";
        Student student1 = new Student("test123","test123","test123");
        student1.setName(studentName);
        assertEquals(studentName, student1.getName());
    }


    @Test
    public void getAndSetBirthdate() {
        String studentDOB = "test666";
        Student student1 = new Student("test123","test123","test123");
        student1.setBirthdate(studentDOB);
        assertEquals(studentDOB, student1.getBirthdate());
    }


//    @Test
//    public void testToString() {
//    }

    @Test
    public void toCSV() {
        Student student1 = new Student("test123","test123","test123");
        String studentCSV = student1.toCSV();
        assertEquals(studentCSV, "test123,test123,test123\n");
    }

    @Test
    public void testEqualsAndHashCode() {
        Student student1 = new Student("test123","test123","test123");
        Student student2 = new Student("test123","test123","test123");
        assertEquals(student1, student2);
        assertEquals(student1.hashCode(), student2.hashCode());
    }

}