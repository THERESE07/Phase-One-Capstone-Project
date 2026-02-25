package com.niyigena.university.management;

import com.niyigena.university.people.Student;
import com.niyigena.university.courses.Course;
import com.niyigena.university.exceptions.*;

import java.util.*;

public class UniversityManager {

    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    public void registerStudent(Student s) {
        students.put(s.getStudentID(), s);
    }

    public void createCourse(Course c) {
        courses.put(c.getCourseCode(), c);
    }

    public void enrollStudentInCourse(String studentID, String courseCode)
            throws CourseFullException, StudentAlreadyEnrolledException {

        Student s = students.get(studentID);
        Course c = courses.get(courseCode);

        if (c.getRoster().contains(s)) {
            throw new StudentAlreadyEnrolledException("Student already enrolled!");
        }

        if (c.getRoster().size() >= c.getCapacity()) {
            throw new CourseFullException("Course is full!");
        }

        c.enroll(s);
    }
}
