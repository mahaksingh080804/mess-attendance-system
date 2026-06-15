package studentregistrationmodule;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Student Registration =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required.");
            sc.close();
            return;
        }

        StudentOperation studentOperation = new StudentOperation();

        // Duplicate Email Check
        if (studentOperation.emailExists(email)) {
            System.out.println("Email already registered.");
            sc.close();
            return;
        }

        // Create Student Object
        Student student = new Student(name, email, password);

        // Register Student
        int rows = studentOperation.registerStudent(student);

        if (rows == 1) {
            System.out.println("Registration Successful.");
        } else {
            System.out.println("Registration Failed.");
        }

        sc.close();
    }
}