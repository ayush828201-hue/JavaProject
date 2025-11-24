package com.vityarthi.project.model;

public class Grade {

    private int gradeId;
    private int studentId;
    private double caMarks;
    private double mteMarks;
    private double eteMarks;
    private String finalGrade;

    public Grade() {}

    public Grade(int studentId, double caMarks, double mteMarks, double eteMarks) {
        this.studentId = studentId;
        this.caMarks = caMarks;
        this.mteMarks = mteMarks;
        this.eteMarks = eteMarks;
    }

    public int getGradeId() {
        return gradeId;
    }



    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getCaMarks() {
        return caMarks;
    }

    public void setCaMarks(double caMarks) {
        this.caMarks = caMarks;
    }

    public double getMteMarks() {
        return mteMarks;
    }

    public void setMteMarks(double mteMarks) {
        this.mteMarks = mteMarks;
    }

    public double getEteMarks() {
        return eteMarks;
    }

    public void setEteMarks(double eteMarks) {
        this.eteMarks = eteMarks;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public double getTotalScore() {
        return (caMarks * 0.20) + (mteMarks * 0.30) + (eteMarks * 0.50);
    }

    @Override
    public String toString() {
        return "Grade [CA=" + caMarks + ", MTE=" + mteMarks + ", ETE=" + eteMarks + ", Final=" + (finalGrade != null ? finalGrade : "N/A") + "]";
    }
}