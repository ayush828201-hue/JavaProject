# Project Statement: Student Grade Calculator

## 1. Problem Statement

In academic settings, the process of calculating, recording, and summarizing student grades is often performed manually. This traditional approach is time-consuming, labor-intensive, and highly susceptible to human error, especially when dealing with a large number of students and various weighted assessment components (e.g., continuous assessments, mid-term exams, end-term exams). Errors in calculation can lead to incorrect final grades, causing dissatisfaction and requiring tedious re-verification. There is a need for a reliable, efficient, and automated system to manage this process.

## 2. Scope of the Project

This project aims to develop a simple but robust Java-based Command Line Interface (CLI) application named **"Student Grade Calculator."** The application will serve as a centralized tool for instructors to manage student records and their grades for the course **CSE2006: Programming in Java**.

The core functionalities will include:
- **Student and Grade Management:** Securely performing CRUD (Create, Read, Update, Delete) operations for student and grade data stored in a database.
- **Automated Grade Calculation:** Automatically calculating the final letter grade for each student based on predefined weightings for different assessment types.
- **Reporting:** Generating a comprehensive class summary report that includes individual student performance and the overall class average, and saving this report to a text file.
- **Error Handling:** Implementing robust error handling to manage invalid user inputs and potential database connectivity issues.
- **Concurrency:** Demonstrating basic multithreading by generating the class report asynchronously.

The application will be built entirely as a console-based tool, ensuring it is lightweight and runs in a standard Java environment without a graphical user interface.

## 3. Target Users

The primary target users for this application are:
- **Instructors / Faculty:** To help them efficiently manage student grades, reduce manual calculation errors, and get a quick overview of class performance.
- **Teaching Assistants (TAs):** To assist faculty in the administrative task of grade management and reporting.

## 4. High-Level Features

- **Menu-Driven Interface:** An intuitive and easy-to-navigate CLI menu for accessing all functionalities.
- **Student Record Management:** Add new students, view the list of all students, update student details, and remove students from the system.
- **Grade Entry and Updates:** Input and modify marks for various assessment components (e.g., CA, MTE, ETE) for each student.
- **Final Grade Calculation:** Automatically compute the final letter grade (e.g., S, A, B, C) based on the entered marks and their respective weightings.
- **Class Summary Report:** Generate and export a `.txt` file containing a detailed report of all students, their marks, final grades, and the class average score.
- **Asynchronous Report Generation:** Use a separate thread to generate the report, allowing the user to continue interacting with the application.
