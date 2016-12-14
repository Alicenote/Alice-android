package com.namestore.alicenote.network;

import com.namestore.alicenote.models.AppointmentObj;
import com.namestore.alicenote.network.reponse.FillFirstSetupResponse;


import com.namestore.alicenote.network.reponse.LoginSignupResponse;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.models.UserObj;

import com.namestore.alicenote.network.reponse.VenueViewResponse;
import com.namestore.alicenote.network.request.FirstSetupRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kienht on 11/3/16.
 */

public interface AliceApi {

    @POST(Constants.API_LOGIN)
    Observable<LoginSignupResponse> login(@Body UserObj user);

    @POST(Constants.API_SIGNUP)
    Observable<LoginSignupResponse> signup(@Body UserObj user);

    @POST(Constants.API_SOCIAL_LOGIN)
    Observable<LoginSignupResponse> socialLogin(@Body UserObj user);

    @GET(Constants.API_FILL_SETUP_SALON)
    Observable<FillFirstSetupResponse> fillFirstSetup(@Query("salon_id") Integer salonId);

    @POST(Constants.API_REQUEST_SETUP_SALON)
    Observable<BaseResponse> requestFirstSetupSalon(@Query("salon_id") Integer salonId, @Body FirstSetupRequest firstSetupRequest);

    @GET("https://api.myjson.com/bins/1kpjf")
    Call<List<AppointmentObj>> getEvent();

    @GET(Constants.API_VENUE_VIEW)
    Observable<VenueViewResponse> VenueView(@Query("salon_id") Integer salonId,@Query("location_id") Integer locationId);





}

