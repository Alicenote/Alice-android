package com.namestore.alicenote.ui.art;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.models.ArtObj;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.art.adapter.ArtGroupAdapter;


import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtGroupActivity extends BaseActivity implements ArtGroupAdapter.OnArtItemClickListener {

    ArrayList<ArtObj> artArrayList;
    ArrayList<Pair<String, Integer>> arrayListData;
    RecyclerView recyclerViewArt;
    ArtGroupAdapter artGroupAdapter;
    Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_art_group);

//        if (!getIntent().getExtras().containsKey(Constants.FIRST_SETUP_SCREEN)) {
//            Log.e("TAG", "bạn phải truyền key FIRST_SETUP_SCREEN sang màn hình này");
//            finish();
//            return;
//        }
        initViews();
        initModels();
    }

    protected void initViews() {
        recyclerViewArt = (RecyclerView) findViewById(R.id.list_art_group);
        recyclerViewArt.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArt.setHasFixedSize(true);

        mButtonAdd = (Button) findViewById(R.id.action_bar).findViewById(R.id.button_add_art);
        mButtonAdd.setOnClickListener(this);
    }

    protected void initModels() {
        artArrayList = new ArrayList<>();

        arrayListData = new ArrayList<>();
        arrayListData.add(new Pair<String, Integer>("SPRING", R.drawable.spring));
        arrayListData.add(new Pair<String, Integer>("SUMMER", R.drawable.summer));
        arrayListData.add(new Pair<String, Integer>("AUTUMN", R.drawable.autumn));
        arrayListData.add(new Pair<String, Integer>("WINTER", R.drawable.winter));

        for (int i = 0; i < arrayListData.size(); i++) {
            ArtObj artGroup = new ArtObj();
            artGroup.setNameArt(arrayListData.get(i).first);
            artGroup.setDrawableArt(arrayListData.get(i).second);
            this.artArrayList.add(artGroup);
        }
        artGroupAdapter = new ArtGroupAdapter(this, artArrayList, false);

        recyclerViewArt.setAdapter(artGroupAdapter);
        recyclerViewArt.setItemAnimator(new DefaultItemAnimator());
    }

    public void moveSubArtAct(String string) {
        Intent mIntent = new Intent(this, SubArtActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mIntent.putExtra(Constants.ART_SCREEN, string);
        startActivity(mIntent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_art:
                showDialog();
                break;
        }
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_artgroup);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText editText = (EditText) dialog.findViewById(R.id.edittext_add_art);

        Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                ArtObj artObj = new ArtObj();
                artObj.setNameArt(string);
                artObj.setDrawableArt(0);
                artGroupAdapter.addItem(artObj);
                arrayListData.add(new Pair<String, Integer>(string, 0));
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public void onArtClick(int position) {
        moveSubArtAct(arrayListData.get(position).first);
    }

    @Override
    public void onEditArt(int position) {
        AppUtils.showShortToast(this, "EDIT");
    }

    @Override
    public void onDeleteArt(int position) {
        AppUtils.showShortToast(this, "DELETE");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
