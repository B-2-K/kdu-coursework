package org.question2;
import org.customlogger.Logg;

public class Main {
    public static Logg logger = new Logg();
    public static void main(String[] args) {
        TicketReservation ticketReservation = new TicketReservation();

        Passenger passenger1 = new Passenger("Bittu", "Kumar", 22, "Male", "economy", "C1");
        Passenger passenger2 = new Passenger("Aakanksha", "Kumari", 30, "Female", "business", "C2");

        // Booking flights
        boolean bookingResult1 = ticketReservation.bookFlight(passenger1.getFirstName(), passenger1.getLastName(), passenger1.getAge(), passenger1.getGender(), passenger1.getTravelClass(), passenger1.getConfirmationNumber());
        boolean bookingResult2 = ticketReservation.bookFlight(passenger2.getFirstName(), passenger2.getLastName(), passenger2.getAge(), passenger1.getGender(), passenger2.getTravelClass(), passenger2.getConfirmationNumber());

        // Display booking results
        logger.info("Booking Result 1: " + (bookingResult1 ? "Success" : "Failure"));
        logger.info("Booking Result 2: " + (bookingResult2 ? "Success" : "Failure"));

        // Canceling a flight
        boolean cancellationResult = ticketReservation.cancel("C1");

        // Display cancellation result
        logger.info("Cancellation Result: " + (cancellationResult ? "Success" : "Failure"));
    }
}
