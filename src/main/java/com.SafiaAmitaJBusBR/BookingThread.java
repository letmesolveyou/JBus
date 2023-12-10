/**
 * The `BookingThread` class represents a thread for making bookings for a specific bus at a given timestamp.
 * It extends the `Thread` class and is designed to run concurrently for handling booking operations.
 * The thread synchronizes on itself to ensure atomicity during the booking process.
 *
 * @author Safia Amita
 * @version 1.0
 * @since 2023-09-07
 */
package com.SafiaAmitaJBusBR;

import java.sql.Timestamp;
public class BookingThread extends Thread {

    /**
     * The bus for which the booking is being made.
     */
    private Bus bus;

    /**
     * The timestamp at which the booking is requested.
     */
    private Timestamp timestamp;

    /**
     * Constructs a `BookingThread` object with the specified name, bus, and timestamp.
     * The thread is started upon construction.
     *
     * @param name      The name of the thread.
     * @param bus       The bus for which the booking is being made.
     * @param timestamp The timestamp at which the booking is requested.
     */
    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    /**
     * Executes the booking thread. It synchronizes on itself to ensure atomicity during the booking process.
     * Prints success or failure messages based on the result of the booking operation.
     */
    @Override
    public void run() {
        System.out.println(
                "Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " is running"
        );

        synchronized (this) {
            if (Payment.makeBooking(this.timestamp, "BR01", this.bus)) {
                System.out.println("Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " Thread berhasil");
            } else {
                System.out.println("Thread " + Thread.currentThread().getId() + " Id : " + Thread.currentThread().getName() + " Thread Tidak Berhasil");
            }
        }

        // Additional synchronized block (redundant in your existing code)
        synchronized (this) {
            Payment.makeBooking(this.timestamp, "BR01", this.bus);
        }
    }
}