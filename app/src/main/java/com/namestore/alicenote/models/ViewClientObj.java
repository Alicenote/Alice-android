package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by nhocnhinho on 15/11/2016.
 */

public class ViewClientObj implements Serializable {

    private int ID;
    private String tvClNameStaff;
    private String tvClDate;
    private String tvClTimeStart;
    private String tvClMoney;
    private String tvClNameService;
    public String getTvClDate() {
        return tvClDate;
    }

    public void setTvClDate(String tvClDate) {
        this.tvClDate = tvClDate;
    }

    public String getTvClTimeStart() {
        return tvClTimeStart;
    }

    public void setTvClTimeStart(String tvClTimeStart) {
        this.tvClTimeStart = tvClTimeStart;
    }

    public String getTvClMoney() {
        return tvClMoney;
    }

    public void setTvClMoney(String tvClMoney) {
        this.tvClMoney = tvClMoney;
    }

    public String getTvClNameService() {
        return tvClNameService;
    }

    public void setTvClNameService(String tvClNameService) {
        this.tvClNameService = tvClNameService;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTvClNameStaff() {
        return tvClNameStaff;
    }

    public void setTvClNameStaff(String tvClNameStaff) {
        this.tvClNameStaff = tvClNameStaff;
    }



    public ViewClientObj(int ID) {
        super();
        this.ID = ID;

    }


    public ViewClientObj(int ID, String tvClNameStaff, String tvClDate, String tvClTimeStart, String tvClMoney, String tvClNameService) {
        super();
        this.ID = ID;
        this.tvClNameService = tvClNameService;
        this.tvClNameStaff = tvClNameStaff;
        this.tvClDate = tvClDate;
        this.tvClTimeStart = tvClTimeStart;
        this.tvClMoney = tvClMoney;
    }
}