/**
 * The `Schedule` class represents the schedule of a bus, including the departure timestamp
 * and seat availability information.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */

package com.SafiaAmitaJBusBR;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Schedule {

    /** The timestamp of the departure schedule. */
    public Timestamp departureSchedule;

    /** The seat availability map, where key is seat number and value is availability status. */
    public Map<String, Boolean> seatAvailability;

    /**
     * Constructs a `Schedule` object with a specified departure schedule and number of seats.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param numberOfSeats The number of seats in the bus.
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Initializes the seat availability map based on the number of seats.
     *
     * @param numberOfSeats The number of seats in the bus.
     */
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String seatCode = (seatNumber < 10) ? "BR0" + seatNumber : "BR" + seatNumber;
            seatAvailability.put(seatCode, true);
        }
    }

    /**
     * Checks if a specific seat is available.
     *
     * @param seat The seat number to check.
     * @return True if the seat is available, false otherwise.
     */
    public boolean isSeatAvailable(String seat) {
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }

    /**
     * Books a specific seat if it is available.
     *
     * @param seat The seat number to book.
     * @return True if the seat is successfully booked, false otherwise.
     */
    public boolean bookSeat(String seat) {
        Boolean availability = seatAvailability.get(seat);
        if (availability != null && availability) {
            seatAvailability.put(seat, false);
            return true;
        }
        return false;
    }

    /**
     * Prints the schedule with seat availability information.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        System.out.println("Departure Date: " + formattedDepartureSchedule);
        System.out.println("Seat Availability:");

        int maxSeatsPerRow = 4; // Assuming 4 seats per row
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");

            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    /**
     * Checks if a list of seats is available.
     *
     * @param seats The list of seat numbers to check.
     * @return True if all seats are available, false otherwise.
     */
    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Books a list of seats if all seats are available.
     *
     * @param seats The list of seat numbers to book.
     * @return True if all seats are successfully booked, false otherwise.
     */
    public boolean bookSeat(List<String> seats) {
        boolean allSeatsBooked = true;
        for (String seat : seats) {
            if (!bookSeat(seat)) {
                allSeatsBooked = false;
            }
        }
        return allSeatsBooked;
    }

    /**
     * Returns a string representation of the `Schedule` object.
     *
     * @return A string containing the departure schedule and seat availability.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        int totalSeats = seatAvailability.size();
        int seatsFilled = (int) seatAvailability.values().stream().filter(b -> !b).count();

        return "Schedule: " + formattedDepartureSchedule +
                "\nOccupied: " + seatsFilled + "/" + totalSeats;
    }
}
