package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.namestore.alicenote.R;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.ui.BaseFragment;

import com.namestore.alicenote.ui.home.MainActivity;

/**
 * Created by nhocnhinho on 23/11/2016.
 */

public class ServiceFragment  extends BaseFragment {


    private AliceApi mAliceApi;
    private MainActivity mMainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_service, container, false);


        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {
       // mAliceApi = ServiceGenerator.creatService(AliceApi.class);


    }

    @Override
    protected void initModels() {



    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mMainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            this.mMainActivity = (MainActivity) activity;
        }
    }
}
