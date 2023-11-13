package com.SafiaAmitaJBusBR;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.sql.Timestamp;
import com.SafiaAmitaJBusBR.dbjson.Serializable;

public class Bus extends Serializable
{
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public List<Schedule> schedules;
    public int accountId;
    
    public Bus(int id, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival)
    {
        super();
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
    }

    public void addSchedule(Timestamp calendar)
    {
        if (schedules.size() < capacity) {
            try {
                this.schedules.add(new Schedule(calendar, this.capacity));
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
    
    public String toString()
    {
        return 
        "Bus Id : " + super.id + 
        "\nName : " + name + 
        "\nFacility : " + facilities +
        "\nPrice : " + price + 
        "\nCapacity : " + capacity + 
        "\nBus Type : " + busType +
        "\nDeparture : " + departure + 
        "\nArrival : " + arrival;
    }
}
