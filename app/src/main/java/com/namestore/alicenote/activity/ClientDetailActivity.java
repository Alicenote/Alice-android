package com.namestore.alicenote.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.namestore.alicenote.R;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.fragment.AddClientFragment;
import com.namestore.alicenote.fragment.ClientFragment;
import com.namestore.alicenote.fragment.DelClientFragment;
import com.namestore.alicenote.fragment.EditClientFragment;
import com.namestore.alicenote.fragment.ViewClientFragment;
import com.namestore.alicenote.interfaces.OnClientDetailListener;

import java.util.ArrayList;

public class ClientDetailActivity  extends CoreActivity implements View.OnClickListener,OnClientDetailListener {
    ArrayList<CoreFragment> fragments = new ArrayList<>();
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

        if (getIntent().getExtras().getString(Constants.KEY_CHECK_CLIENT) == Constants.ADD_CLIENT) {
            showFragment(mAddClientFragment);
        }
        if (getIntent().getExtras().getString(Constants.KEY_CHECK_CLIENT) == Constants.DEL_CLIENT) {
            showFragment(mDelClientFragment);
        }
        if (getIntent().getExtras().getString(Constants.KEY_CHECK_CLIENT) == Constants.VIEW_CLIENT) {
            showFragment(mViewClientFragment);
        }
        if (getIntent().getExtras().getString(Constants.KEY_CHECK_CLIENT) == Constants.EDIT_CLIENT) {
            showFragment(mEditClientFragment);
        }




    }
    public void showFragment(Fragment fragmentToShow) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (CoreFragment _fragment : fragments) {
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
