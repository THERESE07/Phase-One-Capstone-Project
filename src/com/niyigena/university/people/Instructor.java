package com.niyigena.university.people;

public class Instructor extends Person {

    private String department;

    public Instructor(String name, int age, String department) {
        super(name, age);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) {
        this.department = department;
    }
}
