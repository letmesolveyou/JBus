package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.*;
import com.SafiaAmitaJBusBR.dbjson.JsonAutowired;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static @JsonAutowired(
            value = Payment.class, filepath = "C:\\Users\\LENOVO\\Documents\\OOP\\JBus\\src\\main\\java\\com.SafiaAmitaJBusBR\\json\\payment.json"
    ) JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return PaymentController.paymentTable;
    }

    @PostMapping("/makeBooking")
    BaseResponse<Payment> makeBooking
            (
                    @RequestParam int buyerId,
                    @RequestParam int renterId,
                    @RequestParam int busId,
                    @RequestParam List<String> busSeats,
                    @RequestParam String departureDate
            )
    {
        try {
            Account buyerAcc = Algorithm.<Account>find(new AccountController().getJsonTable(), t -> t.id == buyerId);
            Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), a -> a.id == busId);
            if (buyerAcc == null || bus == null || bus.price.price < buyerAcc.balance || !Algorithm.<Schedule>exists(bus.schedules, a -> a.departureSchedule.equals(Timestamp.valueOf(departureDate))) || !Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus)) {
                return new BaseResponse<>(false, "An error occurred while booking", null);
            }
            Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
            payment.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Booked successfully", payment);
        } catch (Exception e) {
            return new BaseResponse<>(false, "An error occurred while booking", null);
        }
    }

    @PostMapping("/{id}/accept")
    BaseResponse<Payment> accept
            (
                    @PathVariable int id
            )
    {
        try{
            Payment payment = getById(id);
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Pembayaran berhasil", payment);
        }
        catch(Exception e){
            return new BaseResponse<>(false, "Pembayaran Tidak berhasil", null);
        }
    }

    @PostMapping("/{id}/cancel")
    BaseResponse<Payment> cancel
            (
                    @PathVariable int id
            )
    {
        try{
        Payment payment = getById(id);
        payment.status = Invoice.PaymentStatus.FAILED;
        return new BaseResponse<>(true, "Pembayaran Tidak berhasil", payment);
    }
        catch(Exception e){
        return new BaseResponse<>(false, "Pembayaran undefined", null);
    }
    }
}
