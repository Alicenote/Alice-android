package com.namestore.alicenote.network.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

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

    @SerializedName("totalPrice")
    @Expose
    public int totalPrice; // dat ten bien: totalPrice !!!!

    @SerializedName("staff")
    @Expose
    public String staff;

    @SerializedName("service")
    @Expose
    public String service;

    @SerializedName("startTime")
    @Expose
    public String startTime;   // startTime !!


    @SerializedName("duration")
    @Expose
    public String duration;

    // bi trung voi BaseResponse#status

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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
}
