package com.namestore.alicenote.ui.venue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.venue.fragment.VenueViewEditAboutFragment;
import com.namestore.alicenote.ui.venue.fragment.VenueViewEditFragment;
import com.namestore.alicenote.ui.venue.fragment.VenueViewEditLocationFragment;
import com.namestore.alicenote.ui.venue.fragment.VenueViewEditNameSalonFragment;
import com.namestore.alicenote.ui.venue.fragment.VenueViewEditPhotoFragment;
import com.namestore.alicenote.ui.venue.fragment.VenueViewFragment;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

import java.util.ArrayList;

public class VenueDetailActivity extends BaseActivity implements OnSettingVenueListener, OnFragmentInteractionListener {
    private ArrayList<BaseFragment> fragmentsArrayList = new ArrayList<>();
    private VenueViewEditFragment mVenueViewEditFragment;
    private VenueViewFragment mVenueViewFragment;
    private VenueViewEditAboutFragment mVenueViewEditAboutFragment;
    private VenueViewEditLocationFragment mVenueViewEditLocationFragment;
    private VenueViewEditNameSalonFragment mVenueViewEditNameSalonFragment;
    private VenueViewEditPhotoFragment mVenueViewEditPhotoFragment;
    private float mMapX,mMapY;
    public String mAboutSalon,mNameSalon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);

        mVenueViewEditFragment = new VenueViewEditFragment();
        mVenueViewFragment = new VenueViewFragment();
        mVenueViewEditAboutFragment = new VenueViewEditAboutFragment();
        mVenueViewEditLocationFragment = new VenueViewEditLocationFragment();
        mVenueViewEditNameSalonFragment = new VenueViewEditNameSalonFragment();
        mVenueViewEditPhotoFragment = new VenueViewEditPhotoFragment();

        if (getIntent().getExtras().getString(Constants.VENUE_KEY_CHECK).equals(Constants.VENUE_VIEW)) {
           showVenueView();
        }

    }


    @Override
    public void onClick(View view) {

    }



    @Override
    public void showVenueViewEdit() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mVenueViewEditFragment).commit();

    }

    @Override
    public void showVenueView() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mVenueViewFragment).commit();
    }

    @Override
    public void showVenueViewEditLocation() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mVenueViewEditLocationFragment).commit();

    }

    @Override
    public void showVenueViewEditAbout() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mVenueViewEditAboutFragment).commit();
    }

    @Override
    public void showVenueViewEditPhoto() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mVenueViewEditPhotoFragment).commit();
    }

    @Override
    public void showVenueViewEditNameSalon() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mVenueViewEditNameSalonFragment).commit();
    }


    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }
}
