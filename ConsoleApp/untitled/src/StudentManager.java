import java.util.ArrayList;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    public StudentManager(){
        DateManager dateManager = new DateManager();

        Student student1 = new Student("S3836322","Ngo My Quynh",dateManager.convertDateString("08/03/2001"));
        Student student2 = new Student("S3817852","Nguyen Thanh Thien",dateManager.convertDateString("06/11/2000"));
        Student student3 = new Student("S3836455","Nguyen Thanh Tuan",dateManager.convertDateString("14/12/1995"));
        Student student4 = new Student("S3818102","Pham Cong Minh",dateManager.convertDateString("05/05/2001"));
        Student student5 = new Student("S3818381","Vo Tran Truong Duy",dateManager.convertDateString("08/11/2001"));
        Student student6 = new Student("S3754105","Nguyen Dang Lam Phuong",dateManager.convertDateString("25/07/2000"));
        Student student7 = new Student("S3812649","Nguyen Pham Quoc Minh",dateManager.convertDateString("24/08/2001"));
        Student student8 = new Student("S3740853","Vu Phuong Thao",dateManager.convertDateString("18/09/1999"));
        Student student9 = new Student("S3804690","Nguyen Ngoc Dang Hung",dateManager.convertDateString("20/08/2000"));
        Student student10 = new Student("S3689902","Nguyen Cao Minh Duc",dateManager.convertDateString("21/02/1999"));

        this.students.add(student1);
        this.students.add(student2);
        this.students.add(student3);
        this.students.add(student4);
        this.students.add(student5);
        this.students.add(student6);
        this.students.add(student7);
        this.students.add(student8);
        this.students.add(student9);
        this.students.add(student10);
    }

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

            for (Student student : this.students){
                if (student.getId().equals(studentId)) {
                    flag = 1;
                    break;
                }
            }

            if(flag == 0){
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

//    public void listOfStudent(){
//
//    }

    @Override
    public String toString() {
        return "StudentManager{" +
                "students=" + students +
                '}';
    }
}
