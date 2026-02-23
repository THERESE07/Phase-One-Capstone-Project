package com.niyigena.university.courses;

import com.niyigena.university.people.Student;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseName;
    private int credits;
    private int capacity;

    private List<Student> roster = new ArrayList<>();

    public Course(String name, int credits, int capacity) {
        this.courseName = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public List<Student> getRoster() { return roster; }

    public boolean isFull() {
        return roster.size() >= capacity;
    }

    public void addStudent(Student s) {
        roster.add(s);
    }
}
