package SafiaAmitaJBusBR;

public class Rating
{
    private long count;
    private long total;

    public Rating()
    {
        this.count=0;
        this.total=0;
    }
    
    private void insert (int rating)
    {
        this.total += rating;
        this.count++;
    }
    
    private double getAverage ()
    {
        if (this.count == 0){
            return 0;
        }
        
        return (double) this.total / this.count;
    }
    
    private long getCount()
    {
        return this.count;
    }
    
    private long getTotal()
    {
        return this.total;
    }
}
