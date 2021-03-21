import java.util.ArrayList;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void add(Student student){
        this.students.add(student);
    }

    public String validStudent(){
        int flag = 0;
        String studentId = "";
        while (flag == 0){
            System.out.print("Please enter the student id: ");
            studentId = Main.scanner.nextLine();

            for (Student student : students){
                if (student.getId().equals(studentId)) {
                    flag = 1;
                    break;
                }
            }

            if(flag != 1){
                System.out.println("The student ID is not recognized by the system");
                System.out.println("Please try again");
            }
        }
        return studentId;
    }

    public Student getStudentById(String studentId){

        for(Student student : students){
            if(student.getId().equals(studentId)){
                return student;
            }
        }
        return null;

    }

    @Override
    public String toString() {
        return "StudentManager{" +
                "students=" + students +
                '}';
    }
}
