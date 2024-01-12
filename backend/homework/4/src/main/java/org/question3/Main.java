package org.question3;
import org.customlogger.Logg;
import java.util.*;

public class Main {
    public static Logg logger = new Logg();
    public static <T> void swapElements(List<T> list, int index1, int index2) {
        try {
            if (index1 < 0 || index1 >= list.size() || index2 < 0 || index2 >= list.size()) {
                throw new IllegalArgumentException("Invalid indices");
            }

            T temp = list.get(index1);
            list.set(index1, list.get(index2));
            list.set(index2, temp);
        } catch (Exception e) {
            logger.error("Error swapping elements: " + e.getMessage());
        }
    }

    public static <T> void printList(List<T> list, String message) {
        logger.info(message + list);
    }

    public static <T> void performSwapAndPrint(List<T> list, int index1, int index2) {
        logger.debug("index1 = " + index1 + ", index2 = " + index2);
        printList(list, "Before swapping: ");
        swapElements(list, index1, index2);
        printList(list, "After swapping: ");
    }

    public static void main(String[] args) {
        try {
            // Example usage with an ArrayList of integers
            List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            performSwapAndPrint(intList, 1, 3);

            // Example usage with an ArrayList of strings
            List<String> stringList = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
            performSwapAndPrint(stringList, 0, 2);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage());
        }
    }
}
