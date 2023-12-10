/**
 * The `Validate` class provides methods for filtering `Price` objects based on their price values.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import java.util.ArrayList;

public class Validate {
    /**
     * Filters the given array of Price objects based on whether their prices are less than or equal to the specified value.
     *
     * @param list  The array of Price objects to filter.
     * @param value The value to compare the prices against.
     * @return An ArrayList containing prices that satisfy the filter condition.
     */
    public static ArrayList<Double> filter(Price[] list, int value, boolean less) {
        ArrayList<Double> harga = new ArrayList<>();

        for (int i = 0; i < list.length; i++) {
            if (less) {
                if (list[i].price <= value) {
                    harga.add(list[i].price);
                }
            } else {
                if (list[i].price > value) {
                    harga.add(list[i].price);
                }
            }
        }

        return harga;
    }
}
