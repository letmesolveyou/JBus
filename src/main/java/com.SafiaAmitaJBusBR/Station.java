/**
 * The `Station` class represents a bus station with information such as the station name, city, and address.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;

public class Station extends Serializable {

    /** The city where the station is located. */
    public City city;

    /** The name of the bus station. */
    public String stationName;

    /** The address of the bus station. */
    public String address;

    /**
     * Constructs a `Station` object with the specified station name, city, and address.
     *
     * @param stationName The name of the bus station.
     * @param city The city where the station is located.
     * @param address The address of the bus station.
     */
    public Station(String stationName, City city, String address) {
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    /**
     * Returns a string representation of the `Station` object.
     *
     * @return A string containing the station name, city, and address.
     */
    @Override
    public String toString() {
        return "Station Name: " + stationName + "\nCity: " + city + "\nAddress: " + address;
    }
}
