package SafiaAmitaJBusBR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    private static final String REGEX_EMAIL = "^[a-z0-9]+@([a-z.]+\\.)+com$";
    private static final String REGEX_PASSWORD = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[\\w]{8,}$";
    
    public Account (String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString()
    {
        return  "id:" +super.id+ "\nName: " + name + "\nEmail : " + email +  "\nPassword : " + password;
    }

    public boolean validate() {
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patternPass = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherEmail = patternEmail.matcher(email);
        Matcher matcherPass = patternPass.matcher(password);
        return matcherEmail.find() && matcherPass.find();
    }
}
