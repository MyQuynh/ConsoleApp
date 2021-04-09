import Model.Course;
import Model.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManager(){

//        Student student1 = new Student("S3836322","Ngo My Quynh","08/03/2001");
//        Student student2 = new Student("S3817852","Nguyen Thanh Thien","06/11/2000");
//        Student student3 = new Student("S3836455","Nguyen Thanh Tuan","14/12/1995");
//        Student student4 = new Student("S3818102","Pham Cong Minh","05/05/2001");
//        Student student5 = new Student("S3818381","Vo Tran Truong Duy","08/11/2001");
//        Student student6 = new Student("S3754105","Nguyen Dang Lam Phuong","25/07/2000");
//        Student student7 = new Student("S3812649","Nguyen Pham Quoc Minh","24/08/2001");
//        Student student8 = new Student("S3740853","Vu Phuong Thao","18/09/1999");
//        Student student9 = new Student("S3804690","Nguyen Ngoc Dang Hung","20/08/2000");
//        Student student10 = new Student("S3689902","Nguyen Cao Minh Duc","21/02/1999");
//
//        this.students.add(student1);
//        this.students.add(student2);
//        this.students.add(student3);
//        this.students.add(student4);
//        this.students.add(student5);
//        this.students.add(student6);
//        this.students.add(student7);
//        this.students.add(student8);
//        this.students.add(student9);
//        this.students.add(student10);
        try {
            readFile("student.csv");
        } catch (IOException e) {
            System.out.println("Cannot find the file");;
        }
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
        //Scanner scannerStudent = new Scanner(System.in);
        while (flag == 0){

            this.showStudentList();

            System.out.print("Please enter the student id: ");
            studentId = Main.scanner.nextLine();
            //System.out.println(studentId);
//            System.out.println((Integer.valueOf(studentId)));
//            System.out.println(studentId);

            //studentId = scannerStudent.nextLine();

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
        //scannerStudent.close();
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

    public void showStudentList(){
        System.out.println();
        System.out.println("---------------");
        System.out.println("LIST OF STUDENTS");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| " + String.format("%1$-18s", "STUDENT_ID" + " | " + String.format("%1$-50s", "STUDENT_NAME") + " | " + String.format("%1$18s", "DATE_OF_BIRTH") + " | "));
        for (Student student : this.students) {
            System.out.println("| " + String.format("%1$-10s", student.getId()) + " | " + String.format("%1$-50s", student.getName()) + " | " + String.format("%1$18s", student.getBirthdate()) + " | ");
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }

    public void readFile(String fileName) throws IOException {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the file");
        }
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Student student = new Student(data[0], data[1], data[2]);
            this.students.add(student);
            // do something with the data
        }
        csvReader.close();
    }

}
