package com.namestore.alicenote.network.reponse;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

/**
 * Created by nhocnhinho on 12/11/2016.
 */

public class DashBoardRespone extends BaseResponse {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("client")
    @Expose
    public String client;

    @SerializedName("total_price")
    @Expose
    public String total_price;

    @SerializedName("staff")
    @Expose
    public String staff;

    @SerializedName("service")
    @Expose
    public String service;

    @SerializedName("start_time")
    @Expose
    public String start_time;


    @SerializedName("duration")
    @Expose
    public String duration;



    @SerializedName("status")
    @Expose
    public String dashBoardStatus;

    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("update")
    @Expose
    public String updated;

    @SerializedName("created")
    @Expose
    public String created;


    public String getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getTotal_price() {
        return total_price;
    }

    public String getStaff() {
        return staff;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getService() {
        return service;
    }

    public String getDuration() {
        return duration;
    }


    public String getDate() {
        return date;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCreated() {
        return created;
    }
    public String getDashBoardStatus() {
        return dashBoardStatus;
    }

    public DashBoardRespone(String id, String client, String total_price,String staff,String service,String start_time,
                            String duration, String dashBoardStatus, String date,String updated, String created) {
        super();
        this.id = id;
        this.client = client;
        this.total_price =total_price;
        this.staff =staff;
        this.service=service;
        this.start_time=start_time;
        this.duration=duration;
        this.dashBoardStatus=dashBoardStatus;
        this.date=date;
        this.updated=updated;
        this.created=created;

    }

}
