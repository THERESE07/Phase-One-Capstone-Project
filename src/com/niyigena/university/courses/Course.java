package com.niyigena.university.courses;

import com.niyigena.university.people.Student;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String title;
    private int credits;
    private int capacity;

    private List<Student> roster = new ArrayList<>();

    public Course(String code, String title, int credits, int capacity) {
        this.courseCode = code;
        this.title = title;
        this.credits = credits;
        this.capacity = capacity;
    }



    public List<Student> getRoster() { return roster; }
    public int getCapacity() { return capacity; }
    public String getCourseCode() { return courseCode; }



    public void enroll(Student s) {
        roster.add(s);
    }
}