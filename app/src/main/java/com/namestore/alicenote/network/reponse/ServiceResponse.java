package com.namestore.alicenote.network.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

import java.util.ArrayList;

/**
 * Created by nhocnhinho on 23/11/2016.
 */

public class ServiceResponse  extends BaseResponse {

    @SerializedName("data")
    @Expose
    public ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("id")
        @Expose
        public int id;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("price")
        @Expose
        public String price;

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("duration")
        @Expose
        public String duration;

    }



}