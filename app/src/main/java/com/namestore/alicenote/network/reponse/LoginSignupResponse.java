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
    private String token;

    public int getStatus() {
        return status;
    }

    public JsonObject getErrors() {
        return errors;
    }

    public String getToken() {
        return token;
    }
}
