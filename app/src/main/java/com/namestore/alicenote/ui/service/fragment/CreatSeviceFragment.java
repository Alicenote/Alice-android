package com.namestore.alicenote.ui.service.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.BaseFragment;

/**
 * Created by nhocnhinho on 24/11/2016.
 */

public class CreatSeviceFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fm_view_edit_del_service, container, false);

        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initModels() {

    }
}
