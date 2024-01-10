package org.calculategrade;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentUtil {
    private static final Logger logger = LoggerFactory.getLogger(StudentUtil.class);
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        if (studentIdList == null || studentsGrades == null || studentIdList.length != studentsGrades.length) {
            // Parameter validation
            return new double[0];
        }

        double[] result = new double[studentIdList.length];

        for (int i = 0; i < studentIdList.length; i++) {
            int totalPoints = 0;
            int totalCourses = studentsGrades[i].length;

            for (char grade : studentsGrades[i]) {
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
                        logger.error("Enter the valid grade");
                        break;
                }
            }
            result[i] = totalPoints / (double) totalCourses;
        }

        return result;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher || studentIdList == null || studentsGrades == null || studentIdList.length != studentsGrades.length) {
            // Parameter validation
            return new int[0];
        }

        double[] gpas = calculateGPA(studentIdList, studentsGrades);

        if (gpas == null) {
            return null;
        }

        int count = 0;
        for (double gpa : gpas) {
            if (gpa >= lower && gpa <= higher) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;

        for (int i = 0; i < studentIdList.length; i++) {
            if (gpas[i] >= lower && gpas[i] <= higher) {
                result[index++] = studentIdList[i];
            }
        }
        return result;
    }

    // Main method
    public static void main(String[] args) {
        // Sample Input
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};

        // calculating the GPA of the students
        double[] gpas = calculateGPA(studentIdList, studentsGrades);
        System.out.println(Arrays.toString(gpas));
        logger.info("GPAs: {}", Arrays.toString(gpas));

        // filtering the students by GPA
        int[] filteredStudents = getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrades);
        System.out.println(Arrays.toString(filteredStudents));
        logger.info("Filtered Students: {}", Arrays.toString(filteredStudents));
    }
}
