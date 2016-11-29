package com.namestore.alicenote.ui.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.service.fragment.CreatSeviceFragment;
import com.namestore.alicenote.ui.service.fragment.ViewEditDelSeviceFragment;
import com.namestore.alicenote.ui.service.interfaces.OnServiceDetailListener;

public class ServiceDetailActivity extends AppCompatActivity implements OnServiceDetailListener{
    public String mKeyCheckService;
    public int mId;
    public Toolbar mToolBar;
    private ViewEditDelSeviceFragment mViewEditDelSeviceFragment;
    private CreatSeviceFragment mCreatSeviceFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mKeyCheckService = getIntent().getExtras()
                .getString(Constants.KEY_CHECK_SERVICE);

        mId = getIntent().getExtras()
                .getInt(Constants.KEY_ID_SERVICE);

        mViewEditDelSeviceFragment = new ViewEditDelSeviceFragment();
        mCreatSeviceFragment = new CreatSeviceFragment();


        if (mKeyCheckService.equalsIgnoreCase(Constants.ADD_SERVICE)) {

            showServiceCreate();

        }
        if (mKeyCheckService.equalsIgnoreCase(Constants.EDIT_SERVICE)) {

            showViewEditDelService();
        }

        if (mKeyCheckService.equalsIgnoreCase(Constants.VIEW_SERVICE)) {
            showViewEditDelService();

        }


    }

    @Override
    public void showViewEditDelService() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mViewEditDelSeviceFragment).commit();
    }

    @Override
    public void showServiceCreate() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mCreatSeviceFragment).commit();
    }
}
