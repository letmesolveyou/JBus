package SafiaAmitaJBusBR;
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

    public static boolean availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(departureSchedule)) {
                if (s.isSeatAvailable(seat)) {
                    return true;
                }
            }
        }
        return false;
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

    public static List<Schedule> availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        List<Schedule> availableSchedules = new ArrayList<>();
        for (Schedule s : bus.schedules) {
            if (s.departureSchedule.equals(departureSchedule)) {
                boolean allSeatsAvailable = true;
                for (String seat : seats) {
                    if (!s.isSeatAvailable(seat)) {
                        allSeatsAvailable = false;
                        break;
                    }
                }
                if (allSeatsAvailable) {
                    availableSchedules.add(s);
                }
            }
        }
        return availableSchedules;
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
