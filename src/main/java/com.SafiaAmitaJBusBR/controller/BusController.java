package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.*;
import com.SafiaAmitaJBusBR.dbjson.JsonAutowired;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>
{
    public static @JsonAutowired(
            value = Bus.class, filepath = "C:\\Users\\LENOVO\\Documents\\OOP\\JBus\\src\\main\\java\\com.SafiaAmitaJBusBR\\json\\bus.json"
    ) JsonTable<Bus> busTable;

    @Override
    public JsonTable<Bus> getJsonTable() {
        return BusController.busTable;
    }

    @PostMapping("/create")
    BaseResponse<Bus> create
            (
                    @RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int capacity,
                    @RequestParam List<Facility> facilities,
                    @RequestParam BusType busType,
                    @RequestParam int price,
                    @RequestParam int stationDepartureId,
                    @RequestParam int stationArrivalId
            )
    {
        try {
            JsonTable<Account> AccTable = new AccountController().getJsonTable();
            Account account = Algorithm.<Account>find(AccTable, a->a.id == accountId);
            if (account == null || account.company == null || !Algorithm.<Station>exists(new StationController().getJsonTable(), t -> t.id == stationArrivalId) || !Algorithm.<Station>exists(new StationController().getJsonTable(), b -> b.id == stationDepartureId) ) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            Bus newBus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationArrivalId), Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationDepartureId));
            busTable.add(newBus);
            return new BaseResponse<>(true, "Bus added successfully", newBus);
        } catch (Exception e) {
            return new BaseResponse<>(false, "An error occurred while adding the bus", null);
        }
    }

    @PostMapping("/addSchedule")
    BaseResponse<Bus> addSchedule
            (
                    @RequestParam int busId,
                    @RequestParam String time
            )
    {
        try{
            Bus newBus = Algorithm.<Bus>find(busTable, t->t.id == busId);
            newBus.addSchedule(Timestamp.valueOf(time));
            return new BaseResponse<>(true, "Schedule added successfully", newBus);
        }catch (Exception e){
            return new BaseResponse<>(false, "An error occurred while adding the schedule", null);
        }
    }
}
