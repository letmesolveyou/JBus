package SafiaAmitaJBusBR;

public class JBus
{
    public int getBusId()
    {
        return 0;
    }
    
    public String getBusName()
    {
        return "Bus";
    }
    
    public boolean isDiscount()
    {
        return true;
    }  
    
    public float getDiscountPercentage (int beforeDiscount, int afterDiscount)
    {
        if (beforeDiscount > afterDiscount) {
            float discount = ((beforeDiscount - afterDiscount) * 100.0f) / beforeDiscount;
            return discount;
        } else {
            return 0.0f;
        }
    }

    public int getDiscountedPrice (int price, float discountPercentage)
    {
        if (discountPercentage > 100.0f) {
            discountPercentage = 100.0f;
        }
        
        float discountAmount = (discountPercentage / 100.0f) * price;
        return (int)(price - discountAmount);
    }
    
    public int getOriginalPrice (int discountedPrice, float discountPercentage)
    {
        float originalPrice = discountedPrice / (100.0f - discountPercentage);
        return (int)(originalPrice);
    }
    
    
    
    public static void main(String[] args) {
        JBus jbus = new JBus();

        int beforeDiscount = 1000;
        int afterDiscount = 900;
        
        float discountPercentage = jbus.getDiscountPercentage(beforeDiscount, afterDiscount);
        System.out.println("Discount Percentage: " + discountPercentage + "%");
    
        int originalPrice = jbus.getOriginalPrice(afterDiscount, discountPercentage);
        System.out.println("Original Price: " + originalPrice);
    }
    
    
}
    
