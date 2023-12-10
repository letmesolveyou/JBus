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

    @GetMapping("/getBuyerPayment")
    public BaseResponse<List<Payment>> getBuyerPayment(@RequestParam int buyerId) {
        List<Payment> p = Algorithm.<Payment>collect(getJsonTable(), val->val.buyerId==buyerId);
        if (p != null) {
            return new BaseResponse<>(true, "Berhasil getBuyerPayment", p);
        }
        return new BaseResponse<>(false, "Gagal getBuyerPayment", null);
    }

    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking (
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        AccountController ac = new AccountController();
        StationController sc = new StationController();
        BusController bc = new BusController();

        Predicate<Account> a = (val) -> val.id == buyerId;
        Predicate<Bus> b = (val) -> val.id == busId;
        Predicate<Schedule> s = (val) -> val.departureSchedule.equals(Timestamp.valueOf(departureDate));

        Account buyer = Algorithm.find(ac.getJsonTable(), a);
        Bus bus = Algorithm.find(bc.getJsonTable(), b);
        if (buyer != null && bus != null) {
            Schedule depart = Algorithm.find(bus.schedules, s);
            if (buyer.balance >= bus.price.price && depart != null && depart.isSeatAvailable(busSeats)) {
                Payment pay = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
                pay.status = Invoice.PaymentStatus.WAITING;
                Payment.makeBooking(Timestamp.valueOf(departureDate), pay.busSeats, bus);
                buyer.balance -= busSeats.size() * bus.price.price;
                paymentTable.addElement(pay);
                return new BaseResponse<>(true, "Berhasil makeBooking", pay);
            }
        }
        return new BaseResponse<>(false, "Gagal makeBooking", null);
    }

    @PostMapping ("/{id}/accept")
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

    @PostMapping ("/{id}/cancel")
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
