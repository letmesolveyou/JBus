package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.*;
import com.SafiaAmitaJBusBR.dbjson.JsonAutowired;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>{

    @JsonAutowired(value = Bus.class, filepath= "C:\\\\Users\\\\LENOVO\\\\Documents\\\\OOP\\\\JBus\\\\src\\\\main\\\\java\\\\com.SafiaAmitaJBusBR\\\\json\\\\bus.json")
    public static JsonTable<Bus> busTable;

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create
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
        JsonTable<Account> NewaccTable = new AccountController().getJsonTable();
        Account akun = Algorithm.<Account>find(NewaccTable, a->a.id == accountId);
        if(akun == null || akun.company == null || !Algorithm.<Station>exists(new StationController().getJsonTable(), t -> t.id == stationArrivalId) || !Algorithm.<Station>exists(new StationController().getJsonTable(), b -> b.id == stationDepartureId))
        {
            return new BaseResponse<>(false, "Bus gagal dibuat", null);

        }
        Bus bus = new Bus(name, facilities, new Price(price), capacity, busType, Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationArrivalId), Algorithm.<Station>find(new StationController().getJsonTable(), t->t.id == stationDepartureId));
        bus.accountId = accountId;
        busTable.add(bus);
        return new BaseResponse<>(true, "Bus Berhasil dibuat", bus);
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule
            (
                    @RequestParam int busId,
                    @RequestParam String time
            )
    {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, t -> t.id == busId);
            bus.addSchedule(Timestamp.valueOf(time));
            return new BaseResponse<>(true, "Schedule berhasil dibuat", bus);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Schedule berhasil dibuat", null);
        }
    }

    @GetMapping("/getMyBus")
    public BaseResponse<List<Bus>> getMyBus(@RequestParam int accountId) {
        List<Bus> bus = Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);
        if (bus != null) {
            return new BaseResponse<>(true, "Berhasil getMyBus", bus);
        }
        return new BaseResponse<>(false, "Gagal getMyBus", null);
    }

    @GetMapping("/getAllBus")
    public BaseResponse<List<Bus>> getAllBus(){
        return new BaseResponse<>(true, "", getJsonTable());
    }



}