package org.question3;
import java.util.*;
import org.slf4j.LoggerFactory;

public class Main {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        ArrayList<Integer> numbers = new ArrayList<>(List.of(5, 10, 30, 80));
        logger.debug("ArrayList {} :", numbers);
        // Here we can also use ExecutorService to crate thread pools.

        for (int number : numbers) {
            Thread factorialThread = new Thread(() -> {
                long factorialResult = calculateFactorial(number);
                logger.info("Factorial of {} are: {}", number, factorialResult);

            });

            Thread factorsThread = new Thread(() -> {
                String factorsResult = calculateFactors(number);
                logger.info("Factors of {} are: {}", number, factorsResult);
            });

            factorialThread.start();
            factorsThread.start();

            try {
                factorialThread.join();
                factorsThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        // Main thread finishes last
        logger.info("Main thread finished.");
    }

    private static long calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }

    private static String calculateFactors(int n) {
        StringBuilder factors = new StringBuilder();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                result.add(i);
                if(i != (n / i)){
                    result.add(n / i);
                }
            }
        }
        Collections.sort(result);
        for(int i = 0; i < result.size(); i++){
            factors.append(result.get(i)).append(" ");
        }
        return factors.toString();
    }
}
