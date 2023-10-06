package SafiaAmitaJBusBR;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.sql.Timestamp;

public class Bus extends Serializable implements FileParser
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;
    
    public Object write(){
        return null;
    }
    
    public boolean read(String file){
        return true;
    }
    
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
        this.schedules = new ArrayList<>();
    }
    
    public void addSchedule(Timestamp calendar, int capacity)
    {
        if (schedules.size() < capacity) {
            schedules.add(new Schedule(calendar, this.capacity));
        }
    }
    
    public String toString()
    {
        return 
        "Bus Id : " + super.id + 
        "\nName : " + name + 
        "\nFacility : " + facility + 
        "\nPrice : " + price + 
        "\nCapacity : " + capacity + 
        "\nBus Type : " + busType + 
        "\nCity : " + city + 
        "\nDeparture : " + departure + 
        "\nArrival : " + arrival;
    }
}
