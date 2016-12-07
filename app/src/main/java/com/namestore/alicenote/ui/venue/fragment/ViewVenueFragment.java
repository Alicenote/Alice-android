package com.namestore.alicenote.ui.venue.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.venue.VenueDetailActivity;

/**
 * Created by nhocnhinho on 06/12/2016.
 */

public class ViewVenueFragment extends BaseFragment implements OnFragmentInteractionListener {

    private VenueDetailActivity mVenueDetailActivity;
    private Toolbar mToolbar;
    private TextView mToolbarBack;
    private TextView mToolbarEdit;
    private TextView mToolbarTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_venue_view, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initViews(view);
        initModels();
        return view;

    }

    @Override
    protected void initViews(View view) {
        mToolbarBack = (TextView) mToolbar.findViewById(R.id.toolbar_backpress);
        mToolbarEdit = (TextView) mToolbar.findViewById(R.id.toolbar_edit);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
    }

    @Override
    protected void initModels() {
        mToolbarTitle.setText(R.string.your_venue);
        mToolbarBack.setOnClickListener(view -> getActivity().finish());
        mToolbarEdit.setOnClickListener(view -> {

        });

    }

    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

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
