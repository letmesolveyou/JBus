package SafiaAmitaJBusBR;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    
    public Account (String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString()
    {
        return  "Name: " + name + "\nEmail : " + email +  "\nPassword : " + password;
    }

}
