package com.namestore.alicenote.ui.home.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.network.AliceApi;

import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.DashBoardRespone;
import com.namestore.alicenote.ui.home.adapter.DashboardCustomRecyclerViewAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.models.DashboardObj;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhocnhinho on 07/11/2016.
 */

public class DashBoardFragment extends BaseFragment {

    private TextView tvSaleIn;
    private List<DashboardObj> mListViewContactUpComming = new ArrayList<>();
    private List<DashboardObj> mListViewContactThisWeek = new ArrayList<>();
    private RecyclerView mRecyclerListViewUpComming, mRecyclerListViewThisWeek;
    private Button btnHideUpComming, btnHideThisWeek;
    private int checkHideUpComming, checkHideThisWeek;
    private ProgressDialog prgDialog;
    private AliceApi aliceApi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_dashboard, container, false);
        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {

        tvSaleIn = (TextView) view.findViewById(R.id.tvSaleIn);
        mRecyclerListViewUpComming = (RecyclerView) view.findViewById(R.id.recyclerViewUpcommingAppointment);//listview cua upcoming
        btnHideUpComming = (Button) view.findViewById(R.id.btnHideUpComming);
        mRecyclerListViewThisWeek = (RecyclerView) view.findViewById(R.id.recyclerViewWeekAppointment);//listview cua thisweek
        btnHideThisWeek = (Button) view.findViewById(R.id.btnHideThisWeek);


        mRecyclerListViewUpComming.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        mRecyclerListViewUpComming.setHasFixedSize(true);
        mRecyclerListViewThisWeek.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        mRecyclerListViewThisWeek.setHasFixedSize(true);

        aliceApi = ServiceGenerator.creatService(AliceApi.class);
        searchWeekAppointment();


    }


    @Override
    protected void initModels() {
        DashboardCustomRecyclerViewAdapter adapter = new DashboardCustomRecyclerViewAdapter(getContext(), mListViewContactUpComming);
        mRecyclerListViewUpComming.setAdapter(adapter);


        mRecyclerListViewUpComming.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
        );
        btnHideUpComming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkHideUpComming == 0) {
                    mRecyclerListViewUpComming.setVisibility(View.GONE);
                    btnHideUpComming.setText("SHOW");
                    checkHideUpComming = 1;
                } else {
                    mRecyclerListViewUpComming.setVisibility(View.VISIBLE);
                    btnHideUpComming.setText("HIDE");
                    checkHideUpComming = 0;
                }
            }
        });


        DashboardCustomRecyclerViewAdapter adapterThisWeek = new DashboardCustomRecyclerViewAdapter(getContext(), mListViewContactThisWeek);
        mRecyclerListViewThisWeek.setAdapter(adapterThisWeek);
        mRecyclerListViewThisWeek.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
        );
        btnHideThisWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkHideThisWeek == 0) {
                    mRecyclerListViewThisWeek.setVisibility(View.GONE);
                    btnHideThisWeek.setText("SHOW");
                    checkHideThisWeek = 1;
                } else {
                    mRecyclerListViewThisWeek.setVisibility(View.VISIBLE);
                    btnHideThisWeek.setText("HIDE");
                    checkHideThisWeek = 0;
                }
            }
        });
    }

    public void searchWeekAppointment() {

        aliceApi.searchWeekAppointment(116, 103).enqueue(new Callback<DashBoardRespone>() {
            @Override
            public void onResponse(Call<DashBoardRespone> call, Response<DashBoardRespone> response) {


                AppUtils.logE("im here");
                if (response.isSuccessful()) {
                    AppUtils.logE("im here" + response.body().getData().get(0).getId());

                }

            }

            @Override
            public void onFailure(Call<DashBoardRespone> call, Throwable t) {
                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });


    }

    public void setPrgDialog(String text) {
        prgDialog = new ProgressDialog(getActivity());
        prgDialog.setMessage(text);
        prgDialog.show();
    }


    @Override
    public void onDestroy() {
        if (prgDialog != null) {
            prgDialog.dismiss();
        }
        super.onDestroy();
    }

}
