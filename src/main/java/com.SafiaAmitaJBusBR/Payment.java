package com.SafiaAmitaJBusBR;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;

    public Payment(int buyerId, int renterId, int busId, String busSeat,  Timestamp departureDate)
    {
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }
    
    public Payment (Account buyer, Renter renter, int busId, String busSeat,  Timestamp departureDate)
    {
        super(buyer.id, renter.id);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }
    
    public String getDepartureInfo()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String date = dateFormat.format(this.departureDate.getTime());
        return
        "\nBuyer Id : " + buyerId + 
        "\nRenter Id : " + renterId + 
        "\nBus Id : " + busId + 
        "\nBus Seat : " + busSeat + 
        "\nDeparture Date : " + date;
    }
    
    public String getTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String date = dateFormat.format(this.time.getTime());
        return "Date : " +date;
    }
    
    public int getBusId()
    {
        return this.busId;
    }

    public static Schedule availableSchedule(Timestamp schedule, String seat, Bus bus){
        for (Schedule s: bus.schedules) {
            if (s.departureSchedule.equals(schedule) && s.isSeatAvailable(seat))
                return s;
        }
        return null;
    }
    
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

    public static Schedule availableSchedule(Timestamp schedule, List<String> seats, Bus bus){
        for (Schedule s: bus.schedules) {
            if (s.departureSchedule.equals(schedule) && s.isSeatAvailable(seats))
                return s;
        }
        return null;
    }

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
