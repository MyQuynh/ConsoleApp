import Model.Course;
import Model.Student;
import Model.StudentEnrollment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentMemoryManager studentMemoryManager = new StudentMemoryManager();

        studentMemoryManager.execute();
    }
}
