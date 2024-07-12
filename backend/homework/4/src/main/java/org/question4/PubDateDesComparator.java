package org.question4;

import java.util.Comparator;

class PubDateDescComparator implements Comparator<Book> {
    private TitleComparator titleComparator = new TitleComparator();

    @Override
    public int compare(Book book1, Book book2) {
        int yearCompare = Integer.compare(book2.getYear(), book1.getYear());
        if (yearCompare == 0) {
            return titleComparator.compare(book1, book2);
        }
        return yearCompare;
    }
}
