package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.R;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ClientResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.models.ClientObj;

import com.namestore.alicenote.ui.home.MainActivity;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class ClientFragment extends BaseFragment {



    private AliceApi mAliceApi;


   private MainActivity mMainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_client, container, false);


        initViews(view);
        initModels();
        return view;
    }


    @Override
    protected void initViews(View view) {

        mAliceApi = ServiceGenerator.creatService(AliceApi.class);

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
