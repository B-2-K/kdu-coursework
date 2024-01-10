package org.apiparser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIResponseParser {
    private static final Logger logger = LoggerFactory.getLogger(APIResponseParser.class);
    public static Book parse(String response) {
        Book book = new Book();

        // Parse title
        String endRule = "<";
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);

        // Parse author
        startRule = "<name>";
        String author = parse(response, startRule, endRule);
        book.setAuthor(author);

        // Parse publication year
        startRule = "<original_publication_year type=\"integer\">";
        int publicationYear = Integer.parseInt(parse(response, startRule, endRule));
        book.setPublicationYear(publicationYear);

        // Parse average rating
        startRule = "<average_rating>";
        double averageRating = Double.parseDouble(parse(response, startRule, endRule));
        book.setAverageRating(averageRating);

        // Parse ratings count
        startRule = "<ratings_count type=\"integer\">";
        String ratingsCountStr = parse(response, startRule, endRule).replace(",", "");
        int ratingsCount = Integer.parseInt(ratingsCountStr);
        book.setRatingsCount(ratingsCount);

        // Parse image URL
        startRule = "<image_url>";
        String imageUrl = parse(response, startRule, endRule);
        book.setImageUrl(imageUrl);

        return book;
    }

    private static String parse(String response, String startRule, String endRule) {
        int start = response.indexOf(startRule) + startRule.length();
        int end = response.indexOf(endRule, start);
        return response.substring(start, end);
    }

    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";

        Book parsedBook = APIResponseParser.parse(response);

        // Displaying parsed book information using System.out.println
        System.out.println("Title: " + parsedBook.getTitle());
        System.out.println("Author: " + parsedBook.getAuthor());
        System.out.println("Publication Year: " + parsedBook.getPublicationYear());
        System.out.println("Average Rating: " + parsedBook.getAverageRating());
        System.out.println("Ratings Count: " + parsedBook.getRatingsCount());
        System.out.println("Image URL: " + parsedBook.getImageUrl());
    }
}
