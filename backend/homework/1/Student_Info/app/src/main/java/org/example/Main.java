package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Integration of functionalities
        Scanner sc = new Scanner(System.in);
        Student student = new Student();
        int id = sc.nextInt();
        String name = sc.nextLine();
        int age = sc.nextInt();
        String grade = sc.nextLine();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setGrade(grade);
        StudentRepository repository = new StudentRepository();

        repository.addStudent(student);

        System.out.println(student.getName());

        // Retrieve a student by ID
        Student retrievedById = repository.getStudentById(1);  // Use the instance method on the repository

        // Retrieve a student by name
        Student retrievedByName = repository.getStudentByName("John Doe");  // Use the instance method on the repository
    }
}
