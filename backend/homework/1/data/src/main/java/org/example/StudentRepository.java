package org.example;

public class StudentRepository {
    private Student[] students = new Student[100];
    private int currentIndex = 0;

    // Method to add a student to the repository
    public void addStudent(Student student) {
        if (currentIndex < students.length) {
            students[currentIndex++] = student;
            System.out.println("Student added to the repository: " + student.getName());
        } else {
            System.out.println("Student repository is full. Cannot add more students.");
        }
    }

    // Method to retrieve a student by ID
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student != null && student.getId() == id) {
                System.out.println("Student found by ID: " + student.getName());
                return student;
            }
        }
        System.out.println("Student not found by ID: " + id);
        return null;
    }

    // Method to retrieve a student by name
    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (student != null && student.getName().equals(name)) {
                System.out.println("Student found by name: " + student.getName());
                return student;
            }
        }
        System.out.println("Student not found by name: " + name);
        return null;
    }
}

