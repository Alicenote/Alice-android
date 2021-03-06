package com.namestore.alicenote.network;

import com.namestore.alicenote.models.UserObj;
import com.namestore.alicenote.models.VenueViewEditObj;
import com.namestore.alicenote.network.reponse.FillFirstSetupResponse;
import com.namestore.alicenote.network.reponse.LoginSignupResponse;
import com.namestore.alicenote.network.reponse.VenueViewEditResponse;
import com.namestore.alicenote.network.reponse.VenueViewResponse;
import com.namestore.alicenote.network.request.FirstSetupRequest;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kienht on 12/2/16.
 */

public class ObservableManager {

    public static Observable<LoginSignupResponse> login(UserObj user) {
        return NetworkGenerator.getInstance()
                .getApi()
                .login(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(loginSignupResponse -> loginSignupResponse);
    }

    public static Observable<LoginSignupResponse> signup(UserObj userObj) {
        return NetworkGenerator.getInstance()
                .getApi()
                .signup(userObj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(loginSignupResponse -> loginSignupResponse);
    }

    public static Observable<LoginSignupResponse> socialLogin(UserObj user) {
        return NetworkGenerator.getInstance()
                .getApi()
                .socialLogin(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(loginSignupResponse -> loginSignupResponse);
    }

    public static Observable<FillFirstSetupResponse> fillFirstSetup(Integer salonId) {
        return NetworkGenerator.getInstance()
                .getApi()
                .fillFirstSetup(salonId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(fillFirstSetupResponse -> fillFirstSetupResponse);
    }

    public static Observable<BaseResponse> requestFirstSetupSalon(Integer salonId, FirstSetupRequest firstSetupRequest) {
        return NetworkGenerator.getInstance()
                .getApi()
                .requestFirstSetupSalon(salonId, firstSetupRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(baseResponse -> baseResponse);

    }
    public static Observable<VenueViewResponse> VenueView (Integer salongId,Integer locationId){
        return NetworkGenerator.getInstance()
                .getApi()
                .VenueView(salongId,locationId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(VenueViewResponse->VenueViewResponse);
    }
    public static Observable<VenueViewEditResponse> VenueViewEdit (Integer salongId, Integer locationId, VenueViewEditObj venueViewEditObj){
        return NetworkGenerator.getInstance()
                .getApi()
                .VenueViewEdit(salongId,locationId,venueViewEditObj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(VenueViewEditResponse->VenueViewEditResponse);
    }
}
