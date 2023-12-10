/**
 * The `Account` class represents a user account with associated information such as email,
 * name, password, company details, and balance. It extends the `Serializable` class for
 * serialization purposes.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable {

    /**
     * The email address associated with the account.
     */
    public String email;

    /**
     * The name associated with the account.
     */
    public String name;

    /**
     * The password associated with the account.
     */
    public String password;

    /**
     * The company details associated with the account (Renter type).
     */
    public Renter company;

    /**
     * The balance associated with the account.
     */
    public double balance;

    /**
     * Regular expression pattern for validating email addresses.
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";

    /**
     * Regular expression pattern for validating passwords. It requires at least 8 characters,
     * one uppercase letter, one lowercase letter, and one digit.
     */
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    /**
     * Constructs an `Account` object with the specified name, email, and password.
     *
     * @param name     The name associated with the account.
     * @param email    The email address associated with the account.
     * @param password The password associated with the account.
     */
    public Account(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
    }

    /**
     * Returns a string representation of the `Account` object.
     *
     * @return A string containing the account details.
     */
    public String toString() {
        return "id:" + super.id + "\nName: " + name + "\nEmail : " + email + "\nPassword : " + password;
    }

    /**
     * Validates the email and password format based on predefined regular expressions.
     *
     * @return `true` if both email and password are valid, otherwise `false`.
     */
    public boolean validate() {
        return email.matches(REGEX_EMAIL) && password.matches(REGEX_PASSWORD);
    }

    /**
     * Placeholder method for writing account information (serialization).
     *
     * @return Always returns `null`.
     */
    public Object write() {
        return null;
    }

    /**
     * Placeholder method for reading account information (deserialization).
     *
     * @param string The serialized string containing account information.
     * @return `true` if reading is successful, otherwise `false`.
     */
    public boolean read(String string) {
        return false;
    }

    /**
     * Adds the specified amount to the account balance.
     *
     * @param amount The amount to be added to the balance.
     * @return `true` if the amount is positive, and the balance is updated, otherwise `false`.
     */
    public boolean topUp(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }
}