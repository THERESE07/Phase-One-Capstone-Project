package com.niyigena.university.people;

public class Instructor extends Person {
    private String department;

    public Instructor(String name, int age, String department) {
        super(name, age);
        this.department = department;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public double calculateTuition() {
        return 0; // instructors do not pay tuition
    }
}