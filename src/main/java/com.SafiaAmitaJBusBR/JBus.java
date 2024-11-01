/**
 * The `JBus` class serves as the main class for the JBus application. It contains various static methods
 * related to bus operations, discount calculations, and other utility functions. The class also includes the
 * main method to run the Spring Boot application. It uses the `JsonDBEngine` for data storage and retrieval.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */

package com.SafiaAmitaJBusBR;

import java.sql.Timestamp;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.SafiaAmitaJBusBR.dbjson.JsonDBEngine;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus
{
    public static int getBusId()
    {
        return 0;
    }
    
    public static String getBusName()
    {
        return "Bus";
    }
    
    public static boolean isDiscount()
    {
        return true;
    }  
    
    public static float getDiscountPercentage (int beforeDiscount, int afterDiscount)
    {
        if (beforeDiscount > afterDiscount) {
            float discount = ((beforeDiscount - afterDiscount) * 100.0f) / beforeDiscount;
            return discount;
        } else {
            return 0.0f;
        }
    }

    public static int getDiscountedPrice (int price, float discountPercentage)
    {
        if (discountPercentage > 100.0f) {
            discountPercentage = 100.0f;
        }
        
        float discountAmount = (discountPercentage / 100.0f) * price;
        return (int)(price - discountAmount);
    }
    
    public static int getOriginalPrice (int discountedPrice, float discountPercentage)
    {
        float originalPrice = (100.0f * discountedPrice) / (100.0f - discountPercentage);
        return (int)(originalPrice);
    }
    
    public static float getAdminFeePercentage()
    {
        return 0.05f;
    }
    
    public static int getAdminFee (int price)
    {
        return (int)(0.05f * price);
    }
    
    public static int getTotalPrice (int price, int numberOfSeat)
    {
        int adminFee = getAdminFee (price);
        float totalPrice = (price * numberOfSeat) + adminFee;
        return (int)(totalPrice);
    }
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JBus.class, args);
        JsonDBEngine.Run(JBus.class);
        Runtime.getRuntime().addShutdownHook(new Thread(()->JsonDBEngine.join()));

         /*
        JBus jbus = new JBus();


        int beforeDiscount = 1000;
        int afterDiscount = 900;
        int numberOfSeat = 3;

        float discountPercentage = jbus.getDiscountPercentage(beforeDiscount, afterDiscount);
        System.out.println("Discount Percentage: " + discountPercentage + "%");

        int discountedPrice = jbus.getDiscountedPrice (beforeDiscount, discountPercentage);
        System.out.println("Discounted Price: " + discountedPrice);

        int originalPrice = jbus.getOriginalPrice(afterDiscount, discountPercentage);
        System.out.println("Original Price: " + originalPrice);

        int adminFee = jbus.getAdminFee(beforeDiscount);
        System.out.println("Admin Fee: " + adminFee);

        int totalPrice = jbus.getTotalPrice(beforeDiscount, numberOfSeat);
        System.out.println("Total Price: " + totalPrice);


       Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);


        Price originalPrice = new Price(90.0);

        Voucher voucher = new Voucher("Voucher Mita", 2206, Type.DISCOUNT, 50.0, 15.0); //ganti tipe disini ya mit, rebate=76,5, disc=75

        System.out.println("Used gakkk " + voucher.isUsed()); // salah

        if (voucher.canApply(originalPrice)) {
            double Priceee = voucher.apply(originalPrice);
            System.out.println("Price: " + Priceee);
        }

        System.out.println("Used gakkk " + voucher.isUsed()); // benar


       Payment testPayment = new Payment (1, 1, 1, "A", 1, "A", "A");
       Invoice testInvoice = new Invoice (2, 2, 2, "B");
       Station testStation = new Station (3, "C", City.DEPOK);

       System.out.println(testPayment.print());
       System.out.println(testInvoice.print());
       System.out.println(testStation.print());


      Review testReview = new Review(1, "23 August 2023", "Bad Quality");
      Price testPrice = new Price(100000, 20000);
      Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "JL. Margonda Raya");
      Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
      Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
      Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
      Rating testRating = new Rating();
      System.out.println(testReview);
      System.out.println(testBus);
      System.out.println(testAccount);
      System.out.println(testPrice);
      System.out.println(testRating);


    Price[] unfilteredArray = new Price[5];
    for(int i = 0; i < unfilteredArray.length; i++) {
        int j = 5000;
        unfilteredArray[i] = new Price((i+1)*j);
    }

    System.out.println("Price List");
    for (Price price : unfilteredArray) {
        System.out.println(price.price);
    }

    System.out.println("Below 12000.0");
    System.out.println(Validate.filter(unfilteredArray, 12000, true));
    System.out.println("Above 10000.0");
    System.out.println(Validate.filter(unfilteredArray,10000, false));

    Bus testBus = createBus();
    // Payment
    Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
    System.out.println(testPayment.getDepartureInfo());
    System.out.println(testPayment.getTime());
    // Tes Schedule
    Calendar schedule1 = Calendar.getInstance();
    testBus.addSchedule(schedule1);
    Calendar schedule2 = Calendar.getInstance();
    schedule2.add(Calendar.DAY_OF_MONTH, 3);
    testBus.addSchedule(schedule2);
    for(Schedule s: testBus.schedules){
    testBus.printSchedule(s);
    }



    Bus b = createBus();
    Timestamp schedule1 = Timestamp.valueOf("2023-07-18 15:00:00");
    Timestamp schedule2 = Timestamp.valueOf("2023-07-20 12:00:00");

    // Menambahkan jadwal keberangkatan ke Bus
    b.addSchedule(schedule1, 12);
    b.addSchedule(schedule2, 12);

    // Menampilkan jadwal keberangkatan
    System.out.println("Schedules:");
    b.schedules.forEach(Schedule::printSchedule);

    // Membuat pemesanan kursi dengan tanggal dan kursi yang berbeda
    Timestamp t1 = Timestamp.valueOf("2023-07-19 15:00:00");
    System.out.println("\nMake booking at July 19, 2023 15:00:00 Seat BR12");
    System.out.println(Payment.makeBooking(t1, "BR12", b)); // Invalid date

    Timestamp t2 = Timestamp.valueOf("2023-07-18 15:00:00");
    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat BR20");
    System.out.println(Payment.makeBooking(t2, "BR20", b)); // Invalid seat

    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat BR07");
    System.out.println(Payment.makeBooking(t2, "BR07", b)); // Valid date and seat

    Timestamp t3 = Timestamp.valueOf("2023-07-20 12:00:00");
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat BR01");
    System.out.println(Payment.makeBooking(t3, "BR01", b)); // Valid date and seat

    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat BR01 again");
    System.out.println(Payment.makeBooking(t3, "BR01", b)); // Invalid seat (already booked)

    // Menampilkan jadwal keberangkatan setelah pemesanan
    System.out.println("\nUpdated Schedule\n");
    b.schedules.forEach(Schedule::printSchedule);

        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);

        // PT Modul 5
        // Tes Pagination
        Bus b = createBus();
        List<Timestamp> listOfSchedules = new ArrayList<>();
        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

        listOfSchedules.forEach(b::addSchedule);
        System.out.println("Page 1");
        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");
        System.out.println("Page 2");
        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // Tes Booking
        String msgSuccess = "Booking Success!";
        String msgFailed = "Booking Failed";
        // valid date, invalid seat = Booking Failed
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: BR17 BR18");
        System.out.println(Payment.makeBooking(t1, List.of("BR17", "BR18"), b)? msgSuccess : msgFailed);
        // valid date, invalid seat = Booking Failed
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat BR26");
        System.out.println(Payment.makeBooking(t2, "BR26", b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: BR7 BR8");
        System.out.println(Payment.makeBooking(t2, List.of("BR7", "BR8"), b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: BR1 BR2");
        System.out.println(Payment.makeBooking(t3, List.of("BR1", "BR2"), b)? msgSuccess : msgFailed);
        // valid date, book the same seat = Booking Failed
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat BR1");
        System.out.println(Payment.makeBooking(t3, "BR1", b)? msgSuccess : msgFailed);
        // check if the data changed
        System.out.println("\nUpdated Schedule");
        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);


               // TP Modul 6
                String filepath = "C:\\Users\\LENOVO\\Documents\\OOP\\JBus\\data\\station.json";
                Gson gson = new Gson();

                try {
                    BufferedReader buffer = new BufferedReader(new FileReader(filepath));
                    List<Station> stationJson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
                    stationJson.forEach(e -> System.out.println(e.toString()));
                    System.out.println();
                    buffer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }


        Renter trueRegex = new Renter ("Mita_280504",111111111);
        System.out.println(trueRegex.validate());

        Renter falseRegex = new Renter ("mitacantik", 11);
        System.out.println(falseRegex.validate());


        Gson gson = new Gson();
        try {
            String filepath = "C:\\Users\\LENOVO\\Documents\\OOP\\JBus\\data\\accountDatabase.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);

            Account account1 = new Account("Mita", "safiamita@ui.com", "Mita_280504");
            Account account2 = new Account("Hikam", "syahrillah@ugm.com", "Hikam130404");

            // Menambahkan objek Account ke JsonTable
            tableAccount.add(account1);
            tableAccount.add(account2);

            // Menyimpan JsonTable ke file "accountDatabase.json"
            tableAccount.writeJson();

            System.out.println("Data berikut disimpan ke accountDatabase.json");
            System.out.println(account1.toString());
            System.out.println(account2.toString());
        }
        catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menulis data ke file: " + e.getMessage());
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus,
                    Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bus.schedules.forEach(Schedule::printSchedule);
        */
    }

    /*
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
                Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }

     */

    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val)->val<=22;
        Predicate<Integer> above = (val)->val>43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
    }

    public static List<Bus> filterByDeparture (List<Bus> buses, City departure, int page, int pageSize) {
        List<Bus> filteredBuses = buses;
        List<Bus> list = new ArrayList<Bus>();
        for(Bus bus : filteredBuses)
        {
            if(bus.departure.city.equals(departure))
            {
                list.add(bus);
            }
        }
        return Algorithm.paginate(list, page, pageSize, t -> true);
    }

    public static List<Bus> filterByPrice (List<Bus> buses, int min, int max){
        List<Bus> filteredBuses = buses;
        List<Bus> list = new ArrayList<Bus>();
        for (Bus bus : buses) {
            if (bus.price.price > min && bus.price.price <= max) {
                filteredBuses.add(bus);
            }
        }
        return filteredBuses;
    }

    public static Bus filterBusId (List<Bus> buses, int id) {
        for (Bus bus : buses) {
            if (bus.id == id) {
                return bus;
            }
        }
        return null;
    }

    public static List<Bus> filterByDepartureandArrival (List<Bus> buses, City departure, City arrival, int page, int pageSize){
        List<Bus> filteredBuses = buses;
        List<Bus> list = new ArrayList<Bus>();
        for(Bus bus : filteredBuses)
        {
            if(bus.departure.city.equals(departure) & bus.arrival.city.equals(arrival))
            {
                list.add(bus);
            }
        }
        return Algorithm.paginate(list, page, pageSize, t -> true);
    }
}


    
