package Model;

import java.util.Arrays;
import java.util.List;

public class StudentEnrollment implements Model {
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
    public String toString() {
        return "StudentEnrollment{" +
                "student=" + student +
                ", course=" + course +
                ", semester='" + semester + '\'' +
                ", propertyStudentEnrollment=" + propertyStudentEnrollment +
                '}';
    }

    @Override
    public String toCSV() {
        return this.student.getId()+","+this.course.getId()+","+this.semester+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrollment that = (StudentEnrollment) o;
        return course.equals(that.course) &&
                student.equals(that.student) &&
                semester.equals(that.semester);
    }

}
