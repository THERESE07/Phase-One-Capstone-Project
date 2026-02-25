package com.niyigena.university.people;

import com.niyigena.university.courses.Course;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private String studentID;
    private double GPA;
    private Map<Course, Double> courseGrades = new HashMap<>();

    public Student(String name, int age, String studentID) {
        super(name, age);
        this.studentID = studentID;
    }

    public String getStudentID() { return studentID; }
    public double getGPA() { return GPA; }

    public void addCourseGrade(Course course, double grade) {
        courseGrades.put(course, grade);
        updateGPA();
    }

    private void updateGPA() {
        if (courseGrades.isEmpty()) {
            GPA = 0;
            return;
        }
        GPA = courseGrades.values().stream().mapToDouble(g -> g).average().orElse(0);
    }

    @Override
    public String toString() {
        return "Student{name='" + getName() +
                "', age=" + getAge() +
                ", studentID='" + studentID +
                "', GPA=" + GPA +
                ", courses=" + courseGrades.keySet() +
                "}";
    }

    @Override
    public double calculateTuition() {
        return 0; // Overridden in subclasses
    }
}