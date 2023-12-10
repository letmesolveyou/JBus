/**
 * The `Price` class represents the pricing information for a bus, including the original price and any rebate applied.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

public class Price {

    /** The original price of the bus. */
    public double price;

    /** The rebate applied to the original price. */
    public double rebate;

    /**
     * Constructs a `Price` object with the given original price and no rebate.
     *
     * @param price The original price of the bus.
     */
    public Price(double price) {
        this.price = price;
        this.rebate = 0;
    }

    /**
     * Constructs a `Price` object with the given original price and rebate.
     *
     * @param price  The original price of the bus.
     * @param rebate The rebate applied to the original price.
     */
    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
    }

    /**
     * Returns a string representation of the `Price` object.
     *
     * @return A string representation including the original price and rebate.
     */
    @Override
    public String toString() {
        return "Price : " + price + "\nRebate : " + rebate;
    }
}
