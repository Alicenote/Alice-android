package com.namestore.alicenote.ui.signinup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.signinup.LoginSignupActivity;
import com.namestore.alicenote.ui.start.StartActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.dialog.DialogNotice;
import com.namestore.alicenote.models.UserObj;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;


/**
 * Created by kienht on 10/24/16.
 */

public class LoginFragment extends BaseFragment {

    TextView mTextViewSignup;
    Button mButtonLogin;
    EditText mEditTexEmail;
    EditText mEditTexPassword;
    TextView mTextViewForgotPass;
    TextView mTextViewReportError;
    TextView mTextViewIncorrect;
    Button mButtonFb;
    Button mButtonGoogleP;
    TextView mTextViewContact;
    SwitchCompat switchCompatLogin;
    private LoginSignupActivity loginSignupActivity;
    LinearLayout linearLayout;
    UserObj mUser = new UserObj();
    TextInputLayout mInputEmail;
    TextInputLayout mInputPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_login, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
    }

    @Override
    protected void initViews(View view) {
        mTextViewSignup = (TextView) view.findViewById(R.id.textview_signup);
        mButtonLogin = (Button) view.findViewById(R.id.button_login);
        mEditTexEmail = (EditText) view.findViewById(R.id.editText_login_username);
        mEditTexPassword = (EditText) view.findViewById(R.id.editText_login_password);
        mTextViewForgotPass = (TextView) view.findViewById(R.id.textview_forgot_pass);
        mTextViewReportError = (TextView) view.findViewById(R.id.textview_report_error_login);
        mButtonFb = (Button) view.findViewById(R.id.button_facebook_login);
        mButtonGoogleP = (Button) view.findViewById(R.id.button_google_login);
        mTextViewContact = (TextView) view.findViewById(R.id.textview_contact);
        switchCompatLogin = (SwitchCompat) view.findViewById(R.id.switch_compat_login);
        linearLayout = (LinearLayout) view.findViewById(R.id.frgment_login);
        mTextViewIncorrect = (TextView) view.findViewById(R.id.textview_incorrect_login);
        mTextViewIncorrect.setVisibility(View.INVISIBLE);
        mInputEmail = (TextInputLayout) view.findViewById(R.id.input_email);
        mInputPassword = (TextInputLayout) view.findViewById(R.id.input_password);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusableInTouchMode(true);
        mTextViewSignup.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mTextViewForgotPass.setOnClickListener(this);
        mTextViewReportError.setOnClickListener(this);
        mButtonFb.setOnClickListener(this);
        mButtonGoogleP.setOnClickListener(this);
        mTextViewContact.setOnClickListener(this);
        ViewUtils.configEditText(getActivity(), mEditTexEmail, linearLayout, "", R.drawable.icon_email, mTextViewIncorrect);
        ViewUtils.configEditText(getActivity(), mEditTexPassword, linearLayout, "", R.drawable.icon_password, mTextViewIncorrect);
    }

    public void setTextViewIncorrect(String text) {
        mTextViewIncorrect.setVisibility(View.VISIBLE);
        mTextViewIncorrect.setText(text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof LoginSignupActivity) {
            this.loginSignupActivity = (LoginSignupActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof StartActivity) {
            this.loginSignupActivity = (LoginSignupActivity) activity;
        }
    }

    private void showDialog(String string) {
        DialogNotice dialogNotice = new DialogNotice();
        dialogNotice.showDialog(getActivity(), string);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_login:
                mUser.email = mEditTexEmail.getText().toString();
                mUser.passwordHash = mEditTexPassword.getText().toString();

                if (TextUtils.isEmpty(mUser.email) || TextUtils.isEmpty(mUser.passwordHash)) {
                    showDialog("Please filling in the blanks");
                } else if (AppUtils.checkEmail(mUser.email)) {
                    if (mUser.passwordHash.length() < 8) {
                        showDialog("Password phai lon hon 8 ky tu");
                    } else {
                        onFragmentInteractionListener.onViewClick(Constants.LOGIN_BUTTON, mUser);
                    }
                } else {
                    showDialog("Email ko dung dinh dang");
                }
                break;

            case R.id.textview_forgot_pass:
                AppUtils.showShortToast(getActivity(), Constants.FORGOT_PASS);
                break;

            case R.id.textview_report_error_login:
                AppUtils.showShortToast(getActivity(), Constants.REPORT_ERROR);
                break;

            case R.id.button_facebook_login:
                onFragmentInteractionListener.onViewClick(Constants.LOGIN_FACEBOOK);
                break;

            case R.id.button_google_login:
                onFragmentInteractionListener.onViewClick(Constants.LOGIN_GOOGLE);
                break;

            case R.id.textview_contact:
                AppUtils.showShortToast(getActivity(), Constants.CONTACT_ALICE);
                break;

            case R.id.textview_signup:
                onFragmentInteractionListener.onViewClick(Constants.SIGNUP_FRAGMENT);
                break;

            default:
                break;
        }
    }
}
