package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by Nhocnhinho on 5/26/2015.
 */


public class Client_Object implements Serializable {


    private String tvName;


    public String getTvName() {
        return tvName;
    }


    public void setTvName(String tvName) {
        this.tvName = tvName;
    }





    public Client_Object(String tvName) {
        super();

        this.tvName = tvName;

    }
}
