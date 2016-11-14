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
    protected int mStatus;

    public int getStatus() {
        return mStatus;
    }

    @SerializedName("errors")
    @Expose
    protected String mErrors;

    public String getErrors() {
        return mErrors;
    }

}
