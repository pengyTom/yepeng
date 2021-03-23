package com.zzh.vo;


import java.util.Date;

/**
 * 价格实体--根据日期进行更改获取当天的价格
 */
public class PriceCalendar {
    private Date date;
    private double price;
    private String psid;
    public PriceCalendar() {
    }

    public PriceCalendar(Date date, double price) {
        this.date = date;
        this.price = price;
    }

    public PriceCalendar(Date date, double price, String psid) {
        this.date = date;
        this.price = price;
        this.psid = psid;
    }

    public void setPsid(String psid) {
        this.psid = psid;
    }

    public String getPsid() {

        return psid;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
