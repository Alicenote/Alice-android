package com.namestore.alicenote.ui.client;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.fragment.AddEditClientFragment;
import com.namestore.alicenote.ui.client.fragment.DelClientFragment;
import com.namestore.alicenote.ui.client.fragment.ViewClientFragment;
import com.namestore.alicenote.ui.client.interfaces.OnClientDetailListener;
import com.namestore.alicenote.ui.signinup.fragment.LoginFragment;

import java.util.ArrayList;

public class ClientDetailActivity extends BaseActivity
        implements View.OnClickListener, OnClientDetailListener, OnFragmentInteractionListener {
    ArrayList<BaseFragment> fragments = new ArrayList<>();
    //  private ClientFragment mClientFragment;
    public AddEditClientFragment mAddEditClientFragment;
    private DelClientFragment mDelClientFragment;
    private ViewClientFragment mViewClientFragment;
    private String mKeyCheckClient;
    public int mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_client_detail);

        mKeyCheckClient = getIntent().getExtras().getString(Constants.KEY_CHECK_CLIENT);

        mId = getIntent().getExtras().getInt(Constants.KEY_ID);

        mAddEditClientFragment = new AddEditClientFragment();

        mDelClientFragment = new DelClientFragment();

        mViewClientFragment = new ViewClientFragment();

      /*  fragments.add(mAddEditClientFragment);
    //    fragments.add(mClientFragment);
        *//**//*fragments.add(mDelClientFragment);
        fragments.add(mEditClientFragment);
        fragments.add(mViewClientFragment);*//**//*
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, mAddEditClientFragment)
           //     .add(R.id.container, mClientFragment)
  *//**//*              .add(R.id.container, mDelClientFragment)
                .add(R.id.container, mEditClientFragment)
                .add(R.id.container, mViewClientFragment)*//**//*

                .commit();*/

//        if (mKeyCheckClient.equalsIgnoreCase(Constants.ADD_CLIENT)) {
//            showAddClient();
//        }
//        if (mKeyCheckClient.equalsIgnoreCase(Constants.DEL_CLIENT)) {
//            showDeleteClient();
//        }
//        if (mKeyCheckClient.equalsIgnoreCase(Constants.VIEW_CLIENT)) {
//            showClientView();
//        }
//        if (mKeyCheckClient.equalsIgnoreCase(Constants.EDIT_CLIENT)) {
//
//        }
       // showAddClient();
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
    public void showAddClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mAddEditClientFragment).commit();

    }

    @Override
    public void showClientView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mViewClientFragment).commit();
    }


    @Override
    public void showDeleteClient() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mDelClientFragment).commit();
    }


    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }


}
