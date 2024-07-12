package org.question4;
import org.slf4j.LoggerFactory;
import java.util.*;

class Main {
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Set<Book> books;
        if (comparator == null) {
            books = new TreeSet<>();
        } else {
            books = new TreeSet<>(comparator);
        }

        // Adding books
        Book book1 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("The Last Lecture", "Randy Pausch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        return books;
    }

    public static void main(String[] args) {
        // Test case 1
        Set<Book> set1 = treeSetDemo(null);
        logger.info("Test Case 1:");
        for (Book book : set1) {
            logger.info("Title: {}, Author: {}, Year: {}", book.getTitle(), book.getAuthor(), book.getYear());
        }

        // Test case 2
        Set<Book> set2 = treeSetDemo(new PubDateAscComparator());
        logger.info("\nTest Case 2:");
        for (Book book : set2) {
            logger.info("Title: {}, Author: {}, Year: {}", book.getTitle(), book.getAuthor(), book.getYear());
        }

        // Test case 3
        Set<Book> set3 = treeSetDemo(new PubDateDescComparator());
        logger.info("\nTest Case 3:");
        for (Book book : set3) {
            logger.info("Title: {}, Author: {}, Year: {}", book.getTitle(), book.getAuthor(), book.getYear());
        }
    }
}

