package com.namestore.alicenote.network;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kienht on 11/4/16.
 */

public class BaseResponse implements Serializable {

    @SerializedName("status")
    @Expose
    protected int status;

    public int getStatus() {
        return status;
    }

    @SerializedName("errors")
    @Expose
    protected JsonObject errors;

    public JsonObject getErrors() {
        return errors;
    }

}
