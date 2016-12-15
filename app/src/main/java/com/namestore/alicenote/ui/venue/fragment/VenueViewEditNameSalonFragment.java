package com.namestore.alicenote.ui.venue.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.venue.VenueDetailActivity;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

/**
 * Created by nhocnhinho on 14/12/2016.
 */

public class VenueViewEditNameSalonFragment extends BaseFragment {
    private LinearLayout mToolbar;
    private Button mToolbarBack;
    private Button mToolbarSave;
    private TextView mToolbarTitle;
    private String mTextTitle = "Edit About";
    private EditText mEditNameSalon;
    private VenueDetailActivity mVenueDetailActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_venue_view_edit_name_salon, container, false);
        mToolbar = (LinearLayout) view.findViewById(R.id.toolbar);


        initViews(view);
        initModels();
        return view;

    }

    @Override
    protected void initViews(View view) {
        mToolbarBack = (Button) mToolbar.findViewById(R.id.toolbar_backpress);
        ViewUtils.setAndScaleDrawableButton(mToolbarBack, R.drawable.icon_back, 0, 0, 0, 0.8);
        mToolbarSave = (Button) mToolbar.findViewById(R.id.toolbar_edit);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mEditNameSalon = (EditText) view.findViewById(R.id.mVenueViewEditEditNameSalon);


    }

    @Override
    protected void initModels() {

        mToolbarTitle.setText(mTextTitle);
        mEditNameSalon.setText(mVenueDetailActivity.mNameSalon);
        mToolbarBack.setOnClickListener(view1 -> {
            if (mActivity instanceof OnSettingVenueListener) {
                ((OnSettingVenueListener) mActivity).showVenueViewEdit();
            }

        });

        mToolbarSave.setText("Save");
        mToolbarSave.setOnClickListener(view1 -> {

            mVenueDetailActivity.mNameSalon = mEditNameSalon.getText().toString();

            if (mActivity instanceof OnSettingVenueListener) {
                ((OnSettingVenueListener) mActivity).showVenueViewEdit();
            }

        });

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof VenueDetailActivity) {
            this.mVenueDetailActivity = (VenueDetailActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof VenueDetailActivity) {
            this.mVenueDetailActivity = (VenueDetailActivity) activity;
        }
    }
}