package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.adapter.ServicesCategoryAdapter;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.models.ServicesCategoryObj;
import com.namestore.alicenote.common.AppUtils;

import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;

import java.util.ArrayList;

/**
 * Created by kienht on 10/26/16.
 */

public class ShopServicesCategoryFragment extends BaseFragment {

    Button mButtonBack;
    Button mButtonNext;
    TextView mTextViewTitle;
    ArrayList<ServicesCategoryObj> servicesCategoryObjArrayList;
    ArrayList<Integer> arrayListData;
    RecyclerView recyclerView;
    ServicesCategoryAdapter servicesCategoryAdapter;
    private FirstSetupAcitivity firstSetupAcitivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_shop_services_category, container, false);
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

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler__services_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_pick_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("What services does\n\"Your Salon\" provide?");
    }

    @Override
    protected void initModels() {
        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);

        servicesCategoryObjArrayList = new ArrayList<>();

        arrayListData = new ArrayList<>();
        arrayListData.add(R.drawable.nail_service);
        arrayListData.add(R.drawable.hair_service);
        arrayListData.add(R.drawable.beauty_service);
        arrayListData.add(R.drawable.commingsoon);

        for (int i = 0; i < arrayListData.size(); i++) {
            ServicesCategoryObj servicesCategoryObj = new ServicesCategoryObj();
            servicesCategoryObj.setImgResId(arrayListData.get(i));
            this.servicesCategoryObjArrayList.add(servicesCategoryObj);
        }
        servicesCategoryAdapter = new ServicesCategoryAdapter(getActivity(), servicesCategoryObjArrayList);

        recyclerView.setAdapter(servicesCategoryAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        switch (position) {
                            case 0:
                                if (mActivity instanceof OnFirstSetupActivityListener) {
                                    ((OnFirstSetupActivityListener) mActivity).nailService();
                                }
                                break;
                            case 1:
                                if (mActivity instanceof OnFirstSetupActivityListener) {
                                    ((OnFirstSetupActivityListener) mActivity).hairService();
                                }
                                break;
                            case 2:
                                AppUtils.showShortToast(getActivity(), "BEAUTY SERVICE");
                                break;
                            case 3:
                                AppUtils.showShortToast(getActivity(), "COMMING SOON SERVICE");
                                break;
                        }

                    }
                })
        );

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showTimeOpenDoorSalon();
                }
                break;
            case R.id.button_next:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).configServices();
                }
                break;

            default:
                break;
        }
        super.onClick(view);
    }

}
