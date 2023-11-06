package SafiaAmitaJBusBR;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public String phoneNumber;
    private final static String REGEX_PHONE = "^[0-9]{9,12}$";
    private final static String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";

    public Renter(String companyName)
    {
        this.companyName = companyName;
    }
    
    public Renter(String companyName, String phoneNumber)
    {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(String companyName, String phoneNumber, String address)
    {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate() {
        Pattern patternName = Pattern.compile(REGEX_NAME);
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherName = patternName.matcher(companyName);
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        return matcherName.find() && matcherPhone.find();
    }
}
