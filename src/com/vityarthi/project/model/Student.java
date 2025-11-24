package com.vityarthi.project.model;

public class Student {

    private int studentId;
    private String studentName;
    private String registrationNumber;

    public Student() {}

    public Student(int studentId, String studentName, String registrationNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registrationNumber = registrationNumber;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Student [ID=" + studentId + ", Name=" + studentName + ", Reg. No.=" + registrationNumber + "]";
    }
}