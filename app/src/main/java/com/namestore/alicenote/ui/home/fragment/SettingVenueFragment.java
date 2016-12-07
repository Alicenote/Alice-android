package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.venue.VenueDetail;

/**
 * Created by nhocnhinho on 05/12/2016.
 */

public class SettingVenueFragment extends BaseFragment {

    private AliceApi mAliceApi;
    private MainActivity mMainActivity;

    private RelativeLayout mRelativeLayoutVenue;
    private TextView mTextVenue, mTextDetailVenue;
    private ImageView mImgVenue;

    private RelativeLayout mRelativeLayoutOpenHours;
    private TextView mTextOpenHours, mTextDetailOpenHours;
    private ImageView mImgOpenHours;

    private RelativeLayout mRelativeLayoutPolicies;
    private TextView mTextPolices, mTextDetailPolices;
    private ImageView mImgPolicies;

    private RelativeLayout mRelativeLayoutNotification;
    private TextView mTextNotification, mTextDetailNotification;
    private ImageView mImgNotification;

    private RelativeLayout mRelativeLayoutTeams;
    private TextView mTextTeams, mTextDetailTeams;
    private ImageView mImgTeams;

    private RelativeLayout mRelativeLayoutOther;
    private TextView mTextOther, mTextDetailOther;
    private ImageView mImgOther;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_setting_venue, container, false);


        initViews(view);
        initModels();
        return view;
    }


    @Override
    protected void initViews(View view) {

        mRelativeLayoutVenue = (RelativeLayout) view.findViewById(R.id.venueMoreDetail);
        mTextVenue = (TextView) mRelativeLayoutVenue.findViewById(R.id.tvVenueName);
        mTextDetailVenue = (TextView) mRelativeLayoutVenue.findViewById(R.id.tvVenueDetail);
        mImgVenue = (ImageView) mRelativeLayoutVenue.findViewById(R.id.ImgVenueImage);

        mRelativeLayoutOpenHours = (RelativeLayout) view.findViewById(R.id.venueOpenHour);
        mTextOpenHours = (TextView) mRelativeLayoutOpenHours.findViewById(R.id.tvVenueName);
        mTextDetailOpenHours = (TextView) mRelativeLayoutOpenHours.findViewById(R.id.tvVenueDetail);
        mImgOpenHours = (ImageView) mRelativeLayoutOpenHours.findViewById(R.id.ImgVenueImage);

        mRelativeLayoutPolicies = (RelativeLayout) view.findViewById(R.id.venuePolicies);
        mTextPolices = (TextView) mRelativeLayoutPolicies.findViewById(R.id.tvVenueName);
        mTextDetailPolices = (TextView) mRelativeLayoutPolicies.findViewById(R.id.tvVenueDetail);
        mImgPolicies = (ImageView) mRelativeLayoutPolicies.findViewById(R.id.ImgVenueImage);

        mRelativeLayoutNotification = (RelativeLayout) view.findViewById(R.id.venueNotification);
        mTextNotification = (TextView) mRelativeLayoutNotification.findViewById(R.id.tvVenueName);
        mTextDetailNotification = (TextView) mRelativeLayoutNotification.findViewById(R.id.tvVenueDetail);
        mImgNotification = (ImageView) mRelativeLayoutNotification.findViewById(R.id.ImgVenueImage);

        mRelativeLayoutTeams = (RelativeLayout) view.findViewById(R.id.venueTeams);
        mTextTeams = (TextView) mRelativeLayoutTeams.findViewById(R.id.tvVenueName);
        mTextDetailTeams = (TextView) mRelativeLayoutTeams.findViewById(R.id.tvVenueDetail);
        mImgTeams = (ImageView) mRelativeLayoutTeams.findViewById(R.id.ImgVenueImage);

        mRelativeLayoutOther = (RelativeLayout) view.findViewById(R.id.venueOther);
        mTextOther = (TextView) mRelativeLayoutOther.findViewById(R.id.tvVenueName);
        mTextDetailOther = (TextView) mRelativeLayoutOther.findViewById(R.id.tvVenueDetail);
        mImgOther = (ImageView) mRelativeLayoutOther.findViewById(R.id.ImgVenueImage);


    }

    @Override
    protected void initModels() {

        mRelativeLayoutVenue.setOnClickListener(view -> {
            Intent _intent =new Intent(getContext(), VenueDetail.class);
            _intent.putExtra(Constants.VENUE_KEY_CHECK,Constants.VENUE_VIEW);
            startActivity(_intent);
        });

        mRelativeLayoutOpenHours.setOnClickListener(view -> {});

        mRelativeLayoutPolicies.setOnClickListener(view -> {});

        mRelativeLayoutNotification.setOnClickListener(view -> {});

        mRelativeLayoutTeams.setOnClickListener(view -> {});

        mRelativeLayoutOther.setOnClickListener(view -> {});


        mTextVenue.setText(R.string.venue); mTextDetailVenue.setText(R.string.detail_venue);
        mTextOpenHours.setText(R.string.open_hours); mTextDetailOpenHours.setText(R.string.detail_open_hours);
        mTextPolices.setText(R.string.polocies); mTextDetailPolices.setText(R.string.detail_policies);
        mTextNotification.setText(R.string.notification); mTextDetailNotification.setText(R.string.detail_notification);
        mTextTeams.setText(R.string.teams); mTextDetailTeams.setText(R.string.detail_teams);
        mTextOther.setText(R.string.other); mTextDetailOther.setText(R.string.detail_other);
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
