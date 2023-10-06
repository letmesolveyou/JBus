package SafiaAmitaJBusBR;
import java.util.ArrayList;

public class Validate
{
    public Validate()
    {
    
    }

    public static ArrayList filter(Price[] list, int value, boolean less)
    {
        ArrayList<Double> harga = new ArrayList<Double>();
        
        for(int i = 0; i < list.length; i++)
        {
            if(less == true)
            {
                if(list[i].price <= value)
                {
                    harga.add(list[i].price);
                }
            }
            
            else{
                if(list[i].price > value)
                {
                    harga.add(list[i].price);
                }
            }
        }
        
        return harga;
    }
}
