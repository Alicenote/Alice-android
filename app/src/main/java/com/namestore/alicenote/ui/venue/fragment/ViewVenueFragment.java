package com.namestore.alicenote.ui.venue.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.vision.barcode.Barcode;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.network.ObservableManager;
import com.namestore.alicenote.network.reponse.VenueViewResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.ui.venue.VenueDetailActivity;
import com.namestore.alicenote.ui.venue.interfaces.OnSettingVenueListener;

import rx.Subscriber;

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


    private TextView mVenueTvNameSalon;
    private TextView mVenueTvVote;
    private TextView mVenueTvReviews;
    private ImageView mVenueBossImage;
    private TextView mVenueTvUnderReviews;

    private ImageView mVenueImageClient;
    private TextView mVenueTvClientName;
    private TextView mVenueTvClientDate;
    private TextView mVenueTvComment;

    private Button mVenueBtnViewAll;

   // private TextView mVenueTvLocation;
    private TextView mVenueTvInforSalon;

    private RelativeLayout mVenueFormPostCode;
    private RelativeLayout mVenueFormPhone;
    private RelativeLayout mVenueFormEmail;
    private RelativeLayout mVenueFormWebsite;

    private TextView mVenueFormPostCodeName;
    private String mFormPostCodeName = "Post Code";
    private TextView mVenueFormPostCodeValue;

    private TextView mVenueFormPhoneName;
    private String mFormPhoneName = "Phone";
    private TextView mVenueFormPhoneValue;

    private TextView mVenueFormEmailName;
    private String mFormEmailName = "Email ";
    private TextView mVenueFormEmailValue;

    private TextView mVenueFormWebsiteName;
    private String mFormWebsiteName = "Website ";
    private TextView mVenueFormWebsiteValue;
    private ProgressDialog prgDialog;

    private int mMapX,mMapY;
    private String mLocation;

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


        mVenueTvNameSalon = (TextView) view.findViewById(R.id.mVenueTvNameSalon);
        mVenueTvVote = (TextView) view.findViewById(R.id.mVenueTvVote);
        mVenueTvReviews = (TextView) view.findViewById(R.id.mVenueTvReviews);
        mVenueBossImage = (ImageView) view.findViewById(R.id.mVenueBossImage);
        mVenueTvUnderReviews = (TextView) view.findViewById(R.id.mVenueTvUnderReviews);

        mVenueImageClient = (ImageView) view.findViewById(R.id.mVenueImageClient);
        mVenueTvClientName = (TextView) view.findViewById(R.id.mVenueTvClientName);
        mVenueTvClientDate = (TextView) view.findViewById(R.id.mVenueTvClientDate);
        mVenueTvComment = (TextView) view.findViewById(R.id.mVenueTvComment);

        mVenueBtnViewAll = (Button) view.findViewById(R.id.mVenueBtnViewAll);

     //   mVenueTvLocation = (TextView) view.findViewById(R.id.mVenueTvLocation);
        mVenueTvInforSalon = (TextView) view.findViewById(R.id.mVenueTvInforSalon);

        mVenueFormPostCode = (RelativeLayout) view.findViewById(R.id.mVenueFormPostCode);
        mVenueFormPhone = (RelativeLayout) view.findViewById(R.id.mVenueFormPhone);
        mVenueFormEmail = (RelativeLayout) view.findViewById(R.id.mVenueFormEmail);
        mVenueFormWebsite = (RelativeLayout) view.findViewById(R.id.mVenueFormWebsite);


        mVenueFormPostCodeName = (TextView) mVenueFormPostCode.findViewById(R.id.tvViewVenueName);
        mVenueFormPhoneName = (TextView) mVenueFormPhone.findViewById(R.id.tvViewVenueName);
        mVenueFormEmailName = (TextView) mVenueFormEmail.findViewById(R.id.tvViewVenueName);
        mVenueFormWebsiteName = (TextView) mVenueFormWebsite.findViewById(R.id.tvViewVenueName);

        mVenueFormPostCodeValue = (TextView) mVenueFormPostCode.findViewById(R.id.tvViewVenueValue);
        mVenueFormPhoneValue = (TextView) mVenueFormPhone.findViewById(R.id.tvViewVenueValue);
        mVenueFormEmailValue = (TextView) mVenueFormEmail.findViewById(R.id.tvViewVenueValue);
        mVenueFormWebsiteValue = (TextView) mVenueFormWebsite.findViewById(R.id.tvViewVenueValue);





        prgDialog = new ProgressDialog(getContext());
        prgDialog.setMessage("Loading...");
        prgDialog.setCancelable(false);

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

        mVenueFormPostCodeName.setText(mFormPostCodeName);
        mVenueFormEmailName.setText(mFormEmailName);
        mVenueFormWebsiteName.setText(mFormWebsiteName);
        mVenueFormPhoneName.setText(mFormPhoneName);

        getDataForViewVenue(1, 1);

        mSupportMapFragment = SupportMapFragment.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mVenueGoogleMap, mSupportMapFragment).commit();
        mSupportMapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng yourVenue = new LatLng(mMapX, mMapY);
        MarkerOptions option = new MarkerOptions();
        option.position(yourVenue);
        option.title("Your Venue").snippet(mLocation);
        option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        Marker maker = googleMap.addMarker(option);
        maker.showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourVenue, 18));
    }


    public void getDataForViewVenue(int salonId, int location) {
        prgDialog.show();
        ObservableManager.VenueView(salonId, location).subscribe(new Subscriber<VenueViewResponse>() {
            @Override
            public void onCompleted() {
                AppUtils.logE("VenueView  Completed");


            }

            @Override
            public void onError(Throwable t) {
                AppUtils.logE("FAILED " + t.getLocalizedMessage());

            }

            @Override
            public void onNext(VenueViewResponse venueViewResponse) {

                mVenueTvVote.setText(String.valueOf(venueViewResponse.getData().getLocations().getPoint()));
                mVenueTvReviews.setText(String.valueOf(venueViewResponse.getData().getLocations().getNo_review()) + " Reviews");
                mVenueTvUnderReviews.setText(String.valueOf(venueViewResponse.getData().getLocations().getNo_review()) + " Reviews");
                mVenueTvClientName.setText(venueViewResponse.getData().getLocations().getClient().getName().toString());
                mVenueTvClientDate.setText(venueViewResponse.getData().getLocations().getClient().getUpdated().toString());
                mVenueTvComment.setText(venueViewResponse.getData().getLocations().getClient().getComment().toString());
             //   mVenueTvLocation.setText(venueViewResponse.getData().getLocations().getAddress().toString());

                mVenueTvInforSalon.setText(venueViewResponse.getData().getLocations().getDescription().toString());

                mVenueFormPostCodeValue.setText(String.valueOf(venueViewResponse.getData().getLocations().getPostcode().toString()));
                mVenueFormPhoneValue.setText(String.valueOf(venueViewResponse.getData().getLocations().getTelephone().toString()));
                mVenueFormEmailValue.setText(venueViewResponse.getData().getLocations().getEmail().toString());
                mVenueFormWebsiteValue.setText(venueViewResponse.getData().getLocations().getWebsite().toString());

                mLocation= venueViewResponse.getData().getLocations().getAddress();
                mLocation= venueViewResponse.getData().getLocations().getAddress();
                mMapX=Integer.parseInt(venueViewResponse.getData().getLocations().getLatitude());
                mMapY=Integer.parseInt(venueViewResponse.getData().getLocations().getLongitude());

                prgDialog.dismiss();
            }
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
