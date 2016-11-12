package com.namestore.alicenote.network;

import com.namestore.alicenote.network.reponse.DashBoardRespone;
import com.namestore.alicenote.network.reponse.LoginSignupResponse;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.models.UserObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kienht on 11/3/16.
 */

public interface AliceApi {

    @POST(Constants.API_LOGIN)
    Call<LoginSignupResponse> login(@Body UserObj user);

    @POST(Constants.API_SIGNUP)
    Call<LoginSignupResponse> signup(@Body UserObj user);

    @POST(Constants.API_SOCIAL_LOGIN)
    Call<LoginSignupResponse> socialLogin(@Body UserObj user);

    @GET(Constants.API_DASHBOARD_WEEK_APPOINTMENT)
    Call<List<DashBoardRespone>> searchWeekAppointment();

    @GET(Constants.API_DASHBOARD_WEEK_APPOINTMENT)
    Call<DashBoardRespone> searchUpCommingAppointment();
}

