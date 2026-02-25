package com.niyigena.university.people;

public class GraduateStudent extends Student {
    private int credits;

    public GraduateStudent(String name, int age, String id, int credits) {
        super(name, age, id);
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "GraduateStudent{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", studentID='" + getStudentID() + '\'' +
                ", GPA=" + getGPA() +
                ", researchCredits=" + credits +
                '}';
    }

    @Override
    public double calculateTuition() {
        return (credits * 200) + 500; // per-credit + research fee
    }
}