package com.namestore.alicenote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nhocnhinho on 24/11/2016.
 */

public class EditServiceObj  implements Serializable {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("price")
    @Expose
    public String price;

    @SerializedName("duration")
    @Expose
    public String duration;

    @SerializedName("group_id")
    @Expose
    public String groupId;


    @SerializedName("description")
    @Expose
    public int description;

    @SerializedName("old_price")
    @Expose
    public String oldPrice;

    @SerializedName("staffs")
    @Expose
    public ArrayList<Staff> id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public ArrayList<Staff> getId() {
        return id;
    }

    public void setId(ArrayList<Staff> id) {
        this.id = id;
    }

    public class Staff{
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @SerializedName("id")
        @Expose
        public String id;
    }


}
