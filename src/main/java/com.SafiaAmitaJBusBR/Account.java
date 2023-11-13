package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Account extends Serializable {
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";


    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
    }

    public String toString() {
        return "id:" + super.id + "\nName: " + name + "\nEmail : " + email + "\nPassword : " + password;
    }

    public boolean validate() {
        return email.matches(REGEX_EMAIL) && password.matches(REGEX_PASSWORD);
    }


    public Object write() {
        return null;
    }

    public boolean read(String string) {
        return false;
    }

    public boolean topUp(double amount) {
        if (amount > 0) {
            return true;
        }
        return false;
    }
}