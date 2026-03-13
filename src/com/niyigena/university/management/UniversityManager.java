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

        if (s == null) {
            throw new IllegalArgumentException("Student does not exist!");
        }

        if (c == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }

        if (c.getRoster().contains(s)) {
            throw new StudentAlreadyEnrolledException("Student already enrolled!");
        }

        if (c.getRoster().size() >= c.getCapacity()) {
            throw new CourseFullException("Course is full!");
        }

        c.enroll(s);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    // ⭐ ADD THIS MISSING METHOD
    public Course getCourse(String code) {
        return courses.get(code);
    }

    public List<Student> getDeansList() {
        return students.values().stream()
                .filter(s -> s.getGPA() > 3.5)
                .toList();
    }

    public Student getTopStudent() {
        return students.values().stream()
                .max(Comparator.comparingDouble(Student::getGPA))
                .orElse(null);
    }

    public void assignGrade(String studentID, String courseCode, double grade) throws Exception {

        Student s = getStudent(studentID);
        if (s == null) throw new Exception("Student not found.");

        Course c = getCourse(courseCode);
        if (c == null) throw new Exception("Course not found.");

        if (!c.getRoster().contains(s))
            throw new Exception("Student is not enrolled in this course.");

        s.addCourseGrade(c, grade); // <-- THIS UPDATES GPA
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
}