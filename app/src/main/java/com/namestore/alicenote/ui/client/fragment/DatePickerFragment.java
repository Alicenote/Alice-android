package com.namestore.alicenote.ui.client.fragment;

/**
 * Created by nhocnhinho on 20/11/2016.
 */

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    OnDateSetListener ondateSet;

    public DatePickerFragment() {
    }

    public void setCallBack(OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    private int year, month, day;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calender = Calendar.getInstance();

        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }
}