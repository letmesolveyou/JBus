/**
 * The `Payment` class represents a payment made for bus reservation. It extends the `Invoice` class and includes
 * additional attributes such as bus ID, departure date, and a list of booked bus seats. The class provides methods
 * for making seat bookings and checking the availability of seats for a specific schedule.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import java.sql.Timestamp;
import java.util.List;

public class Payment extends Invoice {

    private int busId;
    public Timestamp departureDate;
    public List<String> busSeats;

    /**
     * Constructs a `Payment` object with the specified buyer ID, renter ID, bus ID, list of bus seats, and
     * departure date.
     *
     * @param buyerId       The buyer's ID.
     * @param renterId      The renter's ID.
     * @param busId         The ID of the bus.
     * @param busSeats      The list of booked bus seats.
     * @param departureDate The departure date of the bus.
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }

    /**
     * Constructs a `Payment` object with the specified buyer, renter, bus ID, list of bus seats, and departure date.
     *
     * @param buyer         The buyer's account.
     * @param renter        The renter's account.
     * @param busId         The ID of the bus.
     * @param busSeats      The list of booked bus seats.
     * @param departureDate The departure date of the bus.
     */
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyer.id, renter.id);
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }

    /**
     * Retrieves the ID of the bus associated with this payment.
     *
     * @return The bus ID.
     */
    public int getBusId() {
        return this.busId;
    }

    /**
     * Finds an available schedule for a given timestamp and seat in a specific bus.
     *
     * @param schedule The departure schedule timestamp.
     * @param seat     The seat to check for availability.
     * @param bus      The bus to check for the available schedule.
     * @return The available schedule, or null if not found.
     */
    public static Schedule availableSchedule(Timestamp schedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(schedule) && s.isSeatAvailable(seat))
                return s;
        }
        return null;
    }

    /**
     * Makes a booking for a specific departure schedule and seat in a given bus.
     *
     * @param departureSchedule The departure schedule timestamp.
     * @param seat              The seat to book.
     * @param bus               The bus for which the booking is made.
     * @return True if the booking is successful, false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(departureSchedule)) {
                if (s.isSeatAvailable(seat)) {
                    s.bookSeat(seat);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Finds an available schedule for a given timestamp and list of seats in a specific bus.
     *
     * @param schedule The departure schedule timestamp.
     * @param seats    The list of seats to check for availability.
     * @param bus      The bus to check for the available schedule.
     * @return The available schedule, or null if not found.
     */
    public static Schedule availableSchedule(Timestamp schedule, List<String> seats, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(schedule) && s.isSeatAvailable(seats))
                return s;
        }
        return null;
    }

    /**
     * Makes a booking for a specific departure schedule and list of seats in a given bus.
     *
     * @param departureSchedule The departure schedule timestamp.
     * @param seats             The list of seats to book.
     * @param bus               The bus for which the booking is made.
     * @return True if all seats are successfully booked, false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        boolean allSeatsBooked = true;
        for (String seat : seats) {
            if (!makeBooking(departureSchedule, seat, bus)) {
                allSeatsBooked = false;
                break;
            }
        }
        return allSeatsBooked;
    }
}
