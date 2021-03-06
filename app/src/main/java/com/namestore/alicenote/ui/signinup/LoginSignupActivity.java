package com.namestore.alicenote.ui.signinup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.PrefUtils;
import com.namestore.alicenote.network.ObservableManager;
import com.namestore.alicenote.network.reponse.LoginSignupResponse;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.dialog.DialogNotice;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.models.UserObj;
import com.namestore.alicenote.common.AppUtils;

import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.signinup.fragment.FillFullInforUserFragment;
import com.namestore.alicenote.ui.signinup.fragment.LoginFragment;
import com.namestore.alicenote.ui.signinup.fragment.SignUpFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import rx.Subscriber;

/**
 * Created by kienht on 10/25/16.
 */

public class LoginSignupActivity extends BaseActivity
        implements OnFragmentInteractionListener, GoogleApiClient.OnConnectionFailedListener {

    public final static int SUCCESS = 1;
    public final static int LOGIN_ERROR = 100;
    public final static int REGISTER_ERROR = 101;
    public final static int LOGED = 102;
    public final static int FIRST_LOGIN = 103;
    private static final int GOOGLE_SIGN_IN = 9001;

    private AccessTokenTracker mAccessTokenTracker;
    private CallbackManager mCallbackManagerFb;
    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;
    private FillFullInforUserFragment mFillFullInforUserFragment;
    public UserObj mUser = new UserObj();
    private LoginButton mLoginButtonFb;
    private ProgressDialog prgDialog;
    private GoogleApiClient mGoogleApiClient;
    AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext(), () -> mAccessToken = AccessToken.getCurrentAccessToken());

        setContentView(R.layout.activity_login_signup);

        if (!getIntent().getExtras().containsKey(Constants.LOGIN_SIGNUP_SCREEN)) {
            Log.e("TAG", "bạn phải truyền key LOGIN_SIGNUP_SCREEN sang màn hình này");
            finish();
            return;
        }

        mLoginFragment = new LoginFragment();
        mSignUpFragment = new SignUpFragment();
        mFillFullInforUserFragment = new FillFullInforUserFragment();

        if (getIntent().getExtras().getInt(Constants.LOGIN_SIGNUP_SCREEN) == Constants.KEY_LOGIN) {
            showLoginView();
        } else {
            showSignupView();
        }

        mLoginButtonFb = (LoginButton) findViewById(R.id.button_facebook_login);

        loginFb();

        //Google Sign-in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //Google Sign-in

        prgDialog = new ProgressDialog(this);

    }

    /**
     * @login via Facebook
     */
    public void loginFb() {
        mCallbackManagerFb = CallbackManager.Factory.create();
        mLoginButtonFb.setVisibility(View.GONE);
        mLoginButtonFb.setReadPermissions(Arrays.asList("public_profile", "email"));
        mLoginButtonFb.setOnClickListener(view -> {
            mLoginButtonFb.setClickable(false);
            mAccessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                }
            };
            FacebookSdk.sdkInitialize(getApplicationContext(), () ->
                    mLoginButtonFb.registerCallback(mCallbackManagerFb, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    getUserInfoFromFb();
                    setPrgDialog("Loging");
                }

                @Override
                public void onCancel() {
                    AppUtils.logE("FB CANCEL");
                    mLoginButtonFb.setClickable(true);
                }

                @Override
                public void onError(FacebookException exception) {
                    AppUtils.logE("FB ERROR");
                    mLoginButtonFb.setClickable(true);

                }
            }));

            mAccessTokenTracker.startTracking();
        });

    }

    /**
     * @get user info after login fb success
     */
    private void getUserInfoFromFb() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), (object, response) -> {
            try {
                mUser.id = object.getString("id");
                mUser.email = object.getString("email");
                mUser.firstName = object.getString("first_name");
                mUser.lastName = object.getString("last_name");
                String gender = object.getString("gender");
                if (gender.equals("male")) {
                    mUser.gender = SignUpFragment.GENDER_MALE;
                } else {
                    mUser.gender = SignUpFragment.GENDER_FEMALE;
                }
                mUser.imageAvatar = "http://graph.facebook.com/" + mUser.id + "/picture?type=square&width=300&height=300";
                prgDialog.dismiss();
                showFillInforUserView();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,gender,name,first_name,last_name,email,birthday,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getUserInfoFromGooglePlus(int requestCode, Intent data) {
        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();

                mUser.firstName = acct.getGivenName();
                mUser.lastName = acct.getFamilyName();
                mUser.email = acct.getEmail();
                mUser.id = acct.getId();
                mUser.gender = 0;
                Uri personPhoto = acct.getPhotoUrl();
                if (personPhoto != null) {
                    mUser.imageAvatar = personPhoto.toString();
                }

                showFillInforUserView();

            } else {
                // Signed out, show unauthenticated UI.
            }
        }
    }

    /**
     * khi nào muốn hiện Login screen thì gọi hàm này. Có thể gọi từ SignUpFragment
     */
    private void showLoginView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mLoginFragment).commit();
    }

    /**
     * khi nào muốn hiện SignUp screen thì gọi hàm này. Có thể gọi từ LoginFragment
     */
    private void showSignupView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mSignUpFragment).commit();

    }

    private void showFillInforUserView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mFillFullInforUserFragment).commit();

    }

    public void moveIntent(int key, Class<?> cls) {

        Intent mIntent = new Intent(LoginSignupActivity.this, cls);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mIntent.putExtra(Constants.FIRST_SETUP_SCREEN, key);
        startActivity(mIntent);
        finish();
    }

    public void setPrgDialog(String text) {
        prgDialog.setMessage(text);
        prgDialog.show();
    }

    @Override
    public void onViewClick(String tag) {
        switch (tag) {
            case Constants.SIGNUP_FRAGMENT:
                showSignupView();
                break;
            case Constants.LOGIN_FRAGMENT:
                showLoginView();
                break;
            case Constants.LOGIN_FACEBOOK:
                mLoginButtonFb.performClick();
                break;
            case Constants.LOGIN_GOOGLE:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from Facebook
        mCallbackManagerFb.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        getUserInfoFromGooglePlus(requestCode, data);
    }

    @Override
    public void onViewClick(String tag, Object object) {
        mUser = (UserObj) object;
        switch (tag) {
            case Constants.LOGIN_BUTTON:
                // hideKeyBoard();
                setPrgDialog("Login...");
                login(mUser);
                break;

            case Constants.SIGNUP_BUTTON:
                // hideKeyBoard();
                setPrgDialog("Register...");
                signUp(mUser);
                break;

            case Constants.LOGIN_SOCIAL:
                // hideKeyBoard();
                setPrgDialog("Login...");
                loginSocial(mUser);
                break;
        }
    }

    /**
     * @param user to  LOGIN
     */
    public void login(UserObj user) {
        ObservableManager.login(user).subscribe(new Subscriber<LoginSignupResponse>() {
            @Override
            public void onCompleted() {
                AppUtils.logE("LOGIN COMPLETE");
            }

            @Override
            public void onError(Throwable e) {
                AppUtils.logE("login FAILED " + e.getLocalizedMessage());
            }

            @Override
            public void onNext(LoginSignupResponse response) {
                prgDialog.hide();
                switch (response.getStatus()) {
                    case LOGIN_ERROR:
                        AppUtils.showShortToast(LoginSignupActivity.this, response.getErrors());
                        break;
                    case SUCCESS:
                        Constants.TOKEN = response.getmToken();
                        PrefUtils.getInstance(getApplicationContext()).set(PrefUtils.SALON_ID, response.getSalonId());
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, MainActivity.class);
                        break;
                    case LOGED:
                        AppUtils.showShortToast(LoginSignupActivity.this, "Loged");
                        break;
                    case FIRST_LOGIN:
                        Constants.TOKEN = response.getmToken();
                        PrefUtils.getInstance(getApplicationContext()).set(PrefUtils.SALON_ID, response.getSalonId());
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, FirstSetupAcitivity.class);
                        break;
                }
            }
        });
    }

    /**
     * @param user to  SIGNUP
     */
    public void signUp(UserObj user) {
        ObservableManager.signup(user).subscribe(new Subscriber<LoginSignupResponse>() {
            @Override
            public void onCompleted() {
                AppUtils.logE("SIGNUP COMPLETE");
            }

            @Override
            public void onError(Throwable e) {
                AppUtils.logE("SIGNUP FAILED " + e.getLocalizedMessage());
            }

            @Override
            public void onNext(LoginSignupResponse response) {
                prgDialog.hide();
                switch (response.getStatus()) {
                    case REGISTER_ERROR:
                        AppUtils.showShortToast(LoginSignupActivity.this, response.getErrors());
                        break;
                    case SUCCESS:
                        Constants.TOKEN = response.getmToken();
                        PrefUtils.getInstance(getApplicationContext()).set(PrefUtils.SALON_ID, response.getSalonId());
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, FirstSetupAcitivity.class);
                        break;
                    case LOGED:
                        AppUtils.showShortToast(LoginSignupActivity.this, "Loged");
                        break;
                }
            }
        });
    }

    /**
     * @param user to LOGIN FB or GOOGLE PLUS
     */
    public void loginSocial(UserObj user) {
        ObservableManager.socialLogin(user).subscribe(new Subscriber<LoginSignupResponse>() {
            @Override
            public void onCompleted() {
                AppUtils.logE("loginSocial COMPLETE");
            }

            @Override
            public void onError(Throwable e) {
                AppUtils.logE("loginSocial FAILED " + e.getLocalizedMessage());
            }

            @Override
            public void onNext(LoginSignupResponse response) {
                switch (response.getStatus()) {
                    case SUCCESS:
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, MainActivity.class);
                        break;
                    case LOGED:
                        AppUtils.showShortToast(LoginSignupActivity.this, "Loged");
                        break;
                    case FIRST_LOGIN:
                        Constants.TOKEN = response.getmToken();
                        PrefUtils.getInstance(getApplicationContext()).set(PrefUtils.SALON_ID, response.getSalonId());
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, FirstSetupAcitivity.class);
                        break;
                }
            }
        });
    }

    public void showDialog(String string) {
        DialogNotice dialogNotice = new DialogNotice();
        dialogNotice.showDialog(this, string);
    }

    public UserObj getUser() {
        if (mUser == null) {
            mUser = new UserObj();
        }
        return mUser;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onDestroy() {
        if (prgDialog != null) {
            prgDialog.dismiss();
        }

        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
