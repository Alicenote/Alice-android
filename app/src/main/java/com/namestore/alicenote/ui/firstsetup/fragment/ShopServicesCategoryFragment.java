package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.models.SubServicesObj;
import com.namestore.alicenote.network.request.FirstSetupRequest;
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
    ArrayList<ServicesCategoryObj> servicesCategoryObjArrayList = new ArrayList<>();
    ArrayList<Pair<String, Integer>> arrayListData = new ArrayList<>();
    ArrayList<String> arrayListDemo = new ArrayList<>();
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
        updateRecyclerView(arrayListDemo);

    }

    public void updateRecyclerView(ArrayList<String> arrayList) {
        arrayListData.add(new Pair<String, Integer>("NAIL", R.drawable.nail_service));
        arrayListData.add(new Pair<String, Integer>("HAIR", R.drawable.hair_service));
        arrayListData.add(new Pair<String, Integer>("BEAUTY", R.drawable.beauty_service));

        for (int i = 0; i < arrayListData.size(); i++) {
            ServicesCategoryObj servicesCategoryObj = new ServicesCategoryObj();
            servicesCategoryObj.setNameService(arrayListData.get(i).first);
            servicesCategoryObj.setImgResId(arrayListData.get(i).second);
            this.servicesCategoryObjArrayList.add(servicesCategoryObj);
        }
        servicesCategoryAdapter = new ServicesCategoryAdapter(getActivity(), servicesCategoryObjArrayList);

        recyclerView.setAdapter(servicesCategoryAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), (view, position) -> {
                    switch (position) {
                        case Constants.NAIL:
                            if (mActivity instanceof OnFirstSetupActivityListener) {
                                ((OnFirstSetupActivityListener) mActivity).showNailServiceFragment();
                            }
                            break;
                        case Constants.HAIR:
                            if (mActivity instanceof OnFirstSetupActivityListener) {
                                ((OnFirstSetupActivityListener) mActivity).showHairServiceFragment();
                            }
                            break;
                        case 2:
                            AppUtils.showShortToast(getActivity(), "BEAUTY SERVICE");
                            break;
                    }

                })
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showWorkingDayFragment();
                }
                break;
            case R.id.button_next:

                if (firstSetupAcitivity.serviceArrayList.get(Constants.NAIL) == null ||
                        firstSetupAcitivity.serviceArrayList.get(Constants.HAIR) == null) {
                    AppUtils.showNoticeDialog(getActivity(), "Plz config service");
                    return;
                }

                firstSetupAcitivity.firstSetupRequest.setServices(firstSetupAcitivity.serviceArrayList);

                //update List All Sub Services 1 lần duy nhất
                if (firstSetupAcitivity.subServicesObjArrayList.size() == 0) {
                    ArrayList<FirstSetupRequest.Service.SubServices> subServicesTotal = new ArrayList<>();
                    subServicesTotal.addAll(0, firstSetupAcitivity.serviceArrayList.get(Constants.NAIL).getSubServices());
                    subServicesTotal.addAll(firstSetupAcitivity.sizeNailArrayList, firstSetupAcitivity.serviceArrayList.get(Constants.HAIR).getSubServices());
                    for (int i = 0; i < subServicesTotal.size(); i++) {
                        SubServicesObj[] subServicesObjs = new SubServicesObj[subServicesTotal.size()];
                        subServicesObjs[i] = new SubServicesObj(subServicesTotal.get(i).getNameService());
                        firstSetupAcitivity.subServicesObjArrayList.add(subServicesObjs[i]);
                    }
                    firstSetupAcitivity.updateListAllSubServices(firstSetupAcitivity.subServicesObjArrayList);
                }

                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showServicesDetailFragment();
                }
                break;

            default:
                break;
        }
        super.onClick(view);
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
