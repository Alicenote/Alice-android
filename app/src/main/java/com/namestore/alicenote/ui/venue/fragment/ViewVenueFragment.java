package com.namestore.alicenote.ui.venue.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.ui.venue.VenueDetail;

/**
 * Created by nhocnhinho on 06/12/2016.
 */

public class ViewVenueFragment extends BaseFragment implements OnFragmentInteractionListener {

private VenueDetail mVenueDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_venue_view, container, false);
        initViews(view);
        initModels();
        return view;

    }
    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initModels() {

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

        if (context instanceof VenueDetail) {
            this.mVenueDetail = (VenueDetail) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof VenueDetail) {
            this.mVenueDetail = (VenueDetail) activity;
        }
    }

}
