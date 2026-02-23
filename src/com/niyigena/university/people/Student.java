package com.niyigena.university.people;




public abstract class Student extends Person {

    private String studentID;
    private double gpa;



    public Student(String name, int age, String studentID) {
        super(name, age);
        this.studentID = studentID;
    }

    public String getStudentID() { return studentID; }
    public double getGPA() { return gpa; }

    public void setGPA(double gpa) { this.gpa = gpa; }


    }

