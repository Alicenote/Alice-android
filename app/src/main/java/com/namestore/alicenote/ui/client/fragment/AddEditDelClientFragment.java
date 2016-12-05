package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.R;
import com.namestore.alicenote.models.AddEditClientObj;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ClientAddEditDelResponse;
import com.namestore.alicenote.network.reponse.ClientViewResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class AddEditDelClientFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
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

    private String textEmail;
    private String textMobilePhone;
    private String textPhone;
    private String textAddress;

    private String textComment;

    private Button mButtonDel;

    private AliceApi mAliceApi;

    private int mIntGender;

    private TextView tvBirthday;
    private TextView mTvErrorEmail;
    private AddEditClientObj mAddEditClientObj = new AddEditClientObj();

    private LinearLayout mLinearLayout;
    private ClientDetailActivity mClientDetailActivity;

    private ProgressDialog prgDialog;
    Spinner mSpinnerGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mClientDetailActivity.mToolBar);

        if (mClientDetailActivity.mKeyCheckClient.
                equals(Constants.ADD_CLIENT))
            mClientDetailActivity.mToolBar.setTitle("Add Client");
        else
            mClientDetailActivity.mToolBar.setTitle("Edit Client");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        mButtonDel = (Button) view.findViewById(R.id.btnDel);

        mTvErrorEmail = (TextView) view.findViewById(R.id.mErrorEmail);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.lineEmail);

        tvBirthday = (TextView) view.findViewById(R.id.tvBirthday);

        mSpinnerGender = (Spinner) view.findViewById(R.id.spinner_edGender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerGender.setAdapter(adapter);
        mSpinnerGender.setOnItemSelectedListener(this);
        prgDialog = new ProgressDialog(getContext());
        prgDialog.setCancelable(false);


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // 0 la male
        //1 la female
        //2 la other
        mIntGender = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        mAddEditClientObj.setGender(mIntGender);
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
        prgDialog.show();
        mAliceApi.pushInfoClient(mAddEditClientObj, 130).enqueue(new Callback<ClientAddEditDelResponse>() {
            @Override
            public void onResponse(Call<ClientAddEditDelResponse> call, Response<ClientAddEditDelResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), "Add Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        prgDialog.hide();
                        getActivity().finish();
                    }

                } else {
                    Toast.makeText(getContext(), "Add Client Completed", Toast.LENGTH_SHORT).show();
                    prgDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ClientAddEditDelResponse> call, Throwable t) {

            }
        });

    }

    public void updateInfoClient() {
        prgDialog.show();
        mAliceApi.updateInfoClient(mAddEditClientObj, 130, mClientDetailActivity.mId).enqueue(new Callback<ClientAddEditDelResponse>() {
            @Override
            public void onResponse(Call<ClientAddEditDelResponse> call, Response<ClientAddEditDelResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(), "Update Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        prgDialog.hide();
                        getActivity().finish();
                    } else
                        Toast.makeText(getActivity(), "Update Client Fail", Toast.LENGTH_SHORT).show();
                    prgDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ClientAddEditDelResponse> call, Throwable t) {

            }
        });

    }


    public void searchViewClient() {
        prgDialog.show();
        mAliceApi.searchViewClient(130, mClientDetailActivity.mId).enqueue(new Callback<ClientViewResponse>() {
            @Override
            public void onResponse(Call<ClientViewResponse> call, Response<ClientViewResponse> response) {
                if (response.isSuccessful()) {
                    edFistName.setText(response.body().getData().getClient().getFirstName());
                    edLastName.setText(response.body().getData().getClient().getLastName());
                    tvBirthday.setText(response.body().getData().getClient().getBirthday());
                    edEmail.setText(response.body().getData().getClient().getEmail());
                    edMobilePhone.setText(response.body().getData().getClient().getPhone());
                    edAddress.setText(response.body().getData().getClient().getAddress());
                    edComment.setText(response.body().getData().getClient().getDescription());
                    mSpinnerGender.setSelection(response.body().getData().getClient().getGender());

                    prgDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ClientViewResponse> call, Throwable t) {

                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });


    }

    public void delInfoClient() {
        prgDialog.show();
        mAliceApi.delClient(130, mClientDetailActivity.mId).enqueue(new Callback<ClientAddEditDelResponse>() {
            @Override
            public void onResponse(Call<ClientAddEditDelResponse> call, Response<ClientAddEditDelResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), "Delete Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        prgDialog.hide();
                        getActivity().finish();

                    } else
                        Toast.makeText(getActivity(), "Delete Client Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientAddEditDelResponse> call, Throwable t) {

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
                    equals(Constants.ADD_CLIENT)) {
                if (textEmail.equals("error") || textFistName.equals("error")
                        || textLastName.equals("error")) {
                    Toast.makeText(getActivity(), "Add Failed , please check again !!!", Toast.LENGTH_SHORT).show();
                } else
                    pushInfoClient();


            } else {//truong update client
                if (textEmail.equals("error") || textFistName.equals("error")
                        || textLastName.equals("error")) {
                    Toast.makeText(getActivity(), "Update Failed, please check again !!!", Toast.LENGTH_SHORT).show();
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
