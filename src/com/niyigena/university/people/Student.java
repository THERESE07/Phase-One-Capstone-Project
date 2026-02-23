package com.niyigena.university.people;

import com.niyigena.university.courses.Course;
import java.util.HashMap;
import java.util.Map;

public abstract class Student extends Person {

    private String studentID;
    private double gpa;

    // course → grade
    private Map<Course, Double> courseGrades = new HashMap<>();

    public Student(String name, int age, String studentID) {
        super(name, age);
        this.studentID = studentID;
    }

    public String getStudentID() { return studentID; }
    public double getGPA() { return gpa; }

    public void setGPA(double gpa) { this.gpa = gpa; }

    public Map<Course, Double> getCourseGrades() {
        return courseGrades;
    }
}