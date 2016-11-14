package com.namestore.alicenote.network.reponse;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

import java.util.List;

/**
 * Created by nhocnhinho on 12/11/2016.
 */

public class DashBoardRespone extends BaseResponse {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("client")
    @Expose
    public String client;

    @SerializedName("total_price")
    @Expose
    public int total_price;

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

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("update")
    @Expose
    public String updated;

    @SerializedName("created")
    @Expose
    public String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getErrors() {
        return mErrors;
    }

}





