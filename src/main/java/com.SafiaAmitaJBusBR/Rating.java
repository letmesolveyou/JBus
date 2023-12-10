/**
 * The `Rating` class represents the rating information, including the count, total, and average rating.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

public class Rating {

    /** The count of ratings. */
    private long count;

    /** The total sum of ratings. */
    private long total;

    /**
     * Constructs a `Rating` object with initial count and total set to zero.
     */
    public Rating() {
        this.count = 0;
        this.total = 0;
    }

    /**
     * Inserts a new rating into the rating system, updating the count and total.
     *
     * @param rating The rating to be inserted.
     */
    public void insert(int rating) {
        this.total += rating;
        this.count++;
    }

    /**
     * Calculates and returns the average rating.
     *
     * @return The average rating, or 0 if there are no ratings.
     */
    public double getAverage() {
        if (this.count == 0) {
            return 0;
        }
        return (double) this.total / this.count;
    }

    /**
     * Returns the count of ratings.
     *
     * @return The count of ratings.
     */
    public long getCount() {
        return this.count;
    }

    /**
     * Returns the total sum of ratings.
     *
     * @return The total sum of ratings.
     */
    public long getTotal() {
        return this.total;
    }

    /**
     * Returns a string representation of the `Rating` object.
     *
     * @return A string representation including the count and total.
     */
    @Override
    public String toString() {
        return "Count : " + count + "\nTotal : " + total;
    }
}