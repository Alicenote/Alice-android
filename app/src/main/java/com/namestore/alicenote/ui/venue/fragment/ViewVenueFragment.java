package com.namestore.alicenote.ui.venue.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.ui.venue.VenueDetailActivity;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

/**
 * Created by nhocnhinho on 06/12/2016.
 */

public class ViewVenueFragment extends BaseFragment implements OnFragmentInteractionListener, OnMapReadyCallback {

    private VenueDetailActivity mVenueDetailActivity;
    private LinearLayout mToolbar;
    private TextView mToolbarBack;
    private TextView mToolbarEdit;
    private TextView mToolbarTitle;
    private SupportMapFragment mSupportMapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_venue_view, container, false);
        mToolbar = (LinearLayout) view.findViewById(R.id.toolbar);


        initViews(view);
        initModels();
        return view;

    }

    @Override
    protected void initViews(View view) {
        mToolbarBack = (TextView) mToolbar.findViewById(R.id.toolbar_backpress);
        mToolbarEdit = (TextView) mToolbar.findViewById(R.id.toolbar_edit);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mSupportMapFragment = SupportMapFragment.newInstance();
        fragmentTransaction.replace(R.id.mVenueGoogleMap, mSupportMapFragment).commit();
        mSupportMapFragment.getMapAsync(this);

    }

    @Override
    protected void initModels() {
        mToolbarTitle.setText(R.string.your_venue);
        mToolbarBack.setOnClickListener(view -> getActivity().finish());
        mToolbarEdit.setOnClickListener(view -> {
            if (mActivity instanceof OnSettingVenueListener) {
                ((OnSettingVenueListener) mActivity).showEditVenue();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng yourVenue = new LatLng(21.014086, 105.795860);
        MarkerOptions option = new MarkerOptions();
        option.position(yourVenue);
        option.title("Your Venue").snippet("122 trung kinh");
        option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        Marker maker = googleMap.addMarker(option);
        maker.showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourVenue, 18));
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
