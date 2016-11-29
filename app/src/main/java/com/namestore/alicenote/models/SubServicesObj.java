package com.namestore.alicenote.models;

/**
 * Created by kienht on 11/2/16.
 */

public class SubServicesObj {

    public SubServicesObj() {
    }

    public SubServicesObj(String nameSubServices) {
        this.nameSubServices = nameSubServices;
    }

    public SubServicesObj(String nameSubServices, boolean isCheck) {
        this.nameSubServices = nameSubServices;
        this.isCheck = isCheck;
    }

    public SubServicesObj(String nameSubServices, boolean isCheck, int duration, int price, boolean work) {
        this.nameSubServices = nameSubServices;
        this.isCheck = isCheck;
        this.duration = duration;
        this.price = price;
        this.work = work;
    }

    private String nameSubServices;
    private boolean isCheck;
    private int duration;
    private float price;
    private boolean work;

    public String getNameSubServices() {
        return nameSubServices;
    }

    public void setNameSubServices(String nameSubServices) {
        this.nameSubServices = nameSubServices;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }
}
