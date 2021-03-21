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

    public String validCourse(){
        int flag = 0;
        String courseId = "";
        while (flag == 0){
            System.out.print("Please enter the course ID: ");
            courseId = Main.scanner.nextLine();

            for (Course course : coursesList){
                if (course.getId().equals(courseId)) {
                    flag = 1;
                    break;
                }
            }

            if(flag != 1){
                System.out.println("The course ID is not recognized by the system");
                System.out.println("Please try again");
            }
        }
        return courseId;
    }

    public Course getCourseById(String courseId){
        for(Course course : coursesList){
            if(course.getId().equals(courseId)){
                return course;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CourseManager{" +
                "coursesList=" + coursesList +
                '}';
    }
}
