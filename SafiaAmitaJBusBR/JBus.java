package SafiaAmitaJBusBR;

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
    
    public static Bus createBus()
    {
        Price price = new Price (750000, 5);
        Bus bus = new Bus ("Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
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
        */
        
        Price originalPrice = new Price(90.0);

        Voucher voucher = new Voucher("Voucher Mita", 2206, Type.DISCOUNT, 50.0, 15.0); //ganti tipe disini ya mit, rebate=76,5, disc=75

        System.out.println("Used gakkk " + voucher.isUsed()); // salah

        if (voucher.canApply(originalPrice)) {
            double Priceee = voucher.apply(originalPrice);
            System.out.println("Price: " + Priceee);
        } 

        System.out.println("Used gakkk " + voucher.isUsed()); // benar
    }
}

    
