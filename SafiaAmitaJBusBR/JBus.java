package SafiaAmitaJBusBR;

import java.util.Calendar;

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
    
    public static void main(String[] args) {
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
      */
     
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
}

public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
}


    
