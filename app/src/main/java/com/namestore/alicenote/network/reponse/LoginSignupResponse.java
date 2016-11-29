package com.namestore.alicenote.network.reponse;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.namestore.alicenote.network.BaseResponse;

/**
 * Created by kienht on 11/7/16.
 */

public class LoginSignupResponse extends BaseResponse {

    @SerializedName("token")
    @Expose
    private String mToken;

    @SerializedName("id")
    @Expose
    private int salonId;

    public String getToken() {
        return mToken;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getErrors() {
        return mErrors;
    }

}
