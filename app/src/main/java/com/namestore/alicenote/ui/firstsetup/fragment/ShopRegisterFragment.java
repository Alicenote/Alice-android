package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.network.request.FirstSetupRequest;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;

import java.util.ArrayList;

/**
 * Created by kienht on 10/31/16.
 */

public class ShopRegisterFragment extends BaseFragment {

    public static final int BSN_TYPE = 0;
    public static final int BSN_STATE = 1;

    Button mButtonBack;
    Button mButtonNext;
    TextView mTextViewTitle;
    EditText mEditTexBsnName;
    EditText mEditTexBsnCity;
    EditText mEditTexBsnPostCode;
    EditText mEditTexBsnAddress;
    Spinner mSpinnerBsnState;
    Spinner mSpinnerBsnType;
    LinearLayout linearLayout;
    private FirstSetupAcitivity firstSetupAcitivity;
    ArrayList<String> arrayListDemo = new ArrayList<>();
    private String bsnName;
    private int bsnType = 0;
    private int bsnState = 0;
    private String bsnCity;
    private String bsnPostcode;
    private String bsnAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_shop_register, container, false);
        initViews(view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
    }

    @Override
    protected void initViews(View view) {
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);

        mTextViewTitle = (TextView) view.findViewById(R.id.tile_profile_setup).findViewById(R.id.title_first_setup);

        mEditTexBsnName = (EditText) view.findViewById(R.id.editText_bussiness_name);
        mEditTexBsnCity = (EditText) view.findViewById(R.id.editText_bussiness_city);
        mEditTexBsnAddress = (EditText) view.findViewById(R.id.editText_bussiness_address);
        mEditTexBsnPostCode = (EditText) view.findViewById(R.id.editText_bussiness_postcode);

        mSpinnerBsnState = (Spinner) view.findViewById(R.id.spinner_bussiness_state);
        mSpinnerBsnType = (Spinner) view.findViewById(R.id.spinner_bussiness_type);

        linearLayout = (LinearLayout) view.findViewById(R.id.frgment_setup_info_salon);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonBack.setVisibility(View.INVISIBLE);
        mButtonNext.setOnClickListener(this);
        mTextViewTitle.setText("Welcome to AliceNote");
        ViewUtils.configEditText(getActivity(), mEditTexBsnName, linearLayout, "Bussiness Name", 0, null);
        ViewUtils.configEditText(getActivity(), mEditTexBsnCity, linearLayout, "City", 0, null);
        ViewUtils.configEditText(getActivity(), mEditTexBsnPostCode, linearLayout, "Post Code", 0, null);
        ViewUtils.configEditText(getActivity(), mEditTexBsnAddress, linearLayout, "Address", 0, null);

        configSpinnerBsnType(arrayListDemo);
        configSpinnerBsnState(arrayListDemo);

        getItemIdFromSpinner(mSpinnerBsnType, BSN_TYPE);
        getItemIdFromSpinner(mSpinnerBsnState, BSN_STATE);
    }

    public void configSpinnerBsnType(ArrayList<String> arrayList) {
        ViewUtils.configSpinner(getActivity(), arrayList, mSpinnerBsnType);
    }

    public void configSpinnerBsnState(ArrayList<String> arrayList) {
        ViewUtils.configSpinner(getActivity(), arrayList, mSpinnerBsnState);
    }

    private void getItemIdFromSpinner(Spinner spinner, final int tag) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int id, long l) {
                switch (tag) {
                    case BSN_TYPE:
                        bsnType = id;
                        break;
                    case BSN_STATE:
                        bsnState = id;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public boolean checkEmpty(String... strings) {
        boolean isEmpty = false;
        for (String string : strings) {
            if ((TextUtils.isEmpty(string))) {
                isEmpty = true;
                return isEmpty;
            }
        }
        return isEmpty;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_next:
                if (firstSetupAcitivity != null) {
                    bsnName = mEditTexBsnName.getText().toString();
                    bsnCity = mEditTexBsnCity.getText().toString();
                    bsnPostcode = mEditTexBsnPostCode.getText().toString();
                    bsnAddress = mEditTexBsnAddress.getText().toString();

                    //Thêm infor data salon vào firstSetupRequest Obj
//                    FirstSetupRequest.Infor infor = new FirstSetupRequest().new Infor(bsnName, bsnType, bsnState,
//                            bsnCity, bsnPostcode, bsnAddress);
//
//                    firstSetupAcitivity.firstSetupRequest.setInfor(infor);
//
//                    if (mActivity instanceof OnFirstSetupActivityListener) {
//                        ((OnFirstSetupActivityListener) mActivity).showWorkingDayFragment();
//                    }

                    if (checkEmpty(bsnName, bsnCity, bsnPostcode, bsnAddress) || bsnType == 0 || bsnState == 0) {
                        AppUtils.showNoticeDialog(getActivity(), "Please filling in the blanks");
                    } else {
                        FirstSetupRequest.Infor infor = firstSetupAcitivity.firstSetupRequest.new Infor(bsnName, bsnType, bsnState,
                                bsnCity, bsnPostcode, bsnAddress);

                        firstSetupAcitivity.firstSetupRequest.setInfor(infor);

                        if (mActivity instanceof OnFirstSetupActivityListener) {
                            ((OnFirstSetupActivityListener) mActivity).showWorkingDayFragment();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }
    }
}
