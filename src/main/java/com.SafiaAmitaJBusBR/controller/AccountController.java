package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.Account;
import com.SafiaAmitaJBusBR.Renter;
import com.SafiaAmitaJBusBR.dbjson.JsonAutowired;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    //@JsonAutowired(value = Account.class, filepath= "")
    //public static accountTable

    public JsonTable<Account> getJsonTable();
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password,
                    @RequestParam Renter company,
                    @RequestParam double balance
            )
    {
        if (name.isBlank() || !Account.validate(email, password)){
            return new BaseResponse<>(false, "Gagal register", null);
        }
        return new BaseResponse<Account>(name, email, password, company, balance);
    }

    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
