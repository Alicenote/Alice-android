package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.network.request.FirstSetupRequest;
import com.namestore.alicenote.ui.firstsetup.adapter.SubServicesAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.models.SubServicesObj;
import com.namestore.alicenote.common.ViewUtils;

import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;

import java.util.ArrayList;

/**
 * Created by kienht on 10/26/16.
 */
public class NailServicesFragment extends BaseFragment implements SubServicesAdapter.OnSubServicesClickListener {

    public static final int NAIL_ID_ON_SERVER = 1;
    private RecyclerView recyclerViewNailService;
    TextView mTextViewTitle;
    EditText mEditTexAddNailService;
    Button mButtonBack;
    Button mButtonNext;
    Button mButtonAddService;
    LinearLayout linearLayout;
    private FirstSetupAcitivity firstSetupAcitivity;
    private String newService;
    SubServicesAdapter subServicesAdapter;
    public ArrayList<SubServicesObj> nailServicesArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_nail_service, container, false);
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
        recyclerViewNailService = (RecyclerView) view.findViewById(R.id.list_nail_services);
        recyclerViewNailService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewNailService.setHasFixedSize(true);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_pick_nail_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("Nail Services");
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
        mButtonAddService = (Button) view.findViewById(R.id.button_add_nail_service);
        mEditTexAddNailService = (EditText) view.findViewById(R.id.editTex_add_nail_service);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_frgment_nail_service);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonBack.setOnClickListener(this);
        mButtonNext.setVisibility(View.INVISIBLE);
        mButtonAddService.setOnClickListener(this);
        ViewUtils.configEditText(getActivity(), mEditTexAddNailService, linearLayout, "Add nail service", 0, null);
        updateRecyclerView(new ArrayList<SubServicesObj>());
    }

    public void updateRecyclerView(final ArrayList<SubServicesObj> subServicesObjs) {
        this.nailServicesArrayList = subServicesObjs;

        subServicesAdapter = new SubServicesAdapter(getActivity(), nailServicesArrayList, this, Constants.NAIL);

        recyclerViewNailService.setAdapter(subServicesAdapter);
        recyclerViewNailService.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:

                ArrayList<FirstSetupRequest.Service.SubServices> subServicesArrayList = new ArrayList<>();
                FirstSetupRequest.Service.SubServices[] subServices = new FirstSetupRequest.Service.SubServices[nailServicesArrayList.size()];
                for (int i = 0; i < subServices.length; i++) {
                    subServices[i] = new FirstSetupRequest().new Service().new SubServices(nailServicesArrayList.get(i).getNameSubServices(),
                            nailServicesArrayList.get(i).isCheck(), 0, 0, true);
                    subServicesArrayList.add(subServices[i]);
                }
                firstSetupAcitivity.sizeNailArrayList = nailServicesArrayList.size();

                FirstSetupRequest.Service.Group nailGroup = new FirstSetupRequest().new Service().new Group(NAIL_ID_ON_SERVER, "Nail");
                FirstSetupRequest.Service nailServices = new FirstSetupRequest().new Service(nailGroup, subServicesArrayList);

                firstSetupAcitivity.serviceArrayList.set(Constants.NAIL, nailServices);

                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showShopServicesCategoryFragment();
                }
                break;

            /**
             * Add nail Service
             * */
            case R.id.button_add_nail_service:
                newService = mEditTexAddNailService.getText().toString();
                SubServicesObj temp = new SubServicesObj();
                temp.setNameSubServices(newService);
                temp.setCheck(true);
                if (!TextUtils.isEmpty(newService)) {
                    mEditTexAddNailService.getText().clear();
                    subServicesAdapter.addItem(temp);
                    recyclerViewNailService.scrollToPosition(subServicesAdapter.getItemCount() - 1);

                    //update list Nail Sub Services after add Item
                    nailServicesArrayList.add(temp);
                    if (firstSetupAcitivity.subServicesObjArrayList.size() != 0) {
                        firstSetupAcitivity.subServicesObjArrayList.add(firstSetupAcitivity.sizeNailArrayList, temp);
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
        if (tag == Constants.NAIL) {
            nailServicesArrayList.remove(position);
            if (firstSetupAcitivity.subServicesObjArrayList.size() != 0) {
                firstSetupAcitivity.subServicesObjArrayList.remove(position);
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
