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

    public Course(String code, String title, int credits, int capacity ) {
        this.courseCode = code;
        this.title = title;
        this.credits = credits;
        this.capacity = capacity;

    }



    public List<Student> getRoster() { return roster; }
    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }


    public int getCapacity() { return capacity; }

    public String getCourseCode() { return courseCode; }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + courseCode + '\'' +
                ", name='" + title + '\'' +
                ", credits=" + credits +
                ", capacity=" + capacity +
                ", enrolledStudents=" + roster.size() +
                '}';
    }

    public void enroll(Student s) {
        roster.add(s);
    }
}