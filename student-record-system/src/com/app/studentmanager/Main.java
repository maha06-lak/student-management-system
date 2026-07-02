package com.app.studentmanager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final StudentManager manager = new StudentManager("students.txt");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=====================================");
        System.out.println(" STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewAllStudents();
                case "3" -> searchStudent();
                case "4" -> updateStudent();
                case "5" -> deleteStudent();
                case "6" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Course: ");
            String course = scanner.nextLine().trim();

            manager.addStudent(new Student(id, name, age, course));
            System.out.println("Student added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: ID and Age must be numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllStudents() {
        List<Student> all = manager.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n--- ALL STUDENTS ---");
        for (Student s : all) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = readInt();
        Optional<Student> found = manager.findById(id);
        if (found.isPresent()) {
            System.out.println(found.get());
        } else {
            System.out.println("No student found with ID " + id);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = readInt();

        if (manager.findById(id).isEmpty()) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter new Age: ");
        int age = readInt();

        System.out.print("Enter new Course: ");
        String course = scanner.nextLine().trim();

        boolean updated = manager.updateStudent(id, name, age, course);
        System.out.println(updated ? "Student updated successfully." : "Update failed.");
    }

    private static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = readInt();
        boolean deleted = manager.deleteStudent(id);
        System.out.println(deleted ? "Student deleted successfully." : "No student found with ID " + id);
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
