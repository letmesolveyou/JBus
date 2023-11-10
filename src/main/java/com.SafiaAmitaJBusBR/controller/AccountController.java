package com.SafiaAmitaJBusBR.controller;

import com.SafiaAmitaJBusBR.Account;
import com.SafiaAmitaJBusBR.Algorithm;
import com.SafiaAmitaJBusBR.Renter;
import com.SafiaAmitaJBusBR.dbjson.JsonAutowired;
import com.SafiaAmitaJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static JsonTable<Account> accountTable;
    @JsonAutowired(value = Account.class, filepath= "src\\main\\java\\com\\SafiaAmitaJBusBR\\json\\accountDatabase.json")

    @GetMapping
    String index(){
        return "account page";
    }

    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        String hashedPass = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            hashedPass = sb.toString();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Account acc = new Account (name, email, password);
        Account hashedAcc = new Account (name, email, hashedPass);
        if (acc.name.isBlank() || !acc.validate() || Algorithm.<Account>exists(getJsonTable(), t -> t.email.equals(acc.email))){
            return new BaseResponse<>(false, "Gagal register", null);
        }
        accountTable.add(hashedAcc);
        return new BaseResponse<Account>(true, "Register Berhasil!", hashedAcc);
    }

    @PostMapping("/login")
    BaseResponse<Account> login
            (
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        String hashedPass = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            hashedPass = sb.toString();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Account account = new Account("", email, hashedPass);
        if (Algorithm.<Account>exists(getJsonTable(), acc -> acc.email.equals(account.email) && acc.password.equals(account.password))) {
            Algorithm.<Account>find(getJsonTable(), c -> c.email == account.email);
            return new BaseResponse<>(true, "Berhasil login", Algorithm.<Account>find(getJsonTable(), c -> c.email.equals(account.email)));
        }
        return new BaseResponse<>(false, "Gagal login", null);
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    )
    {
        if(Algorithm.<Account>exists(getJsonTable(), t->t.id == id && t.company == null)){
            Renter renter = new Renter(companyName, address, phoneNumber);
            return new BaseResponse<>(true, "Renter berhasil dibuat", renter);
        }
        return new BaseResponse<>(false, "Gagal membuat renter", null);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam Double amount
    )
    {
        if(Algorithm.<Account>exists(getJsonTable(), t->t.id == id && t.topUp(amount)))
            return new BaseResponse<>(true, "Top Up Berhasil", amount);
        return new BaseResponse<>(false, "Top Up Tidak berhasil", amount);
    }

    /*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
    */
}
