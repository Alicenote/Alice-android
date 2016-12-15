package com.namestore.alicenote.ui.home;

import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.appointment.AppointmentScheduleActivity;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.art.ArtGroupActivity;

import com.namestore.alicenote.ui.home.fragment.ClientFragment;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;

import com.namestore.alicenote.ui.home.fragment.PendingFragment;
import com.namestore.alicenote.ui.home.fragment.ReportFragment;
import com.namestore.alicenote.ui.home.fragment.SettingVenueFragment;
import com.namestore.alicenote.ui.home.fragment.TreatmentsListingFragment;

import static com.namestore.alicenote.Constants.NUM_PAGES;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public DrawerLayout drawer;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.viewPagerMain);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        ViewUtils.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_dashboard:
                            showPenddingFragment();
                            break;
                        case R.id.action_calendar:
                            showTreatmentsListingFragment();
                            break;
                        case R.id.action_client:
                            showClientFragment();
                            break;
                        case R.id.action_setting_venue:
                            showVenueFragment();
                            break;
                        case R.id.action_ranking:
                            showRankingFragment();
                            break;
                    }
                    return false;
                });

        bottomNavigationView.getMaxItemCount();
    }


    public void showPenddingFragment() {
        mPager.setCurrentItem(Constants.NUM_PENDING);
    }

    public void showClientFragment() {
        mPager.setCurrentItem(Constants.NUM_CLIENT);
    }

    public void showRankingFragment() {
        mPager.setCurrentItem(Constants.NUM_REPORT);
    }

    public void showTreatmentsListingFragment() {
        mPager.setCurrentItem(Constants.NUM_TREATMENTS_LISTING);
    }

    public void showVenueFragment() {
        mPager.setCurrentItem(Constants.NUM_SETTING_VENUE);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case Constants.NUM_PENDING:
                    return new PendingFragment();
                case Constants.NUM_TREATMENTS_LISTING:
                    return new TreatmentsListingFragment();
                case Constants.NUM_REPORT:
                    return new ReportFragment();
                case Constants.NUM_CLIENT:
                    return new ClientFragment();
                case Constants.NUM_SETTING_VENUE:
                    return new SettingVenueFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return NUM_PAGES;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (mPager.getCurrentItem() == Constants.NUM_PENDING) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case R.id.art:
                startActivity(new Intent(MainActivity.this, ArtGroupActivity.class));
                break;

            case R.id.calendar:
                startActivity(new Intent(MainActivity.this, AppointmentScheduleActivity.class));
                break;
        }
        int id = item.getItemId();
        if (id == R.id.calendar) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
    @Override
    public void onViewClick(String tag) {
    }

    @Override
    public void onViewClick(String tag, Object object) {
    }


}
