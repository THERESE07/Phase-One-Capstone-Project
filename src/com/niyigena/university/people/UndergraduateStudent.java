package com.niyigena.university.people;

public class UndergraduateStudent extends Student {
    public UndergraduateStudent(String name, int age, String id) {
        super(name, age, id);
    }

    @Override
    public String toString() {
        return "UndergraduateStudent{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", studentID='" + getStudentID() + '\'' +
                ", GPA=" + getGPA() +
                '}';
    }

    @Override
    public double calculateTuition() {
        return 1500; // flat rate
    }
}