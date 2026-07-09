package studentregistrationmodule;

import java.util.Scanner;
import java.sql.Date;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentOperation studentOperation = new StudentOperation();
        AttendanceOperation attendanceOperation = new AttendanceOperation();

        int choice;

        do {
            System.out.println("\n=====Food Mess Waste Predictor =====");
            System.out.println("1. Register ");
            System.out.println("2. Login ");
            System.out.println("3. Exit ");
            System.out.println("Enter your choice :");

            choice = sc.nextInt();
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

                    Student loggedInStudent = studentOperation.login(loginEmail,loginPassword);

                    if (loggedInStudent != null) {

                        System.out.println("Login Successful.");
                        System.out.println("Welcome " + loggedInStudent.getStudentName());

                        int studentChoice;

                        do {

                            System.out.println("\n===== Student Menu =====");
                            System.out.println("1.Mark Attendance");
                            System.out.println("2.Logout");
                            System.out.print("Enter your choice: ");

                            studentChoice = sc.nextInt();

                            switch (studentChoice) {

                                case 1:
                                    System.out.println("\n=====Mark Attendance====");
                                    System.out.println("1.Breakfast");
                                    System.out.println("2.Lunch");
                                    System.out.println("3.Dinner");
                                    System.out.print("Enter your choice:");

                                    int mealChoice = sc.nextInt();

                                    if (mealChoice < 1 || mealChoice > 3) {
                                        System.out.println("Invalid meal choice.");
                                        break;
                                    }

                                    Date today = new Date(System.currentTimeMillis());

                                    boolean breakfast = (mealChoice == 1);
                                    boolean lunch = (mealChoice == 2);
                                    boolean dinner = (mealChoice == 3);

                                    Attendance attendance = new Attendance(loggedInStudent.getStudentId(),today, breakfast, lunch, dinner);

                                    boolean success = attendanceOperation.markAttendance(attendance, mealChoice);

                                    if (success) {
                                        System.out.println("Attendance marked successfully.");
                                    } else {
                                        System.out.println("Failed to mark attendance.");
                                    }

                                    break;

                                case 2:
                                    System.out.println("Logged out successfully.");
                                    break;

                                default:
                                    System.out.println("Invalid Choice");
                            }
                        }while (studentChoice != 2);
                    }
                    else {
                        System.out.println("Invalid Email or Password.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using Mess Food Waste Predictor.");
                    break;

                default:
                    System.out.println("Invalid Choice.");

            }
        }while (choice != 3);

        sc.close();
    }
}