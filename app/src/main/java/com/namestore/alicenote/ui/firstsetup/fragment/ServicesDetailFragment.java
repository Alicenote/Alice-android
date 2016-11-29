package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.network.BaseResponse;
import com.namestore.alicenote.ui.firstsetup.adapter.AllServicesAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.models.SubServicesObj;

import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kienht on 10/26/16.
 */

public class ServicesDetailFragment extends BaseFragment implements AllServicesAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    TextView mTextViewTitle;
    Button mButtonBack;
    Button mButtonNext;
    private FirstSetupAcitivity firstSetupAcitivity;
    OnFragmentInteractionListener listener;
    AllServicesAdapter allServicesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_services_detail, container, false);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.list_services);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_config_name_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("Prices and duarations\nof services at \"Your Salon\"");
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
    }

    @Override
    protected void initModels() {
        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        updateRecyclerView(new ArrayList<SubServicesObj>());
    }

    public void updateRecyclerView(ArrayList<SubServicesObj> arrayList) {
        String durationTimeService[] = getResources().getStringArray(R.array.duration_time_service);

        allServicesAdapter = new AllServicesAdapter(this.getActivity(), arrayList, durationTimeService, this);

        recyclerView.setAdapter(allServicesAdapter);
    }

    public void swapDataRecyclerView(ArrayList<SubServicesObj> arrayList) {
        allServicesAdapter.swapData(arrayList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showShopServicesCategoryFragment();
                }
                break;
            case R.id.button_next:
                listener.onViewClick(Constants.DASHBOARD_SCREEN);
                break;

            default:
                break;
        }
        super.onClick(view);
    }

    @Override
    public void onClickSpinnerDurationTime(int position, int positionSpinner) {
        AppUtils.logE("position " + position + "||" + "positionSpinner " + positionSpinner);
        firstSetupAcitivity.serviceArrayList.get(position >= firstSetupAcitivity.sizeNailArrayList ? Constants.HAIR : Constants.NAIL)
                .getSubServices().get(position >= firstSetupAcitivity.sizeNailArrayList ? position - firstSetupAcitivity.sizeNailArrayList : position)
                .setDuration(positionSpinner);
    }

    @Override
    public void onClickEdittexPrice(int position, float price) {
        AppUtils.logE("position " + position + "||" + "price " + price);
        firstSetupAcitivity.serviceArrayList.get(position >= firstSetupAcitivity.sizeNailArrayList ? Constants.HAIR : Constants.NAIL)
                .getSubServices().get(position >= firstSetupAcitivity.sizeNailArrayList ? position - firstSetupAcitivity.sizeNailArrayList : position)
                .setPrice(price);
    }

    @Override
    public void onClickCheckBoxStaff(int position, boolean isWork) {
        AppUtils.logE("position " + position + "||" + "price " + isWork);
        firstSetupAcitivity.serviceArrayList.get(position >= firstSetupAcitivity.sizeNailArrayList ? Constants.HAIR : Constants.NAIL)
                .getSubServices().get(position >= firstSetupAcitivity.sizeNailArrayList ? position - firstSetupAcitivity.sizeNailArrayList : position)
                .setWork(isWork);
    }

    @Override
    public void onClickItem(int position) {
        recyclerView.scrollToPosition(position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }
        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


}
