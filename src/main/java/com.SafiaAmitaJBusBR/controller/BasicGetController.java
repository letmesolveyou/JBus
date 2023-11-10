package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.Algorithm;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import com.SafiaAmitaJBusBR.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface BasicGetController <T extends Serializable> {
    JsonTable<T> getJsonTable();

    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List <T> getpage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a ->true);
    }
}
