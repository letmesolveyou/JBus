package SafiaAmitaJBusBR;

public class Bus
{
    int capacity;
    Facility facility;
    String name;
    Price price;
    
    public Bus(String name, Facility facility, Price price, int capacity)
    {
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
    }
}
