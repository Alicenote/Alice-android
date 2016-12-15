package com.namestore.alicenote.ui.venue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

/**
 * Created by nhocnhinho on 06/12/2016.
 */

public class VenueViewEditFragment extends BaseFragment {
    private LinearLayout mToolbar;
    private TextView mToolbarBack;
    private TextView mToolbarSave;
    private TextView mToolbarTitle;

    private RelativeLayout mVenueEditFormPostCode;
    private RelativeLayout mVenueEditFormPhone;
    private RelativeLayout mVenueEditFormEmail;
    private RelativeLayout mVenueEditProduction;

    private TextView mTvVenueEditClickNameSalon;
    private RelativeLayout mRelativeViewEditLocation;
    private ImageView mVenueEditImageCover;

    private TextView mTvVenueEditInforSalonClick;
    private Button mVenueEditButtonDeactive;
    private Button mVenueEditButtonVisibility;

    private TextView mVenueEditFormPostCodeName;
    private String mFormPostCodeName = "Post Code";
    private TextView mVenueEditFormPostCodeValue;

    private TextView mVenueEditFormPhoneName;
    private String mFormPhoneName = "Phone";
    private TextView mVenueEditFormPhoneValue;

    private TextView mVenueEditFormEmailName;
    private String mFormEmailName = "Email ";
    private TextView mVenueEditFormEmailValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_venue_view_edit, container, false);
        mToolbar = (LinearLayout) view.findViewById(R.id.toolbar);


        initViews(view);
        initModels();
        return view;

    }

    @Override
    protected void initViews(View view) {
        mToolbarBack = (TextView) mToolbar.findViewById(R.id.toolbar_backpress);
        mToolbarSave = (TextView) mToolbar.findViewById(R.id.toolbar_edit);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        mTvVenueEditClickNameSalon = (TextView) view.findViewById(R.id.mTvVenueEditClickNameSalon);
        mRelativeViewEditLocation = (RelativeLayout) view.findViewById(R.id.mRelativeViewEditLocation);
        mVenueEditImageCover = (ImageView) view.findViewById(R.id.mVenueEditImageCover);
        mVenueEditFormPostCode = (RelativeLayout) view.findViewById(R.id.mVenueEditFormPostCode);
        mVenueEditFormPhone = (RelativeLayout) view.findViewById(R.id.mVenueEditFormPhone);
        mVenueEditFormEmail = (RelativeLayout) view.findViewById(R.id.mVenueEditFormEmail);
        mVenueEditProduction = (RelativeLayout) view.findViewById(R.id.mVenueEditProduction);
        mTvVenueEditInforSalonClick = (TextView) view.findViewById(R.id.mTvVenueEditInforSalonClick);
        mVenueEditButtonVisibility= (Button) view.findViewById(R.id.mVenueEditButtonVisibility);
        mVenueEditButtonDeactive = (Button) view.findViewById(R.id.mVenueEditButtonDeactive);



    }

    @Override
    protected void initModels() {
        mToolbarTitle.setText(R.string.your_venue);
        mToolbarBack.setOnClickListener(view -> getActivity().finish());
        mToolbarSave.setText("Save");
        mToolbarSave.setOnClickListener(view -> {

            if (mActivity instanceof OnSettingVenueListener) {
                ((OnSettingVenueListener) mActivity).showVenueView();
            }
        });
    }
}
