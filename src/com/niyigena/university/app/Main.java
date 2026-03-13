package com.niyigena.university.app;

import com.niyigena.university.management.UniversityManager;
import com.niyigena.university.people.*;
import com.niyigena.university.courses.*;
import com.niyigena.university.exceptions.*;
import com.niyigena.university.persistence.FileManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();
        FileManager fileManager = new FileManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== UNIVERSITY SYSTEM =====");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade to Student");
            System.out.println("5. View Student Record");
            System.out.println("6. Generate Dean's List");
            System.out.println("7. Show Top Student");
            System.out.println("8. Exit Without Saving");
            System.out.println("9. Save & Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // 1. Register student
                case 1 -> {
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter student ID: ");
                    String id = sc.nextLine();

                    System.out.println("Type of student?");
                    System.out.println("1. Undergraduate");
                    System.out.println("2. Graduate");
                    int type = sc.nextInt();
                    sc.nextLine();

                    Student student;
                    if (type == 1) {
                        student = new UndergraduateStudent(name, age, id);
                    } else {
                        System.out.print("Enter research credits: ");
                        int rc = sc.nextInt();
                        sc.nextLine();
                        student = new GraduateStudent(name, age, id, rc);
                    }

                    manager.registerStudent(student);
                    System.out.println("Student registered successfully!");
                }

                // 2. Create course
                case 2 -> {
                    System.out.print("Enter course code: ");
                    String code = sc.nextLine();

                    System.out.print("Enter course name: ");
                    String cname = sc.nextLine();

                    System.out.print("Enter credits: ");
                    int credits = sc.nextInt();

                    System.out.print("Enter capacity: ");
                    int cap = sc.nextInt();

                    Course course = new Course(code, cname, credits, cap);
                    manager.createCourse(course);

                    System.out.println("Course created successfully!");
                }

                // 3. Enroll student
                case 3 -> {
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();

                    System.out.print("Enter course code: ");
                    String ccode = sc.nextLine();

                    try {
                        manager.enrollStudentInCourse(sid, ccode);
                        System.out.println("Enrollment successful!");
                    } catch (CourseFullException e) {
                        System.out.println("Failed: Course is full!");
                    } catch (StudentAlreadyEnrolledException e) {
                        System.out.println("Failed: Student already enrolled!");
                    }
                }

                // NEW: Assign Grade
                case 4 -> {
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();

                    System.out.print("Enter course code: ");
                    String ccode = sc.nextLine();

                    System.out.print("Enter grade (0–5): ");
                    double grade = sc.nextDouble();
                    sc.nextLine();

                    try {
                        manager.assignGrade(sid, ccode, grade);
                        System.out.println("Grade assigned successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                // 5. View student record
                case 5 -> {
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();

                    Student s = manager.getStudent(sid);

                    if (s == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.println("\n--- STUDENT RECORD ---");
                    System.out.println("Name: " + s.getName());
                    System.out.println("Age: " + s.getAge());
                    System.out.println("GPA: " + s.getGPA());

                    Map<Course, Double> grades = s.getCourseGrades();
                    if (grades.isEmpty()) {
                        System.out.println("No courses enrolled.");
                    } else {
                        System.out.println("Courses:");
                        grades.forEach((course, grade) ->
                                System.out.println("- " + course.getCourseCode() + " | Grade: " + grade));
                    }
                }

                // 6. Dean's list
                case 6 -> {
                    List<Student> deansList = manager.getDeansList();

                    System.out.println("\n--- DEAN'S LIST (GPA > 3.5) ---");
                    if (deansList.isEmpty()) {
                        System.out.println("No students qualify.");
                    } else {
                        deansList.forEach(s ->
                                System.out.println(s.getName() + " (" + s.getStudentID() + ") GPA: " + s.getGPA()));
                    }
                }

                // 7. Top student
                case 7 -> {
                    Student top = manager.getTopStudent();
                    if (top == null) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("\n--- TOP STUDENT ---");
                        System.out.println(top.getName() + " | GPA: " + top.getGPA());
                    }
                }

                // 8. Exit without saving
                case 8 -> {
                    System.out.println("Exiting without saving...");
                    return;
                }

                // 9. Save & Exit
                case 9 -> {
                    try {
                        fileManager.saveStudents(manager.getAllStudents());
                        System.out.println("Data saved successfully! Goodbye.");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }
}