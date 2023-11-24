package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.*;
import com.SafiaAmitaJBusBR.dbjson.JsonAutowired;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    @JsonAutowired(value = Payment.class, filepath= "C:\\\\Users\\\\LENOVO\\\\Documents\\\\OOP\\\\JBus\\\\src\\\\main\\\\java\\\\com.SafiaAmitaJBusBR\\\\json\\\\payment.json")
    public static JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @RequestMapping(value="/makeBooking", method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking
            (
                    @RequestParam int buyerId,
                    @RequestParam int renterId,
                    @RequestParam int busId,
                    @RequestParam List<String> busSeats,
                    @RequestParam String departureDate
            )
    {
        Account buyer = Algorithm.<Account>find(new AccountController().getJsonTable(), t -> t.id == buyerId);
        Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), a -> a.id == busId);
        if (buyer == null || bus == null || bus.price.price < buyer.balance ||
                !Algorithm.<Schedule>exists(bus.schedules, a -> a.departureSchedule.equals(Timestamp.valueOf(departureDate)))
                || !Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus)) {
            return new BaseResponse<>(false, "Gagal membuat booking", null);
        }
        Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
        payment.status = Invoice.PaymentStatus.WAITING;
        paymentTable.add(payment);
        return new BaseResponse<>(true, "Berhasil membuat booking", payment);
    }

    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id)
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

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id)
    {
        try{
            Payment payment = getById(id);
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Pembayaran Tidak berhasil", payment);
        }
        catch(Exception e){
            return new BaseResponse<>(false, "Pembayaran Undefined", null);
        }
    }

}
