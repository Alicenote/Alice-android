package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.home.MainActivity;

/**
 * Created by kienht on 12/15/16.
 */

public class TreatmentsListingFragment extends BaseFragment {


    private MainActivity mMainActivity;
    private TextView mTextViewTitleBar;
    private Button mButtonLeftBar;
    private Button mButtonRightBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_treatments_listing, container, false);
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
        mTextViewTitleBar = (TextView) view.findViewById(R.id.include).findViewById(R.id.toolbar_title);
        mButtonLeftBar = (Button) view.findViewById(R.id.include).findViewById(R.id.toolbar_backpress);
        mButtonRightBar = (Button) view.findViewById(R.id.include).findViewById(R.id.toolbar_edit);
    }

    @Override
    protected void initModels() {
        mButtonLeftBar.setVisibility(View.INVISIBLE);
        mTextViewTitleBar.setText("Treatments Listing");
        ViewUtils.setAndScaleDrawableButton(mButtonRightBar, 0, 0, R.drawable.icon_add, 0, 0.7);
        AppUtils.setTypeFontForTextView(mMainActivity, AppUtils.BOLD, mTextViewTitleBar);
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
