package com.namestore.alicenote.ui.service.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ClientViewResponse;
import com.namestore.alicenote.network.reponse.ServiceEditDelResponse;
import com.namestore.alicenote.network.reponse.ServiceViewResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.namestore.alicenote.ui.service.ServiceDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhocnhinho on 24/11/2016.
 */

public class ViewEditDelSeviceFragment extends BaseFragment {

    EditText mEdSvName;
    EditText mEdSvPrice;
    EditText mEdSvOldPrice;
    EditText mEdSvGroup;
    EditText mEdSvDuration;
    EditText mEdSvDescription;
    Button mBtnAllStaffs;
    Button mBtnStatus;
    Button mBtnSvDel;
    Button mBtnSvEdit;
    RecyclerView mRecyclerServiceView;
    private AliceApi mAliceApi;
    ServiceDetailActivity mServiceDetailActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fm_view_edit_del_service, container, false);

        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {
        mEdSvName = (EditText) view.findViewById(R.id.edSvName);
        mEdSvPrice = (EditText) view.findViewById(R.id.edSvPrice);
        mEdSvOldPrice = (EditText) view.findViewById(R.id.edSvOldPrice);
        mEdSvGroup = (EditText) view.findViewById(R.id.edSvGroup);
        mEdSvDuration = (EditText) view.findViewById(R.id.edSvDuration);
        mEdSvDescription = (EditText) view.findViewById(R.id.edSvDescription);
        mBtnAllStaffs = (Button) view.findViewById(R.id.btnAllStaffs);
        mBtnStatus = (Button) view.findViewById(R.id.btnStatus);
        mBtnSvDel = (Button) view.findViewById(R.id.btnSvDel);
        mBtnSvEdit = (Button) view.findViewById(R.id.btnSvEdit);
        mRecyclerServiceView = (RecyclerView) view.findViewById(R.id.recyclerServiceView);

        mAliceApi = ServiceGenerator.creatService(AliceApi.class);
    }

    @Override
    protected void initModels() {

    }


    public void searchViewService() {
     //   prgDialog.show();
        mAliceApi.searchViewService(189, mServiceDetailActivity.mId).enqueue(new Callback<ServiceViewResponse>() {
            @Override
            public void onResponse(Call<ServiceViewResponse> call, Response<ServiceViewResponse> response) {
                if (response.isSuccessful()) {
                    mEdSvName.setText(response.body().getData().getName());
                    mEdSvPrice.setText(response.body().getData().getPrice());
                    mEdSvOldPrice.setText(response.body().getData().getOldPrice());
                    mEdSvGroup.setText(response.body().getData().getGroupId());
                    mEdSvDuration.setText(response.body().getData().getDuration());
                    mEdSvDescription.setText(response.body().getData().getDescription());


           //         prgDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ServiceViewResponse> call, Throwable t) {

                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ServiceDetailActivity) {
            this.mServiceDetailActivity = (ServiceDetailActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ServiceDetailActivity) {
            this.mServiceDetailActivity = (ServiceDetailActivity) activity;
        }
    }


}
