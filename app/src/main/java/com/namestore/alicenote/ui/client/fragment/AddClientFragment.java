package com.namestore.alicenote.ui.client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.namestore.alicenote.database.Contact;
import com.namestore.alicenote.database.DatabaseHandler;
import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.BaseFragment;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class AddClientFragment extends BaseFragment {
    private EditText edFistName, edLastName, edBirthday, edEmail, edMobilePhone, edWorkPhone,
            edStreetAdress, edAddressLine2, edCity, edState, edCountry, edComment;
    private String textFistName, textLastName, textName, textBirthday, textEmail, textMobilePhone, textWorkPhone,
            textPhone, textAddress, textStreetAdress, textAddressLine2, textCity, textState, textCountry, textComment;
    private DatabaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_add_client, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        initViews(view);
        initModels();
        return view;


    }

    @Override
    protected void initViews(View view) {
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
    }

    @Override
    protected void initModels() {



    }
    public void getTextFromEditText() {
        db = new DatabaseHandler(getContext());
        textFistName = edFistName.getText().toString() + " ";
        textLastName = edLastName.getText().toString();

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

        db.addContact(new Contact(textName, textBirthday, textPhone, textEmail, textAddress, textComment));

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
