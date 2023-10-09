package SafiaAmitaJBusBR;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        intializedSeatAvailability(numberOfSeats);
    }

    private void intializedSeatAvailability(int numberOfSeats)
    {
        seatAvailability = new LinkedHashMap<>();
        for (int seatNumber = 1; seatNumber<=numberOfSeats; seatNumber++)
        {
            String sn = seatNumber < 10? "0"+seatNumber : ""+seatNumber;
            String keySeat = "BR" + sn;
            seatAvailability.put(keySeat, true);
        }
    }
    
    public boolean isSeatAvailable (String seat)
    {
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }
    
    public boolean bookSeat(String seat) 
    {
        Boolean availability = seatAvailability.get(seat);
        if (availability != null && availability) {
            seatAvailability.put(seat, false);
            return true;
        }
        return false;
    }
    
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");

        int maxSeatsPerRow = 4; // Anggap ada 4 kursi per baris
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");

            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat)) {
                return false;
            }
        }
        return true;
    }

    public boolean bookSeat(List<String> seats) {
        boolean allSeatsBooked = true;
        for (String seat : seats) {
            if (!bookSeat(seat)) {
                allSeatsBooked = false;
            }
        }
        return allSeatsBooked;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        int totalSeats = seatAvailability.size();
        int seatsFilled = (int) seatAvailability.values().stream().filter(b -> !b).count();

        return "Schedule: " + formattedDepartureSchedule +
                "\nOccupied: " + seatsFilled + "/"+ totalSeats;
    }
}
