package Model;

import Model.Course;
import Model.Student;

import java.util.Arrays;
import java.util.List;

public class StudentEnrollment implements AdditionService {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrollment(){}

    public List<String> propertyStudentEnrollment = Arrays.asList("STUDENT_ID", "COURSE_ID", "SEMESTER");


    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourses() {
        return course;
    }

    public void setCourses(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toCSV() {
        return this.student.getId()+","+this.course.getId()+","+this.semester+"\n";
    }
}
