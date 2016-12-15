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

        fragmentsArrayList.add(mVenueViewFragment);
        fragmentsArrayList.add(mVenueViewEditFragment);
        fragmentsArrayList.add(mVenueViewEditAboutFragment);
        fragmentsArrayList.add(mVenueViewEditLocationFragment);
        fragmentsArrayList.add(mVenueViewEditNameSalonFragment);
        fragmentsArrayList.add(mVenueViewEditPhotoFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mVenueViewEditFragment)
                .add(R.id.container, mVenueViewFragment)
                .add(R.id.container, mVenueViewEditAboutFragment)
                .add(R.id.container, mVenueViewEditLocationFragment)
                .add(R.id.container, mVenueViewEditNameSalonFragment)
                .add(R.id.container, mVenueViewEditPhotoFragment)

                .commit();
        if (getIntent().getExtras().getString(Constants.VENUE_KEY_CHECK).equals(Constants.VENUE_VIEW)) {
            showFragment(mVenueViewFragment);
        }

    }


    @Override
    public void onClick(View view) {

    }

    public void showFragment(Fragment fragmentToShow) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (BaseFragment _fragment : fragmentsArrayList) {
            if (_fragment == fragmentToShow) {
                transaction.show(fragmentToShow);
            } else {
                transaction.hide(_fragment);
            }
        }
        transaction.commit();
    }

    @Override
    public void showVenueViewEdit() {
        showFragment(mVenueViewEditFragment);

    }

    @Override
    public void showVenueView() {
        showFragment(mVenueViewFragment);

    }

    @Override
    public void showVenueViewEditLocation() {
        showFragment(mVenueViewEditLocationFragment);

    }

    @Override
    public void showVenueViewEditAbout() {
        showFragment(mVenueViewEditAboutFragment);

    }

    @Override
    public void showVenueViewEditPhoto() {
        showFragment(mVenueViewEditPhotoFragment);

    }

    @Override
    public void showVenueViewEditNameSalon() {
        showFragment(mVenueViewEditNameSalonFragment);

    }


    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }
}
