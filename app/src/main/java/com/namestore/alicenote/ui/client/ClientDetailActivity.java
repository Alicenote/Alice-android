package com.namestore.alicenote.ui.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.fragment.AddEditDelClientFragment;
import com.namestore.alicenote.ui.client.fragment.ViewClientFragment;
import com.namestore.alicenote.ui.client.interfaces.OnClientDetailListener;

import java.util.ArrayList;

public class ClientDetailActivity extends BaseActivity
        implements View.OnClickListener, OnClientDetailListener, OnFragmentInteractionListener {
    ArrayList<BaseFragment> fragments = new ArrayList<>();
    //  private ClientFragment mClientFragment;
    public AddEditDelClientFragment mAddEditDelClientFragment;

    private ViewClientFragment mViewClientFragment;
    public String mKeyCheckClient;
    public int mId;
    public Toolbar mToolBar;
    public Intent returnIntent= new  Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_client_detail);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mKeyCheckClient = getIntent().getExtras()
                .getString(Constants.KEY_CHECK_CLIENT);

        mId = getIntent().getExtras()
                .getInt(Constants.KEY_ID);

        mAddEditDelClientFragment = new AddEditDelClientFragment();

        mViewClientFragment = new ViewClientFragment();
      /*  fragments.add(mAddEditDelClientFragment);
    //    fragments.add(mClientFragment);
        *//**//*fragments.add(mDelClientFragment);
        fragments.add(mEditClientFragment);
        fragments.add(mViewClientFragment);*//**//*
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, mAddEditDelClientFragment)
           //     .add(R.id.container, mClientFragment)
  *//**//*              .add(R.id.container, mDelClientFragment)
                .add(R.id.container, mEditClientFragment)
                .add(R.id.container, mViewClientFragment)*//**//*

                .commit();*/

        if (mKeyCheckClient.equalsIgnoreCase(Constants.ADD_CLIENT)) {
            mToolBar.setTitle("Add Client");
            showAddEditDelClient();

        }
        if (mKeyCheckClient.equalsIgnoreCase(Constants.EDIT_CLIENT)) {
            mToolBar.setTitle("Edit Client");
            showAddEditDelClient();
        }

        if (mKeyCheckClient.equalsIgnoreCase(Constants.VIEW_CLIENT)) {
            showClientView();
            mToolBar.setTitle("View Client");
        }

        returnIntent.putExtra("result", 1);
       setResult(Activity.RESULT_OK,returnIntent);
    }

    /*  public void showFragment(Fragment fragmentToShow) {
          FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
          for (BaseFragment _fragment : fragments) {
              if (_fragment == fragmentToShow) {
                  transaction.show(fragmentToShow);
              } else {
                  transaction.hide(_fragment);
              }
          }
          transaction.commit();
      }*/
    @Override
    public void onClick(View view) {

    }

    @Override
    public void showAddEditDelClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mAddEditDelClientFragment).commit();

    }

    @Override
    public void showClientView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mViewClientFragment).commit();
    }


    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }


}
