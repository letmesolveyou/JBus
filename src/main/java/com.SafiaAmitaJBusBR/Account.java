package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    private static final String REGEX_PASSWORD = "^( =.*[a-z])( =.*[A-Z])( =.*\\d)[a-zA-Z\\d]{8,}$";
    
    public Account (String name, String email, String password)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = null;
        this.balance = 0;
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

    public Object write(){
        return null;
    }
    public boolean read(String string){
        return false;
    }

    public boolean topUp(double amount)
    {
        if(amount > 0){
            return true;
        }
        return false;
    }
}
