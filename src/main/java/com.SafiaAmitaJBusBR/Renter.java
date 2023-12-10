/**
 * The `Renter` class represents information about a renter, including the company name, address, and phone number.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable {

    /** The address of the renter. */
    public String address;

    /** The name of the company. */
    public String companyName;

    /** The phone number of the renter. */
    public String phoneNumber;

    /** The regular expression pattern for validating phone numbers. */
    private final static String REGEX_PHONE = "^[0-9]{9,12}$";

    /** The regular expression pattern for validating company names. */
    private final static String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";

    /**
     * Constructs a `Renter` object with a specified company name.
     *
     * @param companyName The name of the company.
     */
    public Renter(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Constructs a `Renter` object with a specified company name and phone number.
     *
     * @param companyName The name of the company.
     * @param phoneNumber The phone number of the renter.
     */
    public Renter(String companyName, String phoneNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a `Renter` object with a specified company name, phone number, and address.
     *
     * @param companyName The name of the company.
     * @param phoneNumber The phone number of the renter.
     * @param address     The address of the renter.
     */
    public Renter(String companyName, String phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Validates the company name and phone number using regular expressions.
     *
     * @return {@code true} if the company name and phone number are valid, {@code false} otherwise.
     */
    public boolean validate() {
        Pattern patternName = Pattern.compile(REGEX_NAME);
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherName = patternName.matcher(companyName);
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        return matcherName.find() && matcherPhone.find();
    }
}