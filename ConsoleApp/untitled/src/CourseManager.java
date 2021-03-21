import java.util.ArrayList;

public class CourseManager {

    private ArrayList<Course> coursesList = new ArrayList<>();

    public void add(Course course){
        this.coursesList.add(course);
    }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ArrayList<Course> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public String toString() {
        return "CourseManager{" +
                "coursesList=" + coursesList +
                '}';
    }
}
