package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.R;
import com.namestore.alicenote.models.AddEditClientObj;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.AddEditDelClientResponse;
import com.namestore.alicenote.network.reponse.ViewClientResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.app.DialogFragment;
import android.widget.RelativeLayout.LayoutParams;

import java.util.Calendar;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class AddEditDelClientFragment extends BaseFragment {
    private EditText edFistName;
    private EditText editGender;
    private EditText edLastName;

    private EditText edEmail;
    private EditText edMobilePhone;

    private EditText edAddress;
    private EditText edComment;


    private String textFistName;
    private String textLastName;
    private String textBirthday;
    private String textGender;
    private String textEmail;
    private String textMobilePhone;
    private String textPhone;
    private String textAddress;

    private String textComment;

    private Button mButtonDel;

    private AliceApi mAliceApi;

    private TextView tvBirthday;
    private TextView mTvErrorEmail;
    private AddEditClientObj mAddEditClientObj = new AddEditClientObj();

    LinearLayout mLinearLayout;
    private ClientDetailActivity mClientDetailActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mClientDetailActivity.mToolBar);
        mClientDetailActivity.mToolBar.setTitle("Add Client");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        View view = inflater.inflate(R.layout.fm_add_edit_client, container, false);

        setHasOptionsMenu(true);


        initViews(view);
        initModels();
        return view;


    }

    @Override
    protected void initViews(View view) {
        mAliceApi = ServiceGenerator.creatService(AliceApi.class);
        edFistName = (EditText) view.findViewById(R.id.edFistName);
        edLastName = (EditText) view.findViewById(R.id.edLastName);

        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edMobilePhone = (EditText) view.findViewById(R.id.edMobilePhone);
        edAddress = (EditText) view.findViewById(R.id.edAddress);
        edComment = (EditText) view.findViewById(R.id.edComment);
        editGender = (EditText) view.findViewById(R.id.edGender);

        mButtonDel = (Button) view.findViewById(R.id.btnDel);

        mTvErrorEmail = (TextView) view.findViewById(R.id.mErrorEmail);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.lineEmail);

        tvBirthday = (TextView) view.findViewById(R.id.tvBirthday);


    }

    @Override
    protected void initModels() {
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();


            }
        });
        if (mClientDetailActivity.mKeyCheckClient.equals(Constants.ADD_CLIENT)) {// truong them client
            mButtonDel.setVisibility(View.GONE);// hide del button


        } else {
            searchViewClient();
            mButtonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showDelDialog();
                }
            });


        }


    }

    public void getTextFromEditText() {


        if (edFistName.getText().toString().equals("") ||
                edFistName.getText().toString().equals("This field cannot be blank")) {
            edFistName.setHint("This field cannot be blank");
            edFistName.setHintTextColor(getResources().getColor(R.color.text_error));
            textFistName = "error";

        } else
            textFistName = edFistName.getText().toString();

        if (edLastName.getText().toString().equals("") ||
                edLastName.getText().toString().equals("This field cannot be blank")) {
            edLastName.setHint("This field cannot be blank");
            edLastName.setHintTextColor(getResources().getColor(R.color.text_error));
            textLastName = "error";

        } else
            textLastName = edLastName.getText().toString();
        textGender = editGender.getText().toString();
        textMobilePhone = edMobilePhone.getText().toString();

        textBirthday = tvBirthday.getText().toString();
        textPhone = textMobilePhone;
        textEmail = edEmail.getText().toString();
        if (textEmail.contains("@")) {
            mTvErrorEmail.setText(null);
            mTvErrorEmail.setTextColor(getResources().getColor(R.color.text_background));
            mLinearLayout.getLayoutParams().height = 1;
            mTvErrorEmail.setBackgroundColor(getResources().getColor(R.color.text_background));
        } else {
            mTvErrorEmail.setBackgroundColor(getResources().getColor(R.color.text_error_background));
            mLinearLayout.getLayoutParams().height = 70;
            mTvErrorEmail.setText("Email need to be: example@abc.xxx");
            mTvErrorEmail.setTextColor(getResources().getColor(R.color.text_error));
            textEmail = "error";

        }
        textAddress = edAddress.getText().toString();
        textComment = edComment.getText().toString();

        mAddEditClientObj.setAddress(textAddress);
        mAddEditClientObj.setBirthday(textBirthday);
        mAddEditClientObj.setComment(textComment);
        mAddEditClientObj.setEmail(textEmail);
        mAddEditClientObj.setFirstName(textFistName);
        mAddEditClientObj.setLastName(textLastName);
        mAddEditClientObj.setGender(1);
        mAddEditClientObj.setPhone(textPhone);


    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */

     /*   Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));*/
        //       date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //  if(){}
            tvBirthday.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }
    };


    public void pushInfoClient() {

        mAliceApi.pushInfoClient(mAddEditClientObj, 130).enqueue(new Callback<AddEditDelClientResponse>() {
            @Override
            public void onResponse(Call<AddEditDelClientResponse> call, Response<AddEditDelClientResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), "Add Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        getActivity().finish();
                    }

                } else {
                    Toast.makeText(getContext(), "Add Client Completed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddEditDelClientResponse> call, Throwable t) {

            }
        });

    }

    public void updateInfoClient() {
        mAliceApi.updateInfoClient(mAddEditClientObj, 130, mClientDetailActivity.mId).enqueue(new Callback<AddEditDelClientResponse>() {
            @Override
            public void onResponse(Call<AddEditDelClientResponse> call, Response<AddEditDelClientResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(), "Update Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        getActivity().finish();
                    } else
                        Toast.makeText(getActivity(), "Update Client Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddEditDelClientResponse> call, Throwable t) {

            }
        });

    }

    public void searchViewClient() {

        mAliceApi.searchViewClient(130, mClientDetailActivity.mId).enqueue(new Callback<ViewClientResponse>() {
            @Override
            public void onResponse(Call<ViewClientResponse> call, Response<ViewClientResponse> response) {
                if (response.isSuccessful()) {
                    edFistName.setText(response.body().getData().getClient().getFirstName());
                    edLastName.setText(response.body().getData().getClient().getLastName());
                    tvBirthday.setText(response.body().getData().getClient().getBirthday());
                    edEmail.setText(response.body().getData().getClient().getEmail());
                    edMobilePhone.setText(response.body().getData().getClient().getPhone());
                    edAddress.setText(response.body().getData().getClient().getAddress());
                    edComment.setText(response.body().getData().getClient().getDescription());
                    //      editGender.setText(response.body().data.getGender());


                }
            }

            @Override
            public void onFailure(Call<ViewClientResponse> call, Throwable t) {

                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });


    }

    public void delInfoClient() {
        mAliceApi.delClient(130, mClientDetailActivity.mId).enqueue(new Callback<AddEditDelClientResponse>() {
            @Override
            public void onResponse(Call<AddEditDelClientResponse> call, Response<AddEditDelClientResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), "Delete Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        getActivity().finish();

                    } else
                        Toast.makeText(getActivity(), "Delete Client Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddEditDelClientResponse> call, Throwable t) {

            }
        });


    }

    public void showDelDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Warning !!!");
        builder.setMessage("Are you sure to delete this client");
        builder.setPositiveButton("OK!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                delInfoClient();


            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.create().show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ClientDetailActivity) {
            this.mClientDetailActivity = (ClientDetailActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ClientDetailActivity) {
            this.mClientDetailActivity = (ClientDetailActivity) activity;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_edit_client, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {

            getTextFromEditText();

            if (mClientDetailActivity.mKeyCheckClient.
                    equals(Constants.ADD_CLIENT)) {// truong them client
                if (textEmail.equals("error") || textFistName.equals("error")
                        || textLastName.equals("error")) {
                    Toast.makeText(getActivity(), "Failed , please check again !!!", Toast.LENGTH_SHORT).show();
                } else
                    pushInfoClient();


            } else {//truong update client
                if (textEmail.equals("error") || textFistName.equals("error")
                        || textLastName.equals("error")) {
                    Toast.makeText(getActivity(), "Failed , please check again !!!", Toast.LENGTH_SHORT).show();
                } else
                    updateInfoClient();

            }


            return true;
        }
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
