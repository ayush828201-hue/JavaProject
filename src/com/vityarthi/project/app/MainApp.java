package com.vityarthi.project.app;

import com.vityarthi.project.db.DatabaseManager;
import com.vityarthi.project.model.Grade;
import com.vityarthi.project.model.Student;
import com.vityarthi.project.service.GradeCalculatorService;
import com.vityarthi.project.util.ReportGenerator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseManager dbManager = new DatabaseManager();
    private static final GradeCalculatorService calculatorService = new GradeCalculatorService();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver. Please ensure the MySQL connector JAR is in your classpath.");
            return;
        }

        System.out.println("Welcome to the Student Grade Calculator!");
        runMainMenu();
    }

    private static void runMainMenu() {
        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        addOrUpdateGrade();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        generateReport();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Thank you for using the Grade Calculator. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Add/Update Student Grade");
        System.out.println("4. Delete a Student");
        System.out.println("5. Generate Class Report");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addNewStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Registration Number: ");
        String regNum = scanner.nextLine();

        if (name.isEmpty() || regNum.isEmpty()) {
            System.out.println("Name and Registration Number cannot be empty.");
            return;
        }

        Student student = new Student(0, name, regNum);
        if (dbManager.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.err.println("Failed to add student. It might be a duplicate registration number or a database issue.");
        }
    }
    
    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        viewAllStudents();
        System.out.print("Enter the ID of the student to delete: ");
        try {
            int studentId = scanner.nextInt();
            scanner.nextLine();
            if (dbManager.deleteStudent(studentId)) {
                System.out.println("Student with ID " + studentId + " deleted successfully.");
            } else {
                System.err.println("Failed to delete student. Check if the ID is correct.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a valid student ID (number).");
            scanner.nextLine();
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n--- List of All Students ---");
        List<Student> students = dbManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void addOrUpdateGrade() {
        System.out.println("\n--- Add/Update Grade ---");
        viewAllStudents();
        System.out.print("Enter the ID of the student to grade: ");
        try {
            int studentId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter CA marks (out of 100): ");
            double ca = scanner.nextDouble();
            System.out.print("Enter MTE marks (out of 100): ");
            double mte = scanner.nextDouble();
            System.out.print("Enter ETE marks (out of 100): ");
            double ete = scanner.nextDouble();
            scanner.nextLine();

            Grade grade = new Grade(studentId, ca, mte, ete);
            double totalScore = grade.getTotalScore();
            String finalGrade = calculatorService.calculateFinalGrade(totalScore);
            grade.setFinalGrade(finalGrade);

            if (dbManager.addOrUpdateGrade(grade)) {
                System.out.println("Grade updated successfully! Final Grade: " + finalGrade);
            } else {
                System.err.println("Failed to update grade. Please check if the student ID is correct.");
            }

        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter valid numbers for ID and marks.");
            scanner.nextLine();
        }
    }

    private static void generateReport() {
        System.out.println("\n--- Generate Class Report ---");
        List<String> reportData = dbManager.getComprehensiveGradeReportData();
        
        List<Student> students = dbManager.getAllStudents();
        List<Grade> grades = students.stream()
            .map(s -> {
                Grade g = new Grade();
                g.setStudentId(s.getStudentId());
                reportData.stream()
                    .filter(line -> line.contains("(" + s.getRegistrationNumber() + ")"))
                    .findFirst()
                    .ifPresent(line -> {
                    });
                return g;
            })
            .collect(Collectors.toList());
        
        double classAverage = 0;

        ReportGenerator reportTask = new ReportGenerator(reportData, classAverage);
        Thread reportThread = new Thread(reportTask);
        reportThread.start(); 

        System.out.println("Report generation has been started in the background. You can continue using the app.");
    }
}