/**
 * The `Bus` class represents a bus entity with associated information such as capacity, facilities,
 * name, price, departure and arrival stations, bus type, schedules, and the associated account ID.
 * It extends the `Serializable` class for serialization purposes.
 *
 * @author Safia Amita
 * * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import com.SafiaAmitaJBusBR.dbjson.Serializable;
public class Bus extends Serializable {

    /**
     * The capacity of the bus.
     */
    public int capacity;

    /**
     * The list of facilities available on the bus.
     */
    public List<Facility> facilities;

    /**
     * The name of the bus.
     */
    public String name;

    /**
     * The price information associated with the bus.
     */
    public Price price;

    /**
     * The departure station of the bus.
     */
    public Station departure;

    /**
     * The arrival station of the bus.
     */
    public Station arrival;

    /**
     * The type of the bus (e.g., regular, luxury).
     */
    public BusType busType;

    /**
     * The list of schedules associated with the bus.
     */
    public List<Schedule> schedules;

    /**
     * The account ID associated with the bus.
     */
    public int accountId;

    /**
     * Constructs a `Bus` object with the specified parameters.
     *
     * @param name      The name of the bus.
     * @param facilities The list of facilities available on the bus.
     * @param price     The price information associated with the bus.
     * @param capacity  The capacity of the bus.
     * @param busType   The type of the bus (e.g., regular, luxury).
     * @param departure The departure station of the bus.
     * @param arrival   The arrival station of the bus.
     */
    public Bus(String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
    }

    /**
     * Adds a schedule to the list of schedules for the bus.
     *
     * @param calendar The timestamp representing the schedule.
     */
    public void addSchedule(Timestamp calendar) {
        if (schedules.size() < capacity) {
            try {
                this.schedules.add(new Schedule(calendar, this.capacity));
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }

    /**
     * Returns a string representation of the `Bus` object.
     *
     * @return A string containing the details of the bus.
     */
    @Override
    public String toString() {
        return "\nName : " + name +
                "\nFacility : " + facilities +
                "\nPrice : " + price +
                "\nCapacity : " + capacity +
                "\nBus Type : " + busType +
                "\nDeparture : " + departure +
                "\nArrival : " + arrival;
    }
}