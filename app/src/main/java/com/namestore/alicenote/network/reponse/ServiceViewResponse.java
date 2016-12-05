package com.namestore.alicenote.network.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

import java.util.ArrayList;

/**
 * Created by nhocnhinho on 23/11/2016.
 */

public class ServiceViewResponse extends BaseResponse {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    public Data data;

    public class Data {


        @SerializedName("id")
        @Expose
        public int id;

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("type")
        @Expose

        public String type;


        @SerializedName("group_id")
        @Expose
        public String groupId;

        @SerializedName("duration")
        @Expose
        public String duration;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @SerializedName("description")
        @Expose
        public String description;

        @SerializedName("price")
        @Expose
        public String price;

        @SerializedName("old_price")
        @Expose
        public String oldPrice;


        public ArrayList<Staffs> getStaffs() {
            return staffs;
        }

        public void setStaffs(ArrayList<Staffs> staffs) {
            this.staffs = staffs;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("staffs")
        @Expose
        public ArrayList<Staffs> staffs;

        public class Staffs {

            @SerializedName("id")
            @Expose
            public int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            @SerializedName("first_name")
            @Expose
            public String firstName;
            @SerializedName("last_name")
            @Expose

            public String lastName;
        }


    }
}