package com.namestore.alicenote.ui.signinup;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.reponse.LoginSignupResponse;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.common.dialog.DialogNotice;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.models.UserObj;
import com.namestore.alicenote.common.AppUtils;

import com.namestore.alicenote.ui.signinup.fragment.FillFullInforUserFragment;
import com.namestore.alicenote.ui.signinup.fragment.LoginFragment;
import com.namestore.alicenote.ui.signinup.fragment.SignUpFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private AliceApi aliceApi;
    public UserObj mUser = new UserObj();
    private LoginButton mLoginButtonFb;
    private ProgressDialog prgDialog;
    private GoogleApiClient mGoogleApiClient;
    AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                mAccessToken = AccessToken.getCurrentAccessToken();
            }
        });

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

        aliceApi = ServiceGenerator.creatService(AliceApi.class);

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
        mLoginButtonFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginButtonFb.setClickable(false);
                mAccessTokenTracker = new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                        AccessToken mAccessToken = newToken;
                    }
                };
                FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
                    @Override
                    public void onInitialized() {
                        mLoginButtonFb.registerCallback(mCallbackManagerFb, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                //AccessToken mAccessToken = loginResult.getAccessToken();
                                // PrefUtils.getInstance(LoginScreen.this).set(PrefUtils.KEY_ACCESS_TOKEN_FB, mAccessToken.getToken());
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
                        });
                    }
                });

                mAccessTokenTracker.startTracking();
            }
        });

    }

    /**
     * @get user info after login fb success
     */
    private void getUserInfoFromFb() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(
                    JSONObject object, GraphResponse response) {
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
                    mUser.passwordHash = "";
                    mUser.telephone = "";
                    prgDialog.dismiss();
                    showFillInforUserView();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                //Uri personPhoto = acct.getPhotoUrl();

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
                hideKeyBoard();
                setPrgDialog("Loging");
                login(mUser);
                break;

            case Constants.SIGNUP_BUTTON:
                hideKeyBoard();
                setPrgDialog("Register");
                signUp(mUser);
                break;

            case Constants.LOGIN_SOCIAL:
                hideKeyBoard();
                setPrgDialog("Loging");
                loginSocial(mUser);
                break;
        }
    }

    /**
     * API LOGIN
     */
    public void login(UserObj user) {
        aliceApi.login(user).enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                AppUtils.logE("OK LOGIN || STATUS: " + response.body().getStatus() + "|TOKEN|" + response.body().getToken());
                if (response.isSuccessful()) {
                    prgDialog.hide();
                    switch (response.body().getStatus()) {
                        case LOGIN_ERROR:

                            break;
                        case SUCCESS:
                            moveIntent(Constants.KEY_SETUP_INFO_SALON, MainActivity.class);
                            break;
                        case LOGED:
                            AppUtils.showShortToast(LoginSignupActivity.this, "ban da dang nhap");
                            break;
                        case FIRST_LOGIN:
                            moveIntent(Constants.KEY_SETUP_INFO_SALON, FirstSetupAcitivity.class);
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });
    }

    /**
     * API SIGNUP
     */
    public void signUp(UserObj user) {
        aliceApi.signup(user).enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                AppUtils.logE("OK SIGNUP || STATUS: " + response.body().getStatus());
                prgDialog.hide();
                if (response.isSuccessful()) {
                    switch (response.body().getStatus()) {
                        case REGISTER_ERROR:

                            break;

                        case SUCCESS:
                            moveIntent(Constants.KEY_SETUP_INFO_SALON, FirstSetupAcitivity.class);
                            break;

                        case LOGED:
                            moveIntent(Constants.KEY_SETUP_INFO_SALON, MainActivity.class);
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });
    }

    /**
     * API LOGIN FB or GOOGLE PLUS
     */
    public void loginSocial(UserObj user) {
        aliceApi.socialLogin(user).enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                AppUtils.logE("OK LOGINSOCIAL || STATUS: " + response.body().getStatus());
                switch (response.body().getStatus()) {
                    case SUCCESS:
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, MainActivity.class);
                        break;

                    case LOGED:
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, MainActivity.class);
                        break;

                    case FIRST_LOGIN:
                        moveIntent(Constants.KEY_SETUP_INFO_SALON, FirstSetupAcitivity.class);
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
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
