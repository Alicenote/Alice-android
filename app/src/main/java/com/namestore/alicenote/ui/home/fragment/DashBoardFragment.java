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
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.Authorization;
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
    private Authorization auth = new Authorization("116", "JvM5QOH7E2acM1PpIyazWjSSPVzA44Cj");


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
        setPrgDialog("show");

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

        aliceApi.searchWeekAppointment().enqueue(new Callback<List<DashBoardRespone>>() {
            @Override
            public void onResponse(Call<List<DashBoardRespone>> call, Response<List<DashBoardRespone>> response) {

            }

            @Override
            public void onFailure(Call<List<DashBoardRespone>> call, Throwable t) {

            }
        });

//        aliceApi.searchWeekAppointment().enqueue(new Callback<DashBoardRespone>() {
//            @Override
//            public void onResponse(Call<DashBoardRespone> call, Response<DashBoardRespone> response) {
//                Log.w("fsadfsadjfasdkfl;sj","OK LOGIN || STATUS: " + response.body().getClient() );
//                if (response.isSuccessful()) {
//                    prgDialog.hide();
//                   /* for (int i = 0; i < response.body().size(); i++) {
//
//                        DashboardObj apk = new DashboardObj(0, null, null, null, null, null);
//                        apk.setTvNameSevice(response.body().get(i).getService());
//                        apk.setTvDate("");
//                        apk.setTvDuration(response.body().get(i).getDuration());
//                        apk.setTvNameStaff(response.body().get(i).getStaff());
//                        apk.setTvTimeStart(response.body().get(i).getStart_time());
//                        mListViewContactUpComming.add(apk);
//                        apk.setTvDate(response.body().get(i).getDate());
//                        mListViewContactThisWeek.add(apk);
//*/
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DashBoardRespone> call, Throwable t) {
//                if (call.isCanceled()) {
//                    AppUtils.logE("request was cancelled");
//                } else {
//                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
//                }
//            }
//        });


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
