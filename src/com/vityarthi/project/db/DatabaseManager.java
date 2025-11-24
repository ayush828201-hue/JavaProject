package com.vityarthi.project.db;

import com.vityarthi.project.model.Student;
import com.vityarthi.project.model.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER = "root";
    private static final String PASS = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_name, registration_number) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentName());
            pstmt.setString(2, student.getRegistrationNumber());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("registration_number")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }
    
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    public boolean addOrUpdateGrade(Grade grade) {
        String selectSql = "SELECT grade_id FROM grades WHERE student_id = ?";
        String updateSql = "UPDATE grades SET ca_marks = ?, mte_marks = ?, ete_marks = ?, final_grade = ? WHERE student_id = ?";
        String insertSql = "INSERT INTO grades (student_id, ca_marks, mte_marks, ete_marks, final_grade) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection()) {
            PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
            selectPstmt.setInt(1, grade.getStudentId());
            ResultSet rs = selectPstmt.executeQuery();

            String sql;
            if (rs.next()) {
                sql = updateSql;
            } else {
                sql = insertSql;
            }
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if(sql.equals(updateSql)){
                pstmt.setDouble(1, grade.getCaMarks());
                pstmt.setDouble(2, grade.getMteMarks());
                pstmt.setDouble(3, grade.getEteMarks());
                pstmt.setString(4, grade.getFinalGrade());
                pstmt.setInt(5, grade.getStudentId());
            }else{
                 pstmt.setInt(1, grade.getStudentId());
                pstmt.setDouble(2, grade.getCaMarks());
                pstmt.setDouble(3, grade.getMteMarks());
                pstmt.setDouble(4, grade.getEteMarks());
                pstmt.setString(5, grade.getFinalGrade());
            }


            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error adding or updating grade: " + e.getMessage());
            return false;
        }
    }
    
    public List<String> getComprehensiveGradeReportData() {
        List<String> reportData = new ArrayList<>();
        String sql = "SELECT s.student_name, s.registration_number, g.ca_marks, g.mte_marks, g.ete_marks, g.final_grade " +
                     "FROM students s JOIN grades g ON s.student_id = g.student_id " +
                     "ORDER BY s.student_name";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String line = String.format("Student: %s (%s), CA: %.1f, MTE: %.1f, ETE: %.1f, Final Grade: %s",
                    rs.getString("student_name"),
                    rs.getString("registration_number"),
                    rs.getDouble("ca_marks"),
                    rs.getDouble("mte_marks"),
                    rs.getDouble("ete_marks"),
                    rs.getString("final_grade")
                );
                reportData.add(line);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching comprehensive report data: " + e.getMessage());
        }
        return reportData;
    }
}