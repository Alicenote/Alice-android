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
import com.namestore.alicenote.common.AppUtils;
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

public class ReportFragment extends BaseFragment implements RankingAdapterRecycler.OnRankClickItemListener {

    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewFullTopDialog;
    private RankingAdapterRecycler rankingAdapterRecycler;
    private RankingDialogAdapterRecycler adapterRecyclerViewFullTopDialog;

    private RelativeLayout titleTopEmployeeDialog;
    private Dialog mTopRankingDialog;
    private Button mButtonCancel;
    private TextView mTextViewTitleTopServiceDialog;
    private TextView mTextViewGuests;
    private TextView mTextViewMonth;
    private TextView mTextViewProportion;

    private ArrayList<RankingObj> rankingObjArrayList = new ArrayList<>();
    private ArrayList<RankingObj.Data> topServicesArrayList = new ArrayList<>();
    private ArrayList<RankingObj.Data> topEmployeeArrayList = new ArrayList<>();
    private ArrayList<RankingObj.Data> reportView = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_report, container, false);
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
        mTextViewGuests = (TextView) view.findViewById(R.id.textview_report_guests);
        mTextViewMonth = (TextView) view.findViewById(R.id.textview_report_month);
        mTextViewProportion = (TextView) view.findViewById(R.id.textview_report_proportion);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
    }

    @Override
    protected void initModels() {
        AppUtils.setTypeFontForTextView(mainActivity, AppUtils.MEDIUM, mTextViewMonth, mTextViewProportion);
        AppUtils.setTypeFontForTextView(mainActivity, AppUtils.BOLD, mTextViewGuests);

        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        float densityScreen = mainActivity.getResources().getDisplayMetrics().density;
        SpacesItemDecoration decoration = new SpacesItemDecoration(AppUtils.convertDpToPx(mainActivity,
                getActivity().getResources().getDimension(R.dimen._3dp) / densityScreen));

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

        rankingAdapterRecycler = new RankingAdapterRecycler(mainActivity, rankingObjArrayList, this);
        recyclerView.setAdapter(rankingAdapterRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        configTopRankingDialog();
    }

    private void configTopRankingDialog() {
        mTopRankingDialog = new Dialog(mainActivity);
        mTopRankingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mTopRankingDialog.setContentView(R.layout.dialog_ranking_top);
        mTopRankingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mTopRankingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mTopRankingDialog.setCancelable(false);
        titleTopEmployeeDialog = (RelativeLayout) mTopRankingDialog.findViewById(R.id.title_top_employee);
        mTextViewTitleTopServiceDialog = (TextView) mTopRankingDialog.findViewById(R.id.title_top_service);

        AppUtils.setTypeFontForTextView(mainActivity, AppUtils.MEDIUM, mTextViewTitleTopServiceDialog,
                (TextView) mTopRankingDialog.findViewById(R.id.textview_employee),
                (TextView) mTopRankingDialog.findViewById(R.id.textview_top_ranking));

        mButtonCancel = (Button) mTopRankingDialog.findViewById(R.id.cancel_button);
        mButtonCancel.setOnClickListener(view -> mTopRankingDialog.dismiss());

        recyclerViewFullTopDialog = (RecyclerView) mTopRankingDialog.findViewById(R.id.list);
        recyclerViewFullTopDialog.addItemDecoration(new SimpleDividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL, 15));

        recyclerViewFullTopDialog.setLayoutManager(new LinearLayoutManager(mainActivity));
        recyclerViewFullTopDialog.setHasFixedSize(true);
    }

    @Override
    public void onFullServiceButton() {
        titleTopEmployeeDialog.setVisibility(View.GONE);
        mTextViewTitleTopServiceDialog.setVisibility(View.VISIBLE);
        adapterRecyclerViewFullTopDialog = new RankingDialogAdapterRecycler(mainActivity, topServicesArrayList,
                RankingDialogAdapterRecycler.SERVICES);
        recyclerViewFullTopDialog.setAdapter(adapterRecyclerViewFullTopDialog);
        mTopRankingDialog.show();
    }

    @Override
    public void onFullEmployeeButton() {
        titleTopEmployeeDialog.setVisibility(View.VISIBLE);
        mTextViewTitleTopServiceDialog.setVisibility(View.GONE);
        adapterRecyclerViewFullTopDialog = new RankingDialogAdapterRecycler(mainActivity, topEmployeeArrayList,
                RankingDialogAdapterRecycler.EMPLOYEE);
        recyclerViewFullTopDialog.setAdapter(adapterRecyclerViewFullTopDialog);
        mTopRankingDialog.show();
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
