package org.question2;
import java.util.*;

public class TicketReservation {
    // final as this no is fixed and cannot be changed later
    private static final int CONFIRMEDLIST_LIMIT = 100;
    private static final int WAITINGLIST_LIMIT = 20;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);

        if (confirmedList.size() < CONFIRMEDLIST_LIMIT) {
            confirmedList.add(passenger);
            return true;
        } else if (waitingList.size() < WAITINGLIST_LIMIT) {
            waitingList.offer(passenger);
            return true;
        } else {
            // Both confirmed and waiting lists are full
            return false;
        }
    }

    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> confirmedIterator = confirmedList.iterator();
        if (removePassenger(confirmedIterator, confirmationNumber)) {
            // If the passenger was in the confirmedList, move the next passenger from waitingList
            if (!waitingList.isEmpty()) {
                confirmedList.add(waitingList.poll());
            }
            return true;
        }

        // If the passenger was not in the confirmedList, check the waitingList
        Iterator<Passenger> waitingIterator = waitingList.iterator();
        if(removePassenger(waitingIterator, confirmationNumber)){
            return removePassenger(waitingIterator, confirmationNumber);
        }

        return false;
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.getConfirmationNumber().equals(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
