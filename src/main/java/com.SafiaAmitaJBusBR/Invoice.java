/**
 * The `Invoice` class represents an invoice entity containing information such as timestamp, buyer ID,
 * renter ID, bus rating, and payment status. It extends the `Serializable` class for serialization purposes.
 * The class includes nested enumerations for bus rating and payment status.
 * Instances of this class can be created to represent invoices in the system.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import java.sql.Timestamp;
import com.SafiaAmitaJBusBR.dbjson.Serializable;

public class Invoice extends Serializable {

    /**
     * The timestamp of the invoice creation.
     */
    public Timestamp time;

    /**
     * The ID of the buyer associated with the invoice.
     */
    public int buyerId;

    /**
     * The ID of the renter associated with the invoice.
     */
    public int renterId;

    /**
     * The bus rating associated with the invoice.
     */
    public BusRating rating;

    /**
     * The payment status associated with the invoice.
     */
    public PaymentStatus status;

    /**
     * Enumeration representing different bus ratings (NONE, NEUTRAL, GOOD, BAD).
     */
    public enum BusRating {
        NONE, NEUTRAL, GOOD, BAD;
    }

    /**
     * Enumeration representing different payment statuses (FAILED, WAITING, SUCCESS).
     */
    public enum PaymentStatus {
        FAILED, WAITING, SUCCESS;
    }

    /**
     * Protected constructor for creating an invoice with specified buyer and renter IDs.
     *
     * @param buyerId The ID of the buyer associated with the invoice.
     * @param renterId The ID of the renter associated with the invoice.
     */
    protected Invoice(int buyerId, int renterId) {
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Constructor for creating an invoice with specified buyer and renter objects.
     *
     * @param buyer The buyer associated with the invoice.
     * @param renter The renter associated with the invoice.
     */
    public Invoice(Account buyer, Renter renter) {
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Returns a string representation of the `Invoice` object.
     *
     * @return A string containing the details of the invoice.
     */
    @Override
    public String toString() {
        return "Invoice Id : " + super.id + "\nBuyer Id : " + buyerId + "\nRenter Id : " + renterId + "\nTime : " + time + "\nRating : " + rating + "\nStatus : " + status;
    }
}