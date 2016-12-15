package com.namestore.alicenote.ui.venue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.ui.BaseFragment;

/**
 * Created by nhocnhinho on 14/12/2016.
 */

public class VenueViewEditLocationFragment extends BaseFragment {
    private LinearLayout mToolbar;
    private Button mToolbarBack;
    private Button mToolbarSave;
    private TextView mToolbarTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_venue_view_edit_location, container, false);
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
    }

    @Override
    protected void initModels() {

    }
}