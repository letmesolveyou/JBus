package SafiaAmitaJBusBR;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat,  Timestamp departureDate)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }
    
    public Payment (int id, Account buyer, Renter renter, int busId, String busSeat,  Timestamp departureDate)
    {
        super(id, buyer.id, renter.id);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }
    
    public String getDepartureInfo()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String date = dateFormat.format(this.departureDate.getTime());
        return 
        "Payment Id : " + super.id + 
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
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
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
}
