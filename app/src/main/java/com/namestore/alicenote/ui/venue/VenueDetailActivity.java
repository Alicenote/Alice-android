package com.namestore.alicenote.ui.venue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.venue.fragment.EditVenueFragment;
import com.namestore.alicenote.ui.venue.fragment.ViewVenueFragment;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

import java.util.ArrayList;

public class VenueDetailActivity extends BaseActivity implements OnSettingVenueListener, OnFragmentInteractionListener {
    private ArrayList<BaseFragment> fragmentsArrayList = new ArrayList<>();
    private EditVenueFragment mEditVenueFragment;
    private ViewVenueFragment mViewVenueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);

        mEditVenueFragment = new EditVenueFragment();
        mViewVenueFragment = new ViewVenueFragment();
        fragmentsArrayList.add(mViewVenueFragment);
        fragmentsArrayList.add(mEditVenueFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mEditVenueFragment)
                .add(R.id.container, mViewVenueFragment)
                .commit();
        if (getIntent().getExtras().getString(Constants.VENUE_KEY_CHECK).equals(Constants.VENUE_VIEW)) {
            showFragment(mViewVenueFragment);
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
    public void showEditVenue() {
        showFragment(mEditVenueFragment);

    }

    @Override
    public void showViewVenue() {
        showFragment(mViewVenueFragment);

    }

    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }
}
