package SafiaAmitaJBusBR;
import java.util.*;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{3,19}$";

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

    public boolean validate() {
        return companyName.matches(REGEX_NAME) && Integer.toString(phoneNumber).matches(REGEX_PHONE);
    }
}
