package SafiaAmitaJBusBR;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;

    public Renter(String companyName)
    {
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }
    
    public Renter(String companyName, String address)
    {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = 0;
    }
    
    public Renter(String companyName, int phoneNumber)
    {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(String companyName, int phoneNumber, String address)
    {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
