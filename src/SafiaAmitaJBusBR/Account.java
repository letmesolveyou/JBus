package SafiaAmitaJBusBR;

public class Account extends Serializable implements FileParser
{
    public String email;
    public String name;
    public String password;
    
    public Object write(){
        return null;
    }
    
    public boolean read(String file){
        return true;
    }
    
    public Account (int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString()
    {
        return  "Account Id : " + super.id +  "\nName: " + name + "\nEmail : " + email +  "\nPassword : " + password;
    }

}
