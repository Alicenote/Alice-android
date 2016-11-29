package com.namestore.alicenote.network;

import com.namestore.alicenote.models.EditServiceObj;
import com.namestore.alicenote.models.Event;
import com.namestore.alicenote.models.AddEditClientObj;
import com.namestore.alicenote.network.reponse.ClientAddEditDelResponse;
import com.namestore.alicenote.network.reponse.ClientResponse;
import com.namestore.alicenote.network.reponse.DashBoardRespone;
import com.namestore.alicenote.network.reponse.LoginSignupResponse;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.models.UserObj;
import com.namestore.alicenote.network.reponse.ServiceEditDelResponse;
import com.namestore.alicenote.network.reponse.ServiceResponse;
import com.namestore.alicenote.network.reponse.ClientViewResponse;
import com.namestore.alicenote.network.reponse.ServiceViewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
    Call<DashBoardRespone> searchWeekAppointment(@Query("salon_id") Integer salonId,
                                                       @Query("location_id") Integer locationId);

    @GET(Constants.API_DASHBOARD_UPCOMMING_APPOINTMENT)
    Call<DashBoardRespone> searchUpCommingAppointment(@Query("salon_id") Integer salonId,
                                                      @Query("location_id") Integer locationId);

    @GET("https://api.myjson.com/bins/1kpjf")
    Call<List<Event>> getEvent();

    @GET(Constants.API_CLIENT)
    Call<ClientResponse> searchClient(@Query("salon_id") Integer salonId,
                                      @Query("page") Integer locationId,
                                      @Query("per-page") Integer perPageId,
                                      @Query("sort") String sortId);

    @GET(Constants.API_VIEW_CLIENT)
    Call<ClientViewResponse> searchViewClient(@Query("salon_id") Integer salonId, @Query("id")Integer id);

    @POST(Constants.API_ADD_CLIENT)
    Call<ClientAddEditDelResponse> pushInfoClient (@Body AddEditClientObj addEditClientObj, @Query("salon_id") Integer salonId);
    @PUT(Constants.API_UPDATE_CLIENT)
    Call<ClientAddEditDelResponse> updateInfoClient (@Body AddEditClientObj addEditClientObj,
                                                     @Query("salon_id") Integer salonId, @Query("id") Integer id);
    @DELETE(Constants.API_DEL_CLIENT)
    Call<ClientAddEditDelResponse> delClient (@Query("salon_id") Integer salonId, @Query("id") Integer id);


    @GET(Constants.API_SERVICE)
    Call<ServiceResponse> searchService(@Query("salon_id") Integer salonId, @Query("group_id")Integer id,
                                        @Query("page")Integer page,@Query("per-page")Integer perPage,
                                        @Query("key")String key);

    @PUT(Constants.API_UPDATE_SERVICE)
    Call<ServiceEditDelResponse> updateInfoService(@Body EditServiceObj editServiceObj,
                                                   @Query("salon_id") Integer salonId, @Query("id") Integer id);
    @DELETE(Constants.API_DEL_SERVICE)
    Call<ServiceEditDelResponse> delService (@Query("salon_id") Integer salonId, @Query("id") Integer id);

    @PUT(Constants.API_VIEW_SERVICE)
    Call<ServiceViewResponse> searchViewService(@Query("salon_id") Integer salonId, @Query("id") Integer id);

}

