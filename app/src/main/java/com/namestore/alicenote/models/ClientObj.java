package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by Nhocnhinho on 5/26/2015.
 */


public class ClientObj implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTvLastName() {
        return tvLastName;
    }

    public void setTvLastName(String tvLastName) {
        this.tvLastName = tvLastName;
    }

    public String getTvFirstName() {
        return tvFirstName;
    }

    public void setTvFirstName(String tvFirstName) {
        this.tvFirstName = tvFirstName;
    }

    private int id;
    private String tvFirstName;
    private String tvLastName;
    private String imgAvatar;

    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }


    public ClientObj(int id ,String tvFirstName,String tvLastName,String imgAvatar) {
        super();

        this.id = id;
        this.tvFirstName = tvFirstName;
        this.tvLastName = tvLastName;
        this.imgAvatar = imgAvatar;


    }
}
