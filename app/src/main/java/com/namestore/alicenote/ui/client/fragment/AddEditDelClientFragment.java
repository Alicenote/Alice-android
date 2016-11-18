package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class AddEditDelClientFragment extends BaseFragment {
    private EditText edFistName;
    private EditText editGender;
    private EditText edLastName;
    private EditText edBirthday;
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

    private AddEditClientObj mAddEditClientObj = new AddEditClientObj();
    private ClientDetailActivity mClientDetailActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        edBirthday = (EditText) view.findViewById(R.id.edBirthday);
        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edMobilePhone = (EditText) view.findViewById(R.id.edMobilePhone);
        edAddress = (EditText) view.findViewById(R.id.edAddress);
        edComment = (EditText) view.findViewById(R.id.edComment);
        editGender = (EditText) view.findViewById(R.id.edGender);
        mButtonDel = (Button) view.findViewById(R.id.btnDel);


    }

    @Override
    protected void initModels() {

        if (mClientDetailActivity.mKeyCheckClient.equals(Constants.ADD_CLIENT)) {// truong them client
            mButtonDel.setVisibility(View.GONE);// hide del button

        } else {//truong update client

            searchViewClient();// set cac truong tren edit
            mButtonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //xoa client nay
                    showDelDialog();
                }
            });


        }


    }

    public void getTextFromEditText() {

        textFistName = edFistName.getText().toString() + " ";
        textLastName = edLastName.getText().toString();
        textGender = editGender.getText().toString();
        textMobilePhone = edMobilePhone.getText().toString();

        textBirthday = edBirthday.getText().toString();
        textPhone = textMobilePhone;
        textEmail = edEmail.getText().toString();
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

    public void pushInfoClient() {
        mAliceApi.pushInfoClient(mAddEditClientObj, 130).enqueue(new Callback<AddEditDelClientResponse>() {
            @Override
            public void onResponse(Call<AddEditDelClientResponse> call, Response<AddEditDelClientResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1){
                        Toast.makeText(getActivity(), "Add Client Completed", Toast.LENGTH_SHORT).show();}

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
                    if (response.body().getStatus() == 1)
                        Toast.makeText(getActivity(), "Update Client Completed", Toast.LENGTH_SHORT).show();
                    else
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
                    edFistName.setText(response.body().getData().getFirstName());
                    edLastName.setText(response.body().data.getLastName());
                    edBirthday.setText(response.body().data.getBirthday());
                    edEmail.setText(response.body().data.getEmail());
                    edMobilePhone.setText(response.body().data.getPhone());
                    edAddress.setText(response.body().data.getAddress());
                    edComment.setText(response.body().data.getDescription());
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
                        Toast.makeText(getActivity(), "Delete Client Completed", Toast.LENGTH_SHORT).show();


                        getActivity().finish();
                    }
                    else
                        Toast.makeText(getActivity(), "Delete Client Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddEditDelClientResponse> call, Throwable t) {

            }
        });

    }

    public void showDelDialog() {
        // Use the Builder class for convenient dialog construction
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

        // Create the AlertDialog object and return it
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            Log.w("viewwwwww","editttttttt");
            getTextFromEditText();

            if (mClientDetailActivity.mKeyCheckClient.
                    equals(Constants.ADD_CLIENT)) {// truong them client
                pushInfoClient();
            } else {//truong update client
                updateInfoClient();
            }

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", 1);
            getActivity().setResult(Activity.RESULT_OK, returnIntent);
            getActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
