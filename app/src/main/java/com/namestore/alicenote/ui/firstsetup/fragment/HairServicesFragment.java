package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.ViewUtils;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.network.request.FirstSetupRequest;
import com.namestore.alicenote.ui.firstsetup.adapter.SubServicesAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.models.SubServicesObj;

import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;

import java.util.ArrayList;

/**
 * Created by kienht on 10/26/16.
 */

public class HairServicesFragment extends BaseFragment implements SubServicesAdapter.OnSubServicesClickListener {

    public static final int HAIR_ID_ON_SERVER = 2;
    private RecyclerView recyclerViewHairService;
    TextView mTextViewTitle;
    EditText mEditTexAddHairService;
    Button mButtonBack;
    Button mButtonNext;
    Button mButtonAddService;
    LinearLayout linearLayout;
    private String newService;
    SubServicesAdapter subServicesAdapter;
    private FirstSetupAcitivity firstSetupAcitivity;
    public ArrayList<SubServicesObj> hairServicesArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_hair_service, container, false);
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
        recyclerViewHairService = (RecyclerView) view.findViewById(R.id.list_hair_services);
        recyclerViewHairService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHairService.setHasFixedSize(true);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_pick_hair_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("Hair Services");
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
        mButtonAddService = (Button) view.findViewById(R.id.button_add_hair_service);
        mEditTexAddHairService = (EditText) view.findViewById(R.id.editTex_add_hair_service);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_frgment_hair_service);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonBack.setOnClickListener(this);
        mButtonNext.setVisibility(View.INVISIBLE);
        mButtonAddService.setOnClickListener(this);
        ViewUtils.configEditText(getActivity(), mEditTexAddHairService, linearLayout, "Add hair service", 0, null);
        updateRecyclerView(new ArrayList<SubServicesObj>());
    }

    public void updateRecyclerView(ArrayList<SubServicesObj> subServicesObjs) {
        this.hairServicesArrayList = subServicesObjs;
        subServicesAdapter = new SubServicesAdapter(getActivity(), hairServicesArrayList, this, Constants.HAIR);
        recyclerViewHairService.setAdapter(subServicesAdapter);
        recyclerViewHairService.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                ArrayList<FirstSetupRequest.Service.SubServices> subServicesArrayList = new ArrayList<>();
                FirstSetupRequest.Service.SubServices[] subServices = new FirstSetupRequest.Service.SubServices[hairServicesArrayList.size()];
                for (int i = 0; i < subServices.length; i++) {
                    subServices[i] = new FirstSetupRequest().new Service().new SubServices(hairServicesArrayList.get(i).getNameSubServices(),
                            hairServicesArrayList.get(i).isCheck(), 0, 0, true);
                    subServicesArrayList.add(subServices[i]);
                }

                firstSetupAcitivity.sizeHairArrayList = hairServicesArrayList.size();

                FirstSetupRequest.Service.Group hairGroup = new FirstSetupRequest().new Service().new Group(HAIR_ID_ON_SERVER, "Hair");
                FirstSetupRequest.Service hairServices = new FirstSetupRequest().new Service(hairGroup, subServicesArrayList);

                firstSetupAcitivity.serviceArrayList.set(Constants.HAIR, hairServices);

                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showShopServicesCategoryFragment();
                }
                break;

            /**
             * Add hair Service
             * */
            case R.id.button_add_hair_service:
                newService = mEditTexAddHairService.getText().toString();
                SubServicesObj temp = new SubServicesObj();
                temp.setNameSubServices(newService);
                temp.setCheck(true);
                if (!TextUtils.isEmpty(newService)) {
                    mEditTexAddHairService.getText().clear();
                    subServicesAdapter.addItem(temp);
                    recyclerViewHairService.scrollToPosition(subServicesAdapter.getItemCount() - 1);

                    //update list Hair Sub Services Item
                    hairServicesArrayList.add(temp);
                    if (firstSetupAcitivity.subServicesObjArrayList.size() != 0) {
                        firstSetupAcitivity.subServicesObjArrayList.add(firstSetupAcitivity.sizeNailArrayList
                                + firstSetupAcitivity.sizeHairArrayList, temp);
                        firstSetupAcitivity.swap();
                    }
                }
                break;

            default:
                break;
        }
        super.onClick(view);
    }

    @Override
    public void onDeleteSubServiceItem(int position, int tag) {
        if (tag == Constants.HAIR) {
            hairServicesArrayList.remove(position);
            if (firstSetupAcitivity.subServicesObjArrayList.size() != 0) {
                firstSetupAcitivity.subServicesObjArrayList.remove(position + firstSetupAcitivity.sizeNailArrayList);
                firstSetupAcitivity.swap();
            }
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
