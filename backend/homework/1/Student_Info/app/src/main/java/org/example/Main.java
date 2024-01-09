package org.example;

public class Main {
    public static void main(String[] args) {
        // Integration of functionalities
        Student student = new Student(1, "John Doe", 20, "A");
        StudentRepository repository = new StudentRepository();
        repository.addStudent(student);

        System.out.println(student.getName());

        // Retrieve a student by ID
        Student retrievedById = repository.getStudentById(1);  // Use the instance method on the repository

        // Retrieve a student by name
        Student retrievedByName = repository.getStudentByName("John Doe");  // Use the instance method on the repository
    }
}
