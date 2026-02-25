package com.niyigena.university.app;

import com.niyigena.university.management.UniversityManager;
import com.niyigena.university.people.*;
import com.niyigena.university.courses.*;
import com.niyigena.university.exceptions.*;
import com.niyigena.university.persistence.FileManager;

import java.io.IOException;
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
            System.out.println("4. Exit Without Saving");
            System.out.println("5. Save & Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear leftover newline

            switch (choice) {

                // -------------------------------------
                // 1. REGISTER STUDENT
                // -------------------------------------
                case 1:
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
                        student = new GraduateStudent(name, age, id, rc);
                    }

                    manager.registerStudent(student);
                    System.out.println("Student registered successfully!");
                    break;

                // -------------------------------------
                // 2. CREATE COURSE
                // -------------------------------------
                case 2:
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
                    break;

                // -------------------------------------
                // 3. ENROLL STUDENT IN COURSE
                // -------------------------------------
                case 3:
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
                    break;

                // -------------------------------------
                // 4. EXIT WITHOUT SAVING
                // -------------------------------------
                case 4:
                    System.out.println("Exiting without saving...");
                    return;

                // -------------------------------------
                // 5. SAVE & EXIT
                // -------------------------------------
                case 5:
                    try {
                        fileManager.saveStudents(manager.getAllStudents());
                        System.out.println("Data saved successfully! Goodbye.");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}