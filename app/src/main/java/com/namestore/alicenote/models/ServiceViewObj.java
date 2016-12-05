package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by nhocnhinho on 23/11/2016.
 */

public class ServiceViewObj implements Serializable {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTvSvNameService() {
        return tvSvNameService;
    }

    public void setTvSvNameService(String tvSvNameService) {
        this.tvSvNameService = tvSvNameService;
    }

    public String getTvSvMoney() {
        return tvSvMoney;
    }

    public void setTvSvMoney(String tvSvMoney) {
        this.tvSvMoney = tvSvMoney;
    }

    public String getTvSvTime() {
        return tvSvTime;
    }

    public void setTvSvTime(String tvSvTime) {
        this.tvSvTime = tvSvTime;
    }

    private int id;

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    private int salonId;
    private String tvSvNameService;
    private String tvSvMoney;
    private String tvSvTime;




    public ServiceViewObj(int id , String tvSvNameService, String tvSvMoney, String tvSvTime) {
        super();

        this.id = id;
        this.tvSvNameService = tvSvNameService;
        this.tvSvMoney = tvSvMoney;
        this.tvSvTime = tvSvTime;

    }
    }