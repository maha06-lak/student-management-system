package com.app.studentmanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentManager {

    private final List<Student> students = new ArrayList<>();
    private final String fileName;

    public StudentManager(String fileName) {
        this.fileName = fileName;
        loadFromFile();
    }

    public void addStudent(Student student) {
        boolean exists = students.stream().anyMatch(s -> s.getId() == student.getId());
        if (exists) {
            throw new IllegalArgumentException("A student with ID " + student.getId() + " already exists.");
        }
        students.add(student);
        saveToFile();
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst();
    }

    public boolean updateStudent(int id, String name, int age, String course) {
        Optional<Student> found = findById(id);
        if (found.isEmpty()) {
            return false;
        }
        Student student = found.get();
        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                writer.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return; // nothing to load yet
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    students.add(Student.fromFileString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
