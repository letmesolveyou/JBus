package com.SafiaAmitaJBusBR;

import com.SafiaAmitaJBusBR.dbjson.Serializable;
public class Review extends Serializable
{
    public String date;
    public String desc;

    public Review(String date, String desc)
    {
        this.date = date;
        this.desc = desc;
    }

    public String toString()
    {
        return  "Date : " + date + "\nDescription: " + desc;
    }
}
