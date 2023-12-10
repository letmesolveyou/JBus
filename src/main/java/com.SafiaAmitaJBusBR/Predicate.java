/**
 * The `Predicate` interface represents a functional interface for a predicate (boolean-valued function) of one argument.
 * It is used to define custom predicates for various functional programming operations.
 *
 * @param <T> The type of the input to the predicate.
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 **/

package com.SafiaAmitaJBusBR;

@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param pred The input argument.
     * @return True if the input argument matches the predicate, otherwise false.
     */
    boolean predicate(T pred);
}
