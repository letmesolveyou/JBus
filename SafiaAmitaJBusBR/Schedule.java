package SafiaAmitaJBusBR;
import java.util.Calendar;
import java.util.LinkedHashMap;

public class Schedule
{
    public Calendar departureSchedule;
    public LinkedHashMap<String, Boolean> seatAvailability;
    
    public Schedule(Calendar departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        intializeSeatAvailability(numberOfSeats);
    }

    private void intializeSeatAvailability(int numberOfSeats)
    {
        seatAvailability = new LinkedHashMap<>();
        for (int seatNumber = 1; seatNumber<=numberOfSeats; seatNumber++)
        {
            String keySeat = "BR" + seatNumber;
            seatAvailability.put(keySeat, true);
        }
    }
}
