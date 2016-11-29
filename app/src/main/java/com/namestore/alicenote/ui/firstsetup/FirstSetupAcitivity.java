package com.namestore.alicenote.ui.firstsetup;

import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.PrefUtils;
import com.namestore.alicenote.models.SubServicesObj;
import com.namestore.alicenote.network.BaseResponse;
import com.namestore.alicenote.network.reponse.FillFirstSetupResponse;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.Constants;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.network.request.FirstSetupRequest;

import com.namestore.alicenote.ui.firstsetup.fragment.HairServicesFragment;
import com.namestore.alicenote.ui.firstsetup.fragment.NailServicesFragment;
import com.namestore.alicenote.ui.firstsetup.fragment.ServicesDetailFragment;
import com.namestore.alicenote.ui.firstsetup.fragment.ShopRegisterFragment;
import com.namestore.alicenote.ui.firstsetup.fragment.ShopServicesCategoryFragment;
import com.namestore.alicenote.ui.firstsetup.fragment.ShopWorkingDayFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kienht on 10/25/16.
 */


public class FirstSetupAcitivity extends BaseActivity implements OnFragmentInteractionListener,
        OnFirstSetupActivityListener {

    public static final int SUCCESS = 1;
    public static final int CONFIGURED = 530;

    private ShopRegisterFragment mShopRegisterFragment;
    private ShopWorkingDayFragment mShopWorkingDayFragment;
    private ShopServicesCategoryFragment mShopServicesCategoryFragment;
    private HairServicesFragment mHairServicesFragment;
    private NailServicesFragment mNailServicesFragment;
    private ServicesDetailFragment mServicesDetailFragment;
    private ProgressDialog prgDialog;
    Handler handler = new Handler();
    private ArrayList<BaseFragment> fragmentsArrayList = new ArrayList<>();
    public AliceApi aliceApi;
    private FillFirstSetupResponse fillFirstSetupResponse = new FillFirstSetupResponse(); //list data lấy từ server để fill vào view của các fragment
    public FirstSetupRequest firstSetupRequest = new FirstSetupRequest(); //đối tượng để post lên server
    public ArrayList<FirstSetupRequest.Service> serviceArrayList = new ArrayList<>(); //list các services category (NAIL, HAIR,...)
    public ArrayList<SubServicesObj> subServicesObjArrayList = new ArrayList<>(); //list các sub Services để đổ vào recyclerview
    public int sizeNailArrayList; //size list Nail Services
    public int sizeHairArrayList; //size list Hair Services
    int salonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_firstsetup);
        if (!getIntent().getExtras().containsKey(Constants.FIRST_SETUP_SCREEN)) {
            Log.e("TAG", "bạn phải truyền key FIRST_SETUP_SCREEN sang màn hình này");
            finish();
            return;
        }

        aliceApi = ServiceGenerator.creatService(AliceApi.class);

        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Loading...");
        prgDialog.show();

        serviceArrayList.add(Constants.NAIL, null);
        serviceArrayList.add(Constants.HAIR, null);

        mShopRegisterFragment = new ShopRegisterFragment();
        mShopWorkingDayFragment = new ShopWorkingDayFragment();
        mShopServicesCategoryFragment = new ShopServicesCategoryFragment();
        mHairServicesFragment = new HairServicesFragment();
        mNailServicesFragment = new NailServicesFragment();
        mServicesDetailFragment = new ServicesDetailFragment();

        fragmentsArrayList.add(mShopRegisterFragment);
        fragmentsArrayList.add(mShopWorkingDayFragment);
        fragmentsArrayList.add(mShopServicesCategoryFragment);
        fragmentsArrayList.add(mHairServicesFragment);
        fragmentsArrayList.add(mNailServicesFragment);
        fragmentsArrayList.add(mServicesDetailFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mShopRegisterFragment)
                .add(R.id.container, mShopWorkingDayFragment)
                .add(R.id.container, mShopServicesCategoryFragment)
                .add(R.id.container, mHairServicesFragment)
                .add(R.id.container, mNailServicesFragment)
                .add(R.id.container, mServicesDetailFragment)
                .commit();

        salonId = PrefUtils.getInstance(FirstSetupAcitivity.this).get(PrefUtils.SALON_ID, 0);
        //salonId = 3;
        getDataFromServerToFillView(salonId);

        if (getIntent().getExtras().getInt(Constants.FIRST_SETUP_SCREEN) == Constants.KEY_SETUP_INFO_SALON) {
            showFragment(mShopRegisterFragment);
        }
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

    public void getDataFromServerToFillView(int salonId) {
        aliceApi.fillFirstSetup(salonId).enqueue(new Callback<FillFirstSetupResponse>() {
            @Override
            public void onResponse(Call<FillFirstSetupResponse> call, Response<FillFirstSetupResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                fillFirstSetupResponse = response.body();

                PrefUtils.getInstance(getApplicationContext()).set(PrefUtils.OWNER_NAME, fillFirstSetupResponse.getOwnerName());

                //Fill infor business Type list SPinner
                ArrayList<String> bsnType = new ArrayList<String>();
                bsnType.add("Select Bussiness Type");
                for (int i = 0; i < fillFirstSetupResponse.getBusinesstypes().size(); i++) {
                    bsnType.add(fillFirstSetupResponse.getBusinesstypes().get(i).getBsnTypeName());
                }
                mShopRegisterFragment.configSpinnerBsnType(bsnType);

                //Fill infor business State list SPinner
                ArrayList<String> bsnState = new ArrayList<String>();
                bsnState.add("Select State");
                for (int i = 0; i < fillFirstSetupResponse.getStates().size(); i++) {
                    bsnState.add(fillFirstSetupResponse.getStates().get(i).getStateName());
                }

                mShopRegisterFragment.configSpinnerBsnState(bsnState);

                //Fill nail services list in Nail Fragment
                ArrayList<SubServicesObj> nailArrayList = new ArrayList<SubServicesObj>();
                for (int i = 0; i < fillFirstSetupResponse.getDefaultServices().get(Constants.NAIL).getServices().size(); i++) {
                    SubServicesObj[] subServicesObjs = new SubServicesObj[fillFirstSetupResponse.getDefaultServices().
                            get(Constants.NAIL).getServices().size()];
                    subServicesObjs[i] = new SubServicesObj(fillFirstSetupResponse.getDefaultServices().get(Constants.NAIL).
                            getServices().get(i).getNameService(), true);
                    nailArrayList.add(subServicesObjs[i]);
                }
                mNailServicesFragment.updateRecyclerView(nailArrayList);

                //Fill hair services list in HairFragment
                ArrayList<SubServicesObj> hairArrayList = new ArrayList<SubServicesObj>();
                for (int i = 0; i < fillFirstSetupResponse.getDefaultServices().get(Constants.HAIR).getServices().size(); i++) {
                    SubServicesObj[] subServicesObjs = new SubServicesObj[fillFirstSetupResponse.getDefaultServices().
                            get(Constants.HAIR).getServices().size()];
                    subServicesObjs[i] = new SubServicesObj(fillFirstSetupResponse.getDefaultServices().get(Constants.HAIR).
                            getServices().get(i).getNameService(), true);
                    hairArrayList.add(subServicesObjs[i]);

                }
                mHairServicesFragment.updateRecyclerView(hairArrayList);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prgDialog.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFailure(Call<FillFirstSetupResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });
    }

    @Override
    public void showShopRegisterFragment() {
        showFragment(mShopRegisterFragment);
    }

    @Override
    public void showWorkingDayFragment() {
        showFragment(mShopWorkingDayFragment);
    }

    @Override
    public void showShopServicesCategoryFragment() {
        showFragment(mShopServicesCategoryFragment);
    }

    @Override
    public void showNailServiceFragment() {
        showFragment(mNailServicesFragment);
    }

    @Override
    public void showHairServiceFragment() {
        showFragment(mHairServicesFragment);
    }

    @Override
    public void showServicesDetailFragment() {
        showFragment(mServicesDetailFragment);
    }

    /**
     * @param allSubServices truyền list các sub services sau khi cấu hình ServicesCategory vào recyclerview của servicesDetailFragment
     */
    public void updateListAllSubServices(ArrayList<SubServicesObj> allSubServices) {
        mServicesDetailFragment.updateRecyclerView(allSubServices);
    }

    /**
     * thay đổi list các sub services sau khi thêm hoặc xoá SubService trong ServicesCategory vào recyclerview của servicesDetailFragment
     */
    public void swap() {
        mServicesDetailFragment.swapDataRecyclerView(subServicesObjArrayList);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onViewClick(String tag) {
        switch (tag) {
            case Constants.DASHBOARD_SCREEN:
                prgDialog.show();
                aliceApi.requestFirstSetupSalon(salonId, firstSetupRequest).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            switch (response.body().getStatus()) {
                                case SUCCESS:
                                    prgDialog.dismiss();
                                    Intent intent = new Intent(FirstSetupAcitivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                    finish();
                                    break;
                                case CONFIGURED:
                                    prgDialog.dismiss();
                                    AppUtils.showNoticeDialog(FirstSetupAcitivity.this, "CONFIGURED");
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        if (call.isCanceled()) {
                            AppUtils.logE("request was cancelled");
                        } else {
                            AppUtils.logE("FAILED " + t.getLocalizedMessage());
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onViewClick(String tag, Object object) {
        switch (tag) {
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            default:
                break;
        }

    }
}
