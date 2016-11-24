package com.namestore.alicenote;

import java.io.Serializable;

/**
 * Created by kienht on 10/28/16.
 */

public class Constants {

    public static final String TAG = "ALICE";
    public static String TOKEN = "null";

    public static final int NAIL = 0;
    public static final int HAIR = 1;

    public static final class Database {
        public static final String DB_NAME = "alice.db";
        public static final int DB_VER = 1;

    }

     /*START API SERVER*/

    public static final String URL_SERVER = "http://api.alicenote.com";
    public static final String API_LOGIN = "/v1/users/login";
    public static final String API_SOCIAL_LOGIN = "/v1/users/social-login";
    public static final String API_SIGNUP = "/v1/users/register";
    public static final String API_FILL_SETUP_SALON = "/v1/welcomes/get-setup";
    public static final String API_REQUEST_SETUP_SALON = "/v1/welcomes/set-setup";
    /*DashBoard*/
    public static final String API_DASHBOARD_WEEK_APPOINTMENT = "/v1/dash-boards/get-week-appointment";
    public static final String API_DASHBOARD_UPCOMMING_APPOINTMENT = "v1/dash-boards/get-upcomming-appointment";

  /*Client*/
    public static final String API_CLIENT ="v1/clients";
    public static final String API_VIEW_CLIENT ="/v1/clients/view";
    public static final String API_ADD_CLIENT="/v1/clients/create";
    public static final String API_UPDATE_CLIENT="/v1/clients/update";
    public static final String API_DEL_CLIENT ="/v1/clients/delete";

    /*END API SERVER*/

    /* LoginSignup Activity */

    public static final int NUM_PAGES = 5;
    public static final int KEY_LOGIN = 0;
    public static final int KEY_SIGNUP = 1;


    public static final String KEY_CHECK_CLIENT = "client.check";
    public static final String KEY_ID = "client.id";
    public static final String KEY_RESUME = "client.resume";
    public static final String KEY_CHECK_EDIT_CLIENT = "clien.check.edit";
    public static final String ADD_CLIENT = "client.add";
    public static final String VIEW_CLIENT = "client.view";
    public static final String EDIT_CLIENT = "client.edit";
    public static final String DEL_CLIENT = "client.del";

    public static final String LOGIN_SIGNUP_SCREEN = "screen.login.signup";
    public static final String LOGIN_FRAGMENT = "FragmentLogin.Login";
    public static final String SIGNUP_FRAGMENT = "FragmentSignup.Login";
    public static final String FORGOT_PASS = "ForgotPass.Login";
    public static final String REPORT_ERROR = "ReportError.Login";
    public static final String LOGIN_FACEBOOK = "LoginFacebook.Login";
    public static final String LOGIN_GOOGLE = "LoginGoogle.Login";
    public static final String CONTACT_ALICE = "ContactAlice.Login";
    public static final String LOGIN_BUTTON = "Login.Login";
    public static final String SIGNUP_BUTTON = "Signup.Login";
    public static final String LOGIN_SOCIAL = "Social.Login";

    /* End of Login Activity */

    /* First Setup Activity */

    public static final String FIRST_SETUP_SCREEN = "screen.login.signup";

    public static final int KEY_SETUP_INFO_SALON = 0;

    public static final String SETUP_INFO_SALON = "FirstSetup.Fragment";
    public static final String TIME_OPEN_DOOR_FRAGMENT = "TimeOpenDoor.Fragment";
    public static final String PICK_SERVICE = "PickService.Fragment";
    public static final String CONFIG_SERVICE = "ConfigService.Fragment";
    public static final String NAIL_SERVICE = "NailService.Fragment";
    public static final String HAIR_SERVICE = "HairService.Fragment";

    /* End of First Setup Activity */

       /* First Setup Activity */

    public static final String DASHBOARD_SCREEN = "screen.dashboard";


    /* End of First Setup Activity */

       /* First Setup Activity */

    public static final String ART_SCREEN = "screen.art";


    /* End of First Setup Activity */

}
