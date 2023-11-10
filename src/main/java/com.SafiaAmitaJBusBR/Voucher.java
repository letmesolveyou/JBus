package com.SafiaAmitaJBusBR;
import com.SafiaAmitaJBusBR.dbjson.Serializable;
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher (String name, int code, Type type, double minimum, double cut)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    public double apply(Price price)
    {
        this.used = true; 
        
        if (type == Type.DISCOUNT){
            if (cut > 100){
                cut = 100;
            }

            if (cut == 100){
                return 0;
            }
            
            return (double) price.price -  price.price * cut / 100.0;
        }
        
        if (type == Type.REBATE){
            if (price.price < cut){
                return 0;
            }
            
            return (double) price.price - cut;
        }
        
        return price.price;
        
    }

    public boolean isUsed()
    {
        return this.used;
    }
    
    public boolean canApply(Price price)
    {
        if (used == false && price.price > minimum) {
            return true;
        }
        
        return false;
    }
}
