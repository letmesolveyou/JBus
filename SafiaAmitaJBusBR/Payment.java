package SafiaAmitaJBusBR;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        
        Calendar twoDays = Calendar.getInstance();
        twoDays.add(Calendar.DAY_OF_MONTH, 2);
        this.departureDate = twoDays;
    }
    
    public Payment (int id, Account buyer, Renter renter, int busId, String busSeat)
    {
        super(id, buyer.id, renter.id);
        this.busId = busId;
        this.busSeat = busSeat;
        
        Calendar twoDays = Calendar.getInstance();
        twoDays.add(Calendar.DAY_OF_MONTH, 2);
        this.departureDate = twoDays;
    }
    
    public String getDepartureInfo()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String date = dateFormat.format(this.departureDate);
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
        String date = dateFormat.format(super.time);
        return "Date : " +date;
    }
    
    public int getBusId()
    {
        return this.busId;
    }
}
