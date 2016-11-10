package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by Nhocnhinho on 5/26/2015.
 */


public class ClientObj implements Serializable {


    private String tvName;


    public String getTvName() {
        return tvName;
    }


    public void setTvName(String tvName) {
        this.tvName = tvName;
    }





    public ClientObj(String tvName) {
        super();

        this.tvName = tvName;

    }
}
