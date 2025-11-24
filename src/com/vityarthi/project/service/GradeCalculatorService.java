package com.vityarthi.project.service;

import com.vityarthi.project.model.Grade;

public class GradeCalculatorService {

    public String calculateFinalGrade(double totalScore) {
        if (totalScore >= 90) {
            return "S";
        } else if (totalScore >= 80) {
            return "A";
        } else if (totalScore >= 70) {
            return "B";
        } else if (totalScore >= 60) {
            return "C";
        } else if (totalScore >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public double calculateClassAverage(java.util.List<Grade> grades) {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        double totalSum = 0;
        for (Grade grade : grades) {
            totalSum += grade.getTotalScore();
        }
        return totalSum / grades.size();
    }
}