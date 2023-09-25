package SafiaAmitaJBusBR;

public class Invoice extends Serializable
{
    public String time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    public enum BusRating
    {
        NONE, NEUTRAL, GOOD, BAD;
    }
    
    public enum PaymentStatus
    {
        FAILED, WAITING, SUCCESS;
    }

    protected Invoice(int id, int buyerId, int renterId, String time)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public Invoice(int id, Account buyer, Renter renter, String time)
    {
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString()
    {
        return  "Invoice Id : " + super.id +  "\nBuyer Id : " + buyerId + "\nRenter Id : " + renterId +  "\nTime : " + time + "\nRating : " + rating + "\nStatus : " + status;
    }
}
