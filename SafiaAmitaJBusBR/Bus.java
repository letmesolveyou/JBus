package SafiaAmitaJBusBR;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super(id);
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
    }
    
    public String toString()
    {
        return "Bus Id : " + super.id + "\nName : " + name + "\nFacility : " + facility + "\nPrice : " + price + "\nCapacity : " + capacity + "\nBus Type : " + busType + "\nCity : " + city + "\nDeparture : " + departure + "\nArrival : " + arrival;
    }
}
