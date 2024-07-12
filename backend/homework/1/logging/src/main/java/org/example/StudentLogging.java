package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentLogging {
    private static final Logger logger = LoggerFactory.getLogger(StudentLogging.class);

    public void logStudentAddition(Student student) {
        logger.info("Student added: {}", student.getName());
    }

    public void logRetrievingStudentById(int id) {
        logger.info("Retrieving student by ID: {}", id);
    }

    public void logRetrievingStudentByName(String name) {
        logger.info("Retrieving student by name: {}", name);
    }
}
