package SafiaAmitaJBusBR;
import java.util.Calendar;

public class Invoice extends Serializable
{
    public Calendar time;
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

    protected Invoice(int id, int buyerId, int renterId)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = Calendar.getInstance();
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public Invoice(int id, Account buyer, Renter renter)
    {
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = Calendar.getInstance();
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString()
    {
        return  "Invoice Id : " + super.id +  "\nBuyer Id : " + buyerId + "\nRenter Id : " + renterId +  "\nTime : " + time + "\nRating : " + rating + "\nStatus : " + status;
    }
}
