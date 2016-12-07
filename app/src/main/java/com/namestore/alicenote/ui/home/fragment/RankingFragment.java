package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.SimpleDividerItemDecoration;
import com.namestore.alicenote.common.recycler.SpacesItemDecoration;
import com.namestore.alicenote.models.RankingObj;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.home.adapter.RankingAdapterRecycler;
import com.namestore.alicenote.ui.home.adapter.RankingDialogAdapterRecycler;

import java.util.ArrayList;

/**
 * Created by kienht on 07/12/2016.
 */

public class RankingFragment extends BaseFragment implements RankingAdapterRecycler.OnRankClickItemListener {

    private MainActivity mainActivity;
    RecyclerView recyclerView;
    RecyclerView recyclerViewFullTopDialog;
    RankingAdapterRecycler rankingAdapterRecycler;
    RankingDialogAdapterRecycler adapterRecyclerViewFullTopDialog;
    private ArrayList<RankingObj> rankingObjArrayList = new ArrayList<>();
    private ArrayList<RankingObj.Data> topServicesArrayList = new ArrayList<>();
    private ArrayList<RankingObj.Data> topEmployeeArrayList = new ArrayList<>();
    private ArrayList<RankingObj.Data> reportView = new ArrayList<>();
    Dialog topRankingDialog;
    Button mButtonCancel;
    RelativeLayout titleTopEmployeeDialog;
    TextView titleTopServiceDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_ranking, container, false);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
    }

    @Override
    protected void initModels() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        recyclerView.addItemDecoration(decoration);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        //fake data
        topServicesArrayList.add(new RankingObj().new Data("01", R.drawable.avatar, "Haircut and Blow Dry", "1st", 40));
        topServicesArrayList.add(new RankingObj().new Data("02", R.drawable.avatar, "Gents Haircut", "2nd", 38));
        topServicesArrayList.add(new RankingObj().new Data("03", R.drawable.avatar, "GHD Styling", "3rd", 27));
        topServicesArrayList.add(new RankingObj().new Data("04", R.drawable.avatar, "Body Massage", "4th", 12));
        topServicesArrayList.add(new RankingObj().new Data("05", R.drawable.avatar, "Head Foils", "5th", 12));
        topServicesArrayList.add(new RankingObj().new Data("06", R.drawable.avatar, "Full Head Tint, Hair cut a...", "6th", 6));
        topServicesArrayList.add(new RankingObj().new Data("06", R.drawable.avatar, "Full Head Tint, Hair cut a...", "6th", 6));
        topServicesArrayList.add(new RankingObj().new Data("06", R.drawable.avatar, "Full Head Tint, Hair cut a...", "6th", 6));
        topServicesArrayList.add(new RankingObj().new Data("06", R.drawable.avatar, "Full Head Tint, Hair cut a...", "6th", 6));
        topServicesArrayList.add(new RankingObj().new Data("06", R.drawable.avatar, "Full Head Tint, Hair cut a...", "6th", 6));

        topEmployeeArrayList.add(new RankingObj().new Data("01", R.drawable.avatar, "kienht", "1st", 99));
        topEmployeeArrayList.add(new RankingObj().new Data("02", R.drawable.avatar, "lucas vo", "2nd", 10));
        topEmployeeArrayList.add(new RankingObj().new Data("03", R.drawable.avatar, "ngocnn", "3rd", 11));
        topEmployeeArrayList.add(new RankingObj().new Data("04", R.drawable.avatar, "tien", "4th", 8));
        topEmployeeArrayList.add(new RankingObj().new Data("05", R.drawable.avatar, "khacpv", "5th", 7));
        topEmployeeArrayList.add(new RankingObj().new Data("06", R.drawable.avatar, "alicenote", "6th", 6));

        reportView.add(new RankingObj().new Data("", R.drawable.avatar, "Like", "", 2300));
        reportView.add(new RankingObj().new Data("", R.drawable.avatar, "Phone View", "", 1130));
        reportView.add(new RankingObj().new Data("", R.drawable.avatar, "Website View", "", 2300));

        rankingObjArrayList.add(new RankingObj(RankingObj.TOP_SERVICES, "Top Services in December 2016", topServicesArrayList));
        rankingObjArrayList.add(new RankingObj(RankingObj.TOP_EMPLOYEE, "Top Employee in December 2016", topEmployeeArrayList));
        rankingObjArrayList.add(new RankingObj(RankingObj.REPORT_VIEW, "Reports view in November 2016", reportView));

        rankingAdapterRecycler = new RankingAdapterRecycler(getActivity(), rankingObjArrayList, this);
        recyclerView.setAdapter(rankingAdapterRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        configTopRankingDialog();
    }

    public void configTopRankingDialog() {
        topRankingDialog = new Dialog(getActivity());
        topRankingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        topRankingDialog.setContentView(R.layout.dialog_ranking_top);
        topRankingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        topRankingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        topRankingDialog.setCancelable(false);
        titleTopEmployeeDialog = (RelativeLayout) topRankingDialog.findViewById(R.id.title_top_employee);
        titleTopServiceDialog = (TextView) topRankingDialog.findViewById(R.id.title_top_service);

        mButtonCancel = (Button) topRankingDialog.findViewById(R.id.cancel_button);
        mButtonCancel.setOnClickListener(view -> topRankingDialog.dismiss());

        recyclerViewFullTopDialog = (RecyclerView) topRankingDialog.findViewById(R.id.list);
        recyclerViewFullTopDialog.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 15));

        recyclerViewFullTopDialog.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFullTopDialog.setHasFixedSize(true);
    }

    @Override
    public void onFullServiceButton() {
        titleTopEmployeeDialog.setVisibility(View.GONE);
        titleTopServiceDialog.setVisibility(View.VISIBLE);
        adapterRecyclerViewFullTopDialog = new RankingDialogAdapterRecycler(getActivity(), topServicesArrayList,
                RankingDialogAdapterRecycler.SERVICES);
        recyclerViewFullTopDialog.setAdapter(adapterRecyclerViewFullTopDialog);
        topRankingDialog.show();
    }

    @Override
    public void onFullEmployeeButton() {
        titleTopEmployeeDialog.setVisibility(View.VISIBLE);
        titleTopServiceDialog.setVisibility(View.GONE);
        adapterRecyclerViewFullTopDialog = new RankingDialogAdapterRecycler(getActivity(), topEmployeeArrayList,
                RankingDialogAdapterRecycler.EMPLOYEE);
        recyclerViewFullTopDialog.setAdapter(adapterRecyclerViewFullTopDialog);
        topRankingDialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity) {
            this.mainActivity = (MainActivity) activity;
        }
    }


}
