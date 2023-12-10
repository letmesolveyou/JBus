/**
 * The `Voucher` class represents a voucher that can be applied to a `Price` object.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;

public class Voucher extends Serializable {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    /**
     * Constructs a new Voucher with the specified attributes.
     *
     * @param name    The name of the voucher.
     * @param code    The code associated with the voucher.
     * @param type    The type of the voucher (REBATE or DISCOUNT).
     * @param minimum The minimum price required to apply the voucher.
     * @param cut     The amount to cut or discount from the price.
     */
    public Voucher(String name, int code, Type type, double minimum, double cut) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    /**
     * Applies the voucher to the given `Price` object and returns the discounted price.
     *
     * @param price The Price object to apply the voucher to.
     * @return The discounted price after applying the voucher.
     */
    public double apply(Price price) {
        this.used = true;

        if (type == Type.DISCOUNT) {
            if (cut > 100) {
                cut = 100;
            }

            if (cut == 100) {
                return 0;
            }

            return (double) price.price - price.price * cut / 100.0;
        }

        if (type == Type.REBATE) {
            if (price.price < cut) {
                return 0;
            }

            return (double) price.price - cut;
        }

        return price.price;

    }

    /**
     * Checks if the voucher has been used and can be applied to the given `Price` object.
     *
     * @param price The Price object to check if the voucher can be applied.
     * @return True if the voucher can be applied, false otherwise.
     */
    public boolean canApply(Price price) {
        return !used && price.price > minimum;
    }

    /**
     * Checks if the voucher has been used.
     *
     * @return True if the voucher has been used, false otherwise.
     */
    public boolean isUsed() {
        return this.used;
    }
}
