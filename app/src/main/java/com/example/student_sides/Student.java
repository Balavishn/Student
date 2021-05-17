package com.example.student_sides;

public class Student implements Comparable<Student> {
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    String name;
    int grade;

    @Override
    public int compareTo(Student o) {
        if (o == null) {
            return -1;
        }
        int c = Integer.valueOf(grade).compareTo(o.grade);
        if (c != 0) {
            return c;
        }
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format("%s has grade %d", name, grade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
