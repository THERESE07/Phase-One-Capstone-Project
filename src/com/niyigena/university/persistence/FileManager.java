package com.niyigena.university.persistence;

import com.niyigena.university.people.Student;
import java.io.*;
import java.util.List;

public class FileManager {

    public void saveStudents(List<Student> students) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

        for (Student s : students) {
            bw.write(s.getStudentID() + "," + s.getName() + "," + s.getAge() + "," + s.getGPA());
            bw.newLine();
        }
        bw.close();
    }
}
