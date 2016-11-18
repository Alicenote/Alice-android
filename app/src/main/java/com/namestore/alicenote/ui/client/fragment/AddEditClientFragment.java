package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.database.Contact;
import com.namestore.alicenote.R;
import com.namestore.alicenote.models.AddEditClientObj;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.AddEditClientResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.namestore.alicenote.ui.home.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class AddEditClientFragment extends BaseFragment {
    private EditText edFistName;
    private EditText editGender;
    private EditText edLastName;
    private EditText edBirthday;
    private EditText edEmail;
    private EditText edMobilePhone;
    private EditText edWorkPhone;
    private EditText edStreetAdress;
    private EditText edAddressLine2;
    private EditText edCity;
    private EditText edState;
    private EditText edCountry;
    private EditText edComment;


    private String textFistName;
    private String textLastName;
    private String textName;
    private String textBirthday;
    private String textGender;
    private String textEmail;
    private String textMobilePhone;
    private String textWorkPhone;
    private String textPhone;
    private String textAddress;
    private String textStreetAdress;
    private String textAddressLine2;
    private String textCity;
    private String textState;
    private String textCountry;
    private String textComment;

    private Button mButtonSave;

    private AliceApi mAliceApi;

    private AddEditClientObj mAddEditClientObj = new AddEditClientObj();
    private int mResume;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_add_edit_client, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
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
        edWorkPhone = (EditText) view.findViewById(R.id.edWorkPhone);
        edStreetAdress = (EditText) view.findViewById(R.id.edStreetAdress);
        edAddressLine2 = (EditText) view.findViewById(R.id.edAddressLine2);
        edCity = (EditText) view.findViewById(R.id.edCity);
        edState = (EditText) view.findViewById(R.id.edState);
        edCountry = (EditText) view.findViewById(R.id.edCountry);
        edComment = (EditText) view.findViewById(R.id.edComment);
        editGender = (EditText) view.findViewById(R.id.edGender);
        mButtonSave = (Button) view.findViewById(R.id.btnSave);
    }

    @Override
    protected void initModels() {

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromEditText();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",1);
                getActivity().setResult(Activity.RESULT_OK,returnIntent);
                getActivity().finish();
            }
        });

    }

    public void getTextFromEditText() {

        textFistName = edFistName.getText().toString() + " ";
        textLastName = edLastName.getText().toString();
        textGender = editGender.getText().toString();
        textMobilePhone = edMobilePhone.getText().toString();
        if (edWorkPhone.getText().toString() == null) {
            textWorkPhone = edWorkPhone.getText().toString();
        } else {
            textWorkPhone = "\n Work Phone : " + edWorkPhone.getText().toString();
        }

        textStreetAdress = edStreetAdress.getText().toString() + " ,";
        if (edAddressLine2.getText().toString() == null) {
            textAddressLine2 = edAddressLine2.getText().toString();
        } else {
            textAddressLine2 = edAddressLine2.getText().toString() + " ,";
        }

        textCity = edCity.getText().toString();
        textState = edState.getText().toString();
        textCountry = edCountry.getText().toString();

        textName = textFistName + textLastName;
        textBirthday = edBirthday.getText().toString();
        textPhone = textMobilePhone + textWorkPhone;
        textEmail = edEmail.getText().toString();
        textAddress = textStreetAdress + textAddressLine2 + textState + textCity + textCountry;
        textComment = edComment.getText().toString();

        mAddEditClientObj.setAddress(textAddress);
        mAddEditClientObj.setBirthday(textBirthday);
        mAddEditClientObj.setComment(textComment);
        mAddEditClientObj.setEmail(textEmail);
        mAddEditClientObj.setFirstName(textFistName);
        mAddEditClientObj.setLastName(textLastName);
        mAddEditClientObj.setGender(1);
        mAddEditClientObj.setPhone(textPhone);

        pushInfoClient();

    }

    public void pushInfoClient() {
        mAliceApi.pushInfoClient(mAddEditClientObj, 89).enqueue(new Callback<AddEditClientResponse>() {
            @Override
            public void onResponse(Call<AddEditClientResponse> call, Response<AddEditClientResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1)
                        Toast.makeText(getActivity(), "Add Client Completed", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Add Client Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddEditClientResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                getTextFromEditText();
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
