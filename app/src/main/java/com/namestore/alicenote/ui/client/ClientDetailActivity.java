package com.namestore.alicenote.ui.client;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.fragment.AddClientFragment;
import com.namestore.alicenote.ui.client.fragment.DelClientFragment;
import com.namestore.alicenote.ui.client.fragment.EditClientFragment;
import com.namestore.alicenote.ui.client.fragment.ViewClientFragment;
import com.namestore.alicenote.ui.client.interfaces.OnClientDetailListener;
import java.util.ArrayList;

public class ClientDetailActivity extends BaseActivity
        implements View.OnClickListener, OnClientDetailListener {
    ArrayList<BaseFragment> fragments = new ArrayList<>();
  //  private ClientFragment mClientFragment;
    private AddClientFragment mAddClientFragment;
    private DelClientFragment mDelClientFragment;
    private EditClientFragment mEditClientFragment;
    private ViewClientFragment mViewClientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_client_detail);

        mAddClientFragment = new AddClientFragment();
    //    mClientFragment= new ClientFragment();
        mDelClientFragment =new DelClientFragment();
        mEditClientFragment =new EditClientFragment();
        mViewClientFragment =new ViewClientFragment();
        fragments.add(mAddClientFragment);
    //    fragments.add(mClientFragment);
        fragments.add(mDelClientFragment);
        fragments.add(mEditClientFragment);
        fragments.add(mViewClientFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mAddClientFragment)
           //     .add(R.id.container, mClientFragment)
                .add(R.id.container, mDelClientFragment)
                .add(R.id.container, mEditClientFragment)
                .add(R.id.container, mViewClientFragment)

                .commit();

        if (getIntent().getExtras()
                .getString(Constants.KEY_CHECK_CLIENT)
                .equalsIgnoreCase(Constants.ADD_CLIENT)) {
            showFragment(mAddClientFragment);
        }
        if (getIntent().getExtras()
                .getString(Constants.KEY_CHECK_CLIENT)
                .equalsIgnoreCase(Constants.DEL_CLIENT)) {
            showFragment(mDelClientFragment);
        }
        if (getIntent().getExtras()
                .getString(Constants.KEY_CHECK_CLIENT)
                .equalsIgnoreCase(Constants.VIEW_CLIENT)) {
            showFragment(mViewClientFragment);
        }
        if (getIntent().getExtras()
                .getString(Constants.KEY_CHECK_CLIENT)
                .equalsIgnoreCase(Constants.EDIT_CLIENT)) {
            showFragment(mEditClientFragment);
        }




    }
    public void showFragment(Fragment fragmentToShow) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (BaseFragment _fragment : fragments) {
            if (_fragment == fragmentToShow) {
                transaction.show(fragmentToShow);
            } else {
                transaction.hide(_fragment);
            }
        }
        transaction.commit();
    }
    @Override
    public void onClick(View view) {

    }

    @Override
    public void showAddClient() {
        showFragment(mAddClientFragment);

    }

    @Override
    public void showClientView() {
        showFragment(mViewClientFragment);
    }

    @Override
    public void showEditClient() {
        showFragment(mEditClientFragment);

    }

    @Override
    public void showDeleteClient() {
        showFragment(mDelClientFragment);
    }

    /*@Override
    public void showClient() {
        showFragment(mClientFragment);
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_client, menu);
        return true;
    }
}
