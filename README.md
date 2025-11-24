# Student Grade Calculator

## 1. Overview

Student Grade Calculator is one of the most important, useful, parts of teaching. For our course CSE2006 i.e Programming in Java,for calculating grades from tutorials,assignments and tests is not only takes our precious time but alse leads to human error,which results in unfair grades and creates headache while while trying to change  for majority of  the students .

To overcome these challenges we have built the Student Grade Calculator which can be helpful in following cases:-

Manazing Records Efficiently: Easily update,delete,view and add the information of all students ands their respective scores.

Effective Calculation: This tool ha sthe capability to automatically computes the grade of each student based on different assessments  and do the correct maths every time.

Get Instant Insights: It helps to generate a qui summary report very quickly for getting the information like top performers, class average and overall grade distribution.

## 2. Features

This application implements the following functional requirements:

- **F.R. 1: Full CRUD Functionality**
  - **Add, View, Update, and Delete** student records.
  - **Add, View, Update, and Delete** grade records for each student, covering different assessment components.

- **F.R. 2: Automated Grade Calculation**
  - Calculates the final letter grade (S, A, B, C, D, F) based on weighted marks for:
    - Continuous Assessment (CA)
    - Mid-Term Exam (MTE)
    - End-Term Exam (ETE)
  - The weighting logic is encapsulated within the `GradeCalculatorService`.

- **F.R. 3: Class Summary Reporting**
  - Generates a detailed class summary report in a `class_summary_report.txt` file.
  - The report includes each student's name, ID, individual marks, final letter grade, and the overall class average score.
  - Report generation is handled asynchronously using a separate thread to avoid blocking the main application flow.

## 3. Technologies and Tools Used

- **Core Language:** Java
- **Database Connectivity:** JDBC (Java Database Connectivity)
- **Key Java Concepts Used:**
  - **OOP:** Classes, Objects, Encapsulation
  - **Collections Framework:** `ArrayList` for in-memory data handling.
  - **I/O Streams:** `FileWriter` and `PrintWriter` for file-based reporting.
  - **Exception Handling:** `try-catch` blocks for `SQLException` and `InputMismatchException`.
  - **Multithreading:** `Runnable` interface and `Thread` class for concurrent report generation.
  - **Flow Control:** `if-else`, `switch-case`, and `while` loops for the CLI menu and logic.

## 4. Steps to Install and Run the Project

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- A relational database (e.g., MySQL, PostgreSQL, or an in-memory DB like H2).
- The appropriate JDBC driver `.jar` file for your database.

### Database Setup (Example for MySQL)

1.  Create a new database (e.g., `university_db`).
2.  Execute the following SQL scripts to create the required tables:

    ```sql
    CREATE TABLE students (
        student_id INT PRIMARY KEY AUTO_INCREMENT,
        student_name VARCHAR(100) NOT NULL,
        registration_number VARCHAR(20) UNIQUE NOT NULL
    );

    CREATE TABLE grades (
        grade_id INT PRIMARY KEY AUTO_INCREMENT,
        student_id INT,
        ca_marks DOUBLE,
        mte_marks DOUBLE,
        ete_marks DOUBLE,
        final_grade VARCHAR(2),
        FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
    );
    ```

### Configuration

1.  Place the JDBC driver `.jar` file in a `lib` folder within the project directory or add it to your classpath.
2.  Open the `src/com/vityarthi/project/db/DatabaseManager.java` file.
3.  Update the placeholder JDBC connection details with your actual database URL, username, and password:

    ```java
    private static final String DB_URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";
    ```

### Compilation and Execution

1.  Navigate to the `src` directory from your terminal.
2.  Compile the Java source files:

    ```bash
    javac com/vityarthi/project/app/MainApp.java
    ```

3.  Run the application:

    ```bash
    java com/vityarthi/project/app/MainApp
    ```

4.  Follow the on-screen menu to interact with the application. The generated report will be saved in the project's root directory as `class_summary_report.txt`.
