package org.question1;
import org.customlogger.Logg;
import java.util.*;

public class GPACalculator {
    public static Logg logger = new Logg();
    double[] result = new double[0];
    public void calculateGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException {
        // Task 1: Parameter Validation
        if (studentIdList.length != studentsGrades.length) {
            throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. " +
                    "studentIdList.length: " + studentIdList.length +
                    ", studentsGrades.length: " + studentsGrades.length);
        }

        int noOfStudentIds = studentIdList.length;
        result = new double[noOfStudentIds];

        for (int i = 0; i < noOfStudentIds; i++) {
            int totalPoints = 0;
            int studentId = studentIdList[i];
            char[] grades = studentsGrades[i];

            for (char grade : grades) {
                // Task 2: Check for Missing Grades
                if (grade == ' ') {
                    throw new MissingGradeException(studentId);
                }
                else {
                    // calculating score
                    switch (grade) {
                        case 'A':
                            totalPoints += 4;
                            break;
                        case 'B':
                            totalPoints += 3;
                            break;
                        case 'C':
                            totalPoints += 2;
                            break;
                        default:
                            logger.error("Enter the valid grade!!!");
                            break;
                    }
                }
            }
            int totalCourses = studentsGrades[i].length;
            if(totalCourses > 0) {
                result[i] = totalPoints / (double) totalCourses;logger.info("Student with ID: " + studentId + " has GPA: " + result[i]);
            }
        }
    }

    public void getStudentsByGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException {
        try {
            // Invoke calculateGPA
            calculateGPA(studentIdList, studentsGrades);
            Arrays.sort(result);
            logger.info("result is sorted in ascending order : ");
            for (int i = 0; i < result.length; i++) {
                logger.info("Student with ID: " + studentIdList[i] + " has GPA: " + result[i]);
            }
        } catch (MissingGradeException e) {
            // Task 3: Exception Chaining
            throw new InvalidDataException("Invalid data while processing GPA for student with ID: " + e.getStudentId(), e);
        }
    }
}