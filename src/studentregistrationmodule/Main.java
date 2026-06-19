package studentregistrationmodule;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentOperation studentOperation = new StudentOperation();

        System.out.println("\n=====Food Mess Waste Predictor =====");
        System.out.println("1.Register ");
        System.out.println("2.Login ");
        System.out.println("3.Exit ");
        System.out.println("Enter your choice :");

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {

            case 1:
                System.out.println("======Student Registration======");

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                // Validation
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    System.out.println("All fields are required.");
                    break;
                }

                // Duplicate Email Check
                if (studentOperation.emailExists(email)) {
                    System.out.println("Email already registered.");
                    break;
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

                break;

            case 2:
                System.out.println("======Student Login======");

                System.out.print("Enter Email: ");
                String loginEmail = sc.nextLine();

                System.out.print("Enter Password: ");
                String loginPassword = sc.nextLine();

                if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
                    System.out.println("Email and Password are required.");
                    break;
                }

               boolean isLoggedIn = studentOperation.login(loginEmail,loginPassword);

                if (isLoggedIn) {
                    System.out.println("Login Successful.");
                } else {
                    System.out.println("Invalid Email or Password.");
                }
                break;

            case 3:
                System.out.println("Thank you for using Mess Food Waste Predictor.");
                break;

            default:
                System.out.println("Invalid Choice.");

        }
        sc.close();
    }
}