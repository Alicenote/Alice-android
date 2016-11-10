package com.namestore.alicenote.ui.art;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.ui.art.interfaces.OnArtActivityListener;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;

import com.namestore.alicenote.ui.art.fragment.ArtGroupFragment;
import com.namestore.alicenote.ui.art.fragment.CreatSubArtFragment;
import com.namestore.alicenote.ui.art.fragment.EditSubArtFragment;
import com.namestore.alicenote.ui.art.fragment.SubArtFragment;
import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtActivity extends BaseActivity implements OnFragmentInteractionListener,
        OnArtActivityListener {

    ArtGroupFragment artGroupFragment;
    SubArtFragment subArtFragment;
    EditSubArtFragment editSubArtFragment;
    CreatSubArtFragment creatSubArtFragment;

    ArrayList<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_art);

//        if (!getIntent().getExtras().containsKey(Constants.FIRST_SETUP_SCREEN)) {
//            Log.e("TAG", "bạn phải truyền key FIRST_SETUP_SCREEN sang màn hình này");
//            finish();
//            return;
//        }

        artGroupFragment = new ArtGroupFragment();
        subArtFragment = new SubArtFragment();
        editSubArtFragment = new EditSubArtFragment();
        creatSubArtFragment = new CreatSubArtFragment();

        fragments.add(artGroupFragment);
        fragments.add(subArtFragment);
        fragments.add(editSubArtFragment);
        fragments.add(creatSubArtFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, artGroupFragment)
                .add(R.id.container, subArtFragment)
                .add(R.id.container, editSubArtFragment)
                .add(R.id.container, creatSubArtFragment)
                .commit();

        if (getIntent().getExtras().getInt(Constants.FIRST_SETUP_SCREEN) == Constants.KEY_SETUP_INFO_SALON) {
            showFragment(artGroupFragment);
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
    public void showArtGroup() {
        showFragment(artGroupFragment);
    }

    @Override
    public void showSubArt() {
        showFragment(subArtFragment);
    }

    @Override
    public void editSubArt() {
        showFragment(editSubArtFragment);
    }

    @Override
    public void creatSubArt() {
        showFragment(creatSubArtFragment);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }
}
