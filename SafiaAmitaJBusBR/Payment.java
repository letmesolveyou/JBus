package SafiaAmitaJBusBR;

public class Payment extends Invoice
{
    private int busId;
    public String departureDate;
    public String busSeat;

    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat)
    {
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment (int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat)
    {
        super(id, buyer.id, renter.id, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String toString()
    {
        return "Payment Id : " + super.id + "\nBuyer Id : " + buyerId + "\nRenter Id : " + renterId + "\nTime : " + time + "\nBus Id : " + busId + "\nDeparture Date : " + departureDate + "\nBus Seat : " + busSeat;
    }
    
    public int getBusId()
    {
        return this.busId;
    }
}
