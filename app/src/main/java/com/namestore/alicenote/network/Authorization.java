package com.namestore.alicenote.network;

/**
 * Created by nhocnhinho on 12/11/2016.
 */

public class Authorization {

    private String userId;
    private String accessToken;

    public Authorization(String userId, String token) {
        this.userId = userId;
        this.accessToken = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
