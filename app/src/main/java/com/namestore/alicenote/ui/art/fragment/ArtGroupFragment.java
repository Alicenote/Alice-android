package com.namestore.alicenote.ui.art.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.art.ArtActivity;
import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.ui.art.adapter.ArtGroupAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.models.ArtObj;
import com.namestore.alicenote.common.AppUtils;

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtGroupFragment extends BaseFragment {

    ArrayList<ArtObj> artArrayList;
    RecyclerView recyclerViewArt;
    ArtGroupAdapter artGroupAdapter;
    ArtActivity artActivity;
    Button mButtonAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_art_group, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
    }

    @Override
    protected void initViews(View view) {
        recyclerViewArt = (RecyclerView) view.findViewById(R.id.list_art_group);
        recyclerViewArt.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewArt.setHasFixedSize(true);

        mButtonAdd = (Button) view.findViewById(R.id.action_bar).findViewById(R.id.button_add_art);
        mButtonAdd.setOnClickListener(this);
    }

    @Override
    protected void initModels() {
        artArrayList = new ArrayList<>();

        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        arrayList.add(new Pair<String, Integer>("SPRING", R.drawable.spring));
        arrayList.add(new Pair<String, Integer>("SUMMER", R.drawable.summer));
        arrayList.add(new Pair<String, Integer>("AUTUMN", R.drawable.autumn));
        arrayList.add(new Pair<String, Integer>("WINTER", R.drawable.winter));

        for (int i = 0; i < arrayList.size(); i++) {
            ArtObj artGroup = new ArtObj();
            artGroup.setNameArt(arrayList.get(i).first);
            artGroup.setDrawableArt(arrayList.get(i).second);
            this.artArrayList.add(artGroup);
        }
        artGroupAdapter = new ArtGroupAdapter(artArrayList);

        recyclerViewArt.setAdapter(artGroupAdapter);
        recyclerViewArt.setItemAnimator(new DefaultItemAnimator());
        recyclerViewArt.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        AppUtils.showShortToast(getActivity(), "OK");
                    }
                }));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_add_art:
                AppUtils.showNoticeDialog(getActivity(), "ADD ART GROUP");
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ArtActivity) {
            this.artActivity = (ArtActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.artActivity = (ArtActivity) activity;
        }
    }

}
