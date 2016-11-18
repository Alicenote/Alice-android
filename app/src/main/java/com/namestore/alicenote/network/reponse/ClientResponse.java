package com.namestore.alicenote.network.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

import java.util.ArrayList;

/**
 * Created by nhocnhinho on 15/11/2016.
 */

public class ClientResponse extends BaseResponse {

    @SerializedName("totalItem")
    @Expose
    public int totalItem;

    public int getTotalItem() {
        return totalItem;
    }

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

        public void setLastName(String last_Name) {
            this.lastName = last_Name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @SerializedName("id")
        @Expose
        public int id;

        @SerializedName("first_name")
        @Expose
        public String firstName;

        @SerializedName("last_name")
        @Expose

        public String lastName;


        @SerializedName("image")
        @Expose
        public String image;

    }
}