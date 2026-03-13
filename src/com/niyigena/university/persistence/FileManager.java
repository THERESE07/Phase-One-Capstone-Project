package com.niyigena.university.persistence;

import com.niyigena.university.people.*;
import com.niyigena.university.courses.Course;

import java.io.*;
import java.util.*;

public class FileManager {

    // ----------------------------
    // SAVE STUDENTS + GRADES
    // ----------------------------
    public void saveStudentsAndGrades(List<Student> students) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

        for (Student s : students) {
            // SAVE STUDENT BASIC INFO
            String type = (s instanceof GraduateStudent) ? "GRAD" : "UNDER";

            bw.write("STUDENT," + type + "," +
                    s.getStudentID() + "," +
                    s.getName() + "," +
                    s.getAge() + "," +
                    s.getGPA());
            bw.newLine();

            // SAVE GRADES
            for (Map.Entry<Course, Double> e : s.getCourseGrades().entrySet()) {
                bw.write("GRADE," +
                        s.getStudentID() + "," +
                        e.getKey().getCourseCode() + "," +
                        e.getValue());
                bw.newLine();
            }
        }

        bw.close();
    }


    // ----------------------------
    // LOAD STUDENTS + GRADES
    // ----------------------------
    public void loadStudentsAndGrades(Map<String, Student> studentMap,
                                      Map<String, Course> courseMap) throws IOException {

        File file = new File("students.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {

            String[] p = line.split(",");

            switch (p[0]) {

                case "STUDENT": {
                    String type = p[1];
                    String id = p[2];
                    String name = p[3];
                    int age = Integer.parseInt(p[4]);
                    double gpa = Double.parseDouble(p[5]);

                    Student s = (type.equals("GRAD"))
                            ? new GraduateStudent(name, age, id, 0)
                            : new UndergraduateStudent(name, age, id);

                    // GPA will naturally calculate once grades load
                    studentMap.put(id, s);
                    break;
                }

                case "GRADE": {
                    String stuId = p[1];
                    String courseCode = p[2];
                    double grade = Double.parseDouble(p[3]);

                    Student stu = studentMap.get(stuId);
                    Course course = courseMap.get(courseCode);

                    if (stu != null && course != null) {
                        stu.addCourseGrade(course, grade);
                    }
                    break;
                }
            }
        }

        br.close();
    }


    // ----------------------------
    // SAVE COURSES
    // ----------------------------
    public void saveCourses(List<Course> courses) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("courses.txt"));

        for (Course c : courses) {
            bw.write("COURSE," +
                    c.getCourseCode() + "," +
                    c.getTitle() + "," +
                    c.getCredits() + "," +
                    c.getCapacity());
            bw.newLine();
        }

        bw.close();
    }

    public void saveStudents(List<Student> students) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

        for (Student s : students) {
            String type = (s instanceof GraduateStudent) ? "GRAD" : "UNDER";

            bw.write(type + "," +
                    s.getStudentID() + "," +
                    s.getName() + "," +
                    s.getAge() + "," +
                    s.getGPA());

            if (s instanceof GraduateStudent g) {
                bw.write("," + g.calculateTuition());
            }

            bw.newLine();
        }
        bw.close();
    }


    // ----------------------------
    // LOAD COURSES
    // ----------------------------
    public List<Course> loadCourses() throws IOException {

        List<Course> list = new ArrayList<>();

        File file = new File("courses.txt");
        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");

            if (!p[0].equals("COURSE")) continue;

            String code = p[1];
            String name = p[2];
            int credits = Integer.parseInt(p[3]);
            int capacity = Integer.parseInt(p[4]);

            list.add(new Course(code, name, credits, capacity));
        }

        br.close();
        return list;
    }
}