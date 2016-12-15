package com.namestore.alicenote.ui.venue.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kyleduo.switchbutton.SwitchButton;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.network.ObservableManager;
import com.namestore.alicenote.network.reponse.VenueViewResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

import rx.Subscriber;

/**
 * Created by nhocnhinho on 06/12/2016.
 */

public class VenueViewEditFragment extends BaseFragment {
    private LinearLayout mToolbar;
    private Button mToolbarBack;
    private Button mToolbarSave;
    private TextView mToolbarTitle;
    private TextView mVenueEditNameSalon;
    private TextView mTvVenueEditInforSalon;

    private RelativeLayout mVenueEditFormPostCode;
    private RelativeLayout mVenueEditFormPhone;
    private RelativeLayout mVenueEditFormEmail;
    private RelativeLayout mVenueEditProduction;

    private TextView mTvVenueEditClickNameSalon;
    private RelativeLayout mRelativeViewEditLocation;
    private ImageView mVenueEditImageCover;

    private TextView mTvVenueEditInforSalonClick;
    private Button mVenueEditButtonDeactive;
    private SwitchButton mVenueEditButtonVisibility;

    private TextView mVenueEditFormPostCodeName;
    private String mFormPostCodeName = "Post Code";
    private TextView mVenueEditFormPostCodeValue;

    private TextView mVenueEditFormPhoneName;
    private String mFormPhoneName = "Phone";
    private TextView mVenueEditFormPhoneValue;

    private TextView mVenueEditFormEmailName;
    private String mFormEmailName = "Email ";
    private TextView mVenueEditFormEmailValue;

    private ProgressDialog prgDialog;
    private String mAboutSalon,mNameSalon;

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
        mToolbarBack = (Button) mToolbar.findViewById(R.id.toolbar_backpress);
        mToolbarSave = (Button) mToolbar.findViewById(R.id.toolbar_edit);

        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mVenueEditNameSalon = (TextView) view.findViewById(R.id.mVenueEditNameSalon);
        mTvVenueEditClickNameSalon = (TextView) view.findViewById(R.id.mTvVenueEditClickNameSalon);
        mRelativeViewEditLocation = (RelativeLayout) view.findViewById(R.id.mRelativeViewEditLocation);
        mVenueEditImageCover = (ImageView) view.findViewById(R.id.mVenueEditImageCover);
        mTvVenueEditInforSalon = (TextView) view.findViewById(R.id.mTvVenueEditInforSalon);

        mVenueEditFormPostCode = (RelativeLayout) view.findViewById(R.id.mVenueEditFormPostCode);
        mVenueEditFormPhone = (RelativeLayout) view.findViewById(R.id.mVenueEditFormPhone);
        mVenueEditFormEmail = (RelativeLayout) view.findViewById(R.id.mVenueEditFormEmail);

        mVenueEditProduction = (RelativeLayout) view.findViewById(R.id.mVenueEditProduction);
        mTvVenueEditInforSalonClick = (TextView) view.findViewById(R.id.mTvVenueEditInforSalonClick);
        mVenueEditButtonVisibility = (SwitchButton) view.findViewById(R.id.mVenueEditButtonVisibility);
        mVenueEditButtonDeactive = (Button) view.findViewById(R.id.mVenueEditButtonDeactive);


        mVenueEditFormPostCodeName = (TextView) mVenueEditFormPostCode.findViewById(R.id.tvViewVenueName);
        mVenueEditFormEmailName = (TextView) mVenueEditFormEmail.findViewById(R.id.tvViewVenueName);
        mVenueEditFormPhoneName = (TextView) mVenueEditFormPhone.findViewById(R.id.tvViewVenueName);

        mVenueEditFormPhoneValue = (TextView) mVenueEditFormPhone.findViewById(R.id.tvViewVenueValue);
        mVenueEditFormPostCodeValue = (TextView) mVenueEditFormPostCode.findViewById(R.id.tvViewVenueValue);
        mVenueEditFormEmailValue = (TextView) mVenueEditFormEmail.findViewById(R.id.tvViewVenueValue);



        prgDialog = new ProgressDialog(getContext());
        prgDialog.setMessage("Loading...");
        prgDialog.setCancelable(false);
    }

    @Override
    protected void initModels() {
        mToolbarTitle.setText(R.string.your_venue);
        ViewUtils.setAndScaleDrawableButton(mToolbarBack, R.drawable.icon_back, 0, 0, 0, 0.8);
        mToolbarBack.setOnClickListener(view -> {
            if (mActivity instanceof OnSettingVenueListener) {
                ((OnSettingVenueListener) mActivity).showVenueView();
            }
        });
        mToolbarSave.setText("Save");
        mToolbarSave.setOnClickListener(view -> {

            if (mActivity instanceof OnSettingVenueListener) {
                ((OnSettingVenueListener) mActivity).showVenueView();
            }
        });

        mVenueEditButtonVisibility.setOnClickListener(view -> {});
                mTvVenueEditInforSalonClick.setOnClickListener(view -> {});
        mTvVenueEditClickNameSalon.setOnClickListener(view -> {});

        mVenueEditFormPostCodeName.setText(mFormPostCodeName);
                mVenueEditFormEmailName.setText(mFormPhoneName);
        mVenueEditFormPhoneName.setText(mFormEmailName);

    }

    public void getDataForVenueViewEdit(int salonId, int location, VenueViewFragment context) {
        prgDialog.show();
        ObservableManager.VenueView(salonId, location).subscribe(new Subscriber<VenueViewResponse>() {
            @Override
            public void onCompleted() {
                AppUtils.logE("VenueView  Completed");
                prgDialog.dismiss();

            }

            @Override
            public void onError(Throwable t) {
                AppUtils.logE("FAILED " + t.getLocalizedMessage());
                prgDialog.dismiss();
            }

            @Override
            public void onNext(VenueViewResponse venueViewResponse) {

                Glide.with(context).load(venueViewResponse.getData().getLocations()
                        .getImageCover().toString()).into(mVenueEditImageCover);

                mNameSalon =venueViewResponse.getData().getLocations().getNameSalon();
                mVenueEditNameSalon.setText(mNameSalon);
                mAboutSalon =venueViewResponse.getData().getLocations().getDescription();
                mTvVenueEditInforSalon.setText(mAboutSalon);

                mVenueEditFormPhoneValue.setText(venueViewResponse.getData().getLocations().getTelephone());
                        mVenueEditFormPostCodeValue.setText(venueViewResponse.getData().getLocations().getPostcode());
                mVenueEditFormEmailValue.setText(venueViewResponse.getData().getLocations().getEmail());
            }
        });

    }


}
