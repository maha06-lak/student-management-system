package com.app.studentmanager;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Format used when saving to the text file: id,name,age,course
    public String toFileString() {
        return id + "," + name + "," + age + "," + course;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        String course = parts[3].trim();
        return new Student(id, name, age, course);
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d Name: %-15s Age: %-3d Course: %s", id, name, age, course);
    }
}
