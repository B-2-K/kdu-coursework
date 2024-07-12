package org.question1;
import org.customlogger.Logg;

public class Main {
    public static Logg logger = new Logg();
    public static void main(String[] args) {
        GPACalculator calculator = new GPACalculator();

        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', 'A', 'A', 'B'}};

        try {
            calculator.calculateGPA(studentIdList, studentsGrades);
            calculator.getStudentsByGPA(studentIdList, studentsGrades);
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException: " + e.getMessage());
        } catch (MissingGradeException e) {
            logger.error("MissingGradeException: Student with ID " + e.getStudentId() + " has missing grades.");
        } catch (InvalidDataException e) {
            logger.error("InvalidDataException: " + e.getMessage());
            logger.error("Underlying cause: " + e.getCause().getMessage());
        }
    }
}


