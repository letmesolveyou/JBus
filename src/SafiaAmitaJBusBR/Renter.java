package SafiaAmitaJBusBR;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";

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
        Pattern patternName = Pattern.compile(REGEX_NAME);
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherName = patternName.matcher(companyName);
        Matcher matcherPhone = patternPhone.matcher(Integer.toString(phoneNumber));
        return matcherName.find() && matcherPhone.find();
    }
}
