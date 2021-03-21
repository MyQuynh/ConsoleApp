import java.util.ArrayList;

public class CourseManager {

    private ArrayList<Course> coursesList = new ArrayList<>();

    public CourseManager(){
        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12);
        Course course2 = new Course("COSC2429","Introduction to Programming", 12);
        Course course3 = new Course("ISY3414","Practical Database Concept", 12);
        Course course4 = new Course("COSC2500", "Intro to Computer Systems", 12);
        Course course5 = new Course("COSC2534","Building IT Systems", 12);
        Course course6 = new Course("COSC2652", "User-central Design", 12);
        Course course7 = new Course("COSC2430", "Web Programming", 12);
        Course course8 = new Course("COSC2081","Programming 1", 12);
        Course course9 = new Course("COSC2440", "Software Architecture: Design and Implementation", 12);
        Course course10 = new Course("COSC2539","Security in Computing and Information Technology", 12);

        this.coursesList.add(course1);
        this.coursesList.add(course2);
        this.coursesList.add(course3);
        this.coursesList.add(course4);
        this.coursesList.add(course5);
        this.coursesList.add(course6);
        this.coursesList.add(course7);
        this.coursesList.add(course8);
        this.coursesList.add(course9);
        this.coursesList.add(course10);
    }

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

    public void listOfCourses(){
        // Creating a list of courses and add it to the Courses array

        Course course1 = new Course("COSC2083","Introduction to Information Technology", 12);
        Course course2 = new Course("COSC2429","Introduction to Programming", 12);
        Course course3 = new Course("ISY3414","Practical Database Concept", 12);
        Course course4 = new Course("COSC2500", "Intro to Computer Systems", 12);
        Course course5 = new Course("COSC2534","Building IT Systems", 12);
        Course course6 = new Course("COSC2652", "User-central Design", 12);
        Course course7 = new Course("COSC2430", "Web Programming", 12);
        Course course8 = new Course("COSC2081","Programming 1", 12);
        Course course9 = new Course("COSC2440", "Software Architecture: Design and Implementation", 12);
        Course course10 = new Course("COSC2539","Security in Computing and Information Technology", 12);

        this.coursesList.add(course1);
        this.coursesList.add(course2);
        this.coursesList.add(course3);
        this.coursesList.add(course4);
        this.coursesList.add(course5);
        this.coursesList.add(course6);
        this.coursesList.add(course7);
        this.coursesList.add(course8);
        this.coursesList.add(course9);
        this.coursesList.add(course10);
    }

    @Override
    public String toString() {
        return "CourseManager{" +
                "coursesList=" + coursesList +
                '}';
    }
}
