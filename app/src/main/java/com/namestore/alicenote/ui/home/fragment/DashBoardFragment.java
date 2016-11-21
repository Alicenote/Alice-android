package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.network.AliceApi;

import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.DashBoardRespone;
import com.namestore.alicenote.ui.home.MainActivity;
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
    private RecyclerView mRecyclerListViewUpComming;
    private RecyclerView mRecyclerListViewThisWeek;
    private Button btnHideUpComming;
    private Button btnHideThisWeek;
    private int mCheckHideUpComming;
    private int mCheckHideThisWeek;

    private AliceApi mAliceApi;
    private Toolbar toolbar;
    private MainActivity mMainActivity;

    private ProgressBar mProgressBarUpComming,mProgressBarWeek;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_dashboard, container, false);


        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
          toolbar.setTitle("DashBoard");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle((Activity) getContext(),mMainActivity.drawer , toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mMainActivity.drawer.setDrawerListener(toggle);
        toggle.syncState();
        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {


        mRecyclerListViewUpComming = (RecyclerView) view.findViewById(R.id.recyclerViewUpcommingAppointment);//listview cua upcoming
        btnHideUpComming = (Button) view.findViewById(R.id.btnHideUpComming);
        mRecyclerListViewThisWeek = (RecyclerView) view.findViewById(R.id.recyclerViewWeekAppointment);//listview cua thisweek
        btnHideThisWeek = (Button) view.findViewById(R.id.btnHideThisWeek);


        mRecyclerListViewUpComming.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        mRecyclerListViewUpComming.setHasFixedSize(true);
        mRecyclerListViewThisWeek.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        mRecyclerListViewThisWeek.setHasFixedSize(true);

        mAliceApi = ServiceGenerator.creatService(AliceApi.class);
        searchWeekAppointment();
        searchUpCommingAppointment();

        DashboardCustomRecyclerViewAdapter adapter = new DashboardCustomRecyclerViewAdapter(getContext(), mListViewContactUpComming);
        mRecyclerListViewUpComming.setAdapter(adapter);

        DashboardCustomRecyclerViewAdapter adapterThisWeek = new DashboardCustomRecyclerViewAdapter(getContext(), mListViewContactThisWeek);
        mRecyclerListViewThisWeek.setAdapter(adapterThisWeek);
        mProgressBarUpComming = (ProgressBar) view.findViewById(R.id.prgBarUpComming);
        mProgressBarWeek = (ProgressBar) view.findViewById(R.id.prgBarWeekAppointment);
    }


    @Override
    protected void initModels() {


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
                if (mCheckHideUpComming == 0) {
                    mRecyclerListViewUpComming.setVisibility(View.GONE);
                    btnHideUpComming.setText("SHOW");
                    mCheckHideUpComming = 1;
                } else {
                    mRecyclerListViewUpComming.setVisibility(View.VISIBLE);
                    btnHideUpComming.setText("HIDE");
                    mCheckHideUpComming = 0;
                }
            }
        });


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
                if (mCheckHideThisWeek == 0) {
                    mRecyclerListViewThisWeek.setVisibility(View.GONE);
                    btnHideThisWeek.setText("SHOW");
                    mCheckHideThisWeek = 1;
                } else {
                    mRecyclerListViewThisWeek.setVisibility(View.VISIBLE);
                    btnHideThisWeek.setText("HIDE");
                    mCheckHideThisWeek = 0;
                }
            }
        });
    }

    public void searchWeekAppointment() {
        mAliceApi.searchWeekAppointment(116, 103).enqueue(new Callback<DashBoardRespone>() {
            @Override
            public void onResponse(Call<DashBoardRespone> call, Response<DashBoardRespone> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {

                        DashboardObj jsonArray = new DashboardObj(0, null, null, null, null, null);
                        jsonArray.setTvNameSevice(response.body().getData().get(i).getService());
                        jsonArray.setTvDuration(response.body().getData().get(i).getDuration());
                        jsonArray.setTvNameStaff(response.body().getData().get(i).getStaff());
                        jsonArray.setTvTimeStart(response.body().getData().get(i).getStartTime());
                        jsonArray.setTvDate(response.body().getData().get(i).getDate());
                        mListViewContactThisWeek.add(jsonArray);
                    }
                    mProgressBarWeek.setVisibility(View.GONE);
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

    public void searchUpCommingAppointment() {

        mAliceApi.searchUpCommingAppointment(116, 103).enqueue(new Callback<DashBoardRespone>() {
            @Override
            public void onResponse(Call<DashBoardRespone> call, Response<DashBoardRespone> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {

                        DashboardObj apk = new DashboardObj(0, null, null, null, null, null);
                        apk.setTvNameSevice(response.body().getData().get(i).getService());
                        apk.setTvDuration(response.body().getData().get(i).getDuration());
                        apk.setTvNameStaff(response.body().getData().get(i).getStaff());
                        apk.setTvTimeStart(response.body().getData().get(i).getStartTime());

                        mListViewContactUpComming.add(apk);
                    }
                    mProgressBarUpComming.setVisibility(View.GONE);
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




    @Override
    public void onDestroy() {
        if (mProgressBarUpComming != null||mProgressBarWeek!=null) {
            mProgressBarUpComming.setVisibility(View.GONE);
            mProgressBarWeek.setVisibility(View.GONE);
        }
        super.onDestroy();
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
