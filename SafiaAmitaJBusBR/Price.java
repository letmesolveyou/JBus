package SafiaAmitaJBusBR;

public class Price
{
    public double rebate;
    public double price;
    public int discount; 

    public Price(double price)
    {
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }
    
    public Price(double price, int discount)
    {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate)
    {
        this.price = price;
        this.rebate = rebate;
        this.discount = 0;
    }
    
    private double getDiscountedPrice()
    {
        
        if (this.discount > 100){
            this.discount = 100;
        }
        
        if (this.discount == 100){
            return 0;
        }
        
        return (double) this.price - (this.discount * this.price);
    }
    
    private double getRebatedPrice()
    {
        
        if (this.rebate > this.price)
        {
            return 0;
        }
        
        return (this.price - this.rebate);
    }
    
    
}
