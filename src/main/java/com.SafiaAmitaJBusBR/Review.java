/**
 * The `Review` class represents a review with a date and description.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;

public class Review extends Serializable {

    /** The date of the review. */
    public String date;

    /** The description of the review. */
    public String desc;

    /**
     * Constructs a `Review` object with a specified date and description.
     *
     * @param date The date of the review.
     * @param desc The description of the review.
     */
    public Review(String date, String desc) {
        this.date = date;
        this.desc = desc;
    }

    /**
     * Returns a string representation of the `Review` object.
     *
     * @return A string containing the date and description of the review.
     */
    @Override
    public String toString() {
        return "Date : " + date + "\nDescription: " + desc;
    }
}
