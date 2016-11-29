package com.namestore.alicenote.ui.art;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.models.ArtObj;
import com.namestore.alicenote.ui.BaseActivity;
import com.namestore.alicenote.ui.art.adapter.ArtGroupAdapter;

import java.util.ArrayList;

/**
 * Created by kienht on 11/10/16.
 */

public class SubArtActivity extends BaseActivity implements ArtGroupAdapter.OnArtItemClickListener {


    TextView mTextViewTitle;
    Button mButtonAdd;
    Button mButtonBack;
    ArrayList<ArtObj> artArrayList;
    ArrayList<Pair<String, Integer>> arrayListData;
    RecyclerView recyclerViewArt;
    ArtGroupAdapter artGroupAdapter;
    String mArtName = "";
    Dialog dialogCreatArt;
    Dialog dialogEditArt;
    int itemId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subart);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mArtName = extras.getString(Constants.ART_SCREEN);
        }
        initViews();
        initModels();
    }

    protected void initViews() {
        mTextViewTitle = (TextView) findViewById(R.id.textview_title_subart);
        mButtonAdd = (Button) findViewById(R.id.button_add_art);
        mButtonBack = (Button) findViewById(R.id.button_back);

        recyclerViewArt = (RecyclerView) findViewById(R.id.list_art_group);
        recyclerViewArt.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArt.setHasFixedSize(true);
    }

    protected void initModels() {
        mTextViewTitle.setText(mArtName);
        mButtonAdd.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);

        artArrayList = new ArrayList<>();

        arrayListData = new ArrayList<>();
        arrayListData.add(new Pair<String, Integer>(mArtName + " 1", 0));
        arrayListData.add(new Pair<String, Integer>(mArtName + " 2", 0));
        arrayListData.add(new Pair<String, Integer>(mArtName + " 3", 0));
        arrayListData.add(new Pair<String, Integer>(mArtName + " 4", 0));

        for (int i = 0; i < arrayListData.size(); i++) {
            ArtObj artGroup = new ArtObj();
            artGroup.setNameArt(arrayListData.get(i).first);
            artGroup.setDrawableArt(arrayListData.get(i).second);
            this.artArrayList.add(artGroup);
        }
        artGroupAdapter = new ArtGroupAdapter(this, artArrayList, true);

        recyclerViewArt.setAdapter(artGroupAdapter);
        recyclerViewArt.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * CREAT SUB ART
     */
    public void creatSubArtDialog() {
        dialogCreatArt = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialogCreatArt.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCreatArt.setContentView(R.layout.dialog_creat_sub_art);
        dialogCreatArt.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogCreatArt.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogCreatArt.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogCreatArt.setCancelable(false);

        LinearLayout linearLayout = (LinearLayout) dialogCreatArt.findViewById(R.id.linear_layout);
        linearLayout.setFocusableInTouchMode(true);

        TextView mTitle = (TextView) dialogCreatArt.findViewById(R.id.action_bar).findViewById(R.id.textview_title);
        mTitle.setText("CREAT ART");

        TextInputLayout inputLayoutName = (TextInputLayout) dialogCreatArt.findViewById(R.id.input_name);
        TextInputLayout inputLayoutPrice = (TextInputLayout) dialogCreatArt.findViewById(R.id.input_price);
        TextInputLayout inputLayoutOldPrice = (TextInputLayout) dialogCreatArt.findViewById(R.id.input_old_price);
        TextInputLayout inputLayoutDescriptions = (TextInputLayout) dialogCreatArt.findViewById(R.id.input_descriptions);

        final EditText inputName = (EditText) dialogCreatArt.findViewById(R.id.edittext_name_art);
        EditText inputPrice = (EditText) dialogCreatArt.findViewById(R.id.edittext_price);
        EditText inputOldPrice = (EditText) dialogCreatArt.findViewById(R.id.edittext_old_price);
        EditText inputDescription = (EditText) dialogCreatArt.findViewById(R.id.edittext_descriptions);

        ViewUtils.configEditText(this, inputName, linearLayout, "", 0, null);
        ViewUtils.configEditText(this, inputPrice, linearLayout, "", 0, null);
        ViewUtils.configEditText(this, inputOldPrice, linearLayout, "", 0, null);
        ViewUtils.configEditText(this, inputDescription, linearLayout, "", 0, null);

        MaterialSpinner mSpinner = (MaterialSpinner) dialogCreatArt.findViewById(R.id.spinner_creat_art);
        String[] timeDuration = getResources().getStringArray(R.array.duration_time_service);
        mSpinner.setItems(timeDuration);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSpinner.setTextColor(this.getResources().getColor(android.R.color.black, this.getTheme()));
        } else {
            mSpinner.setTextColor(this.getResources().getColor(android.R.color.black));
        }
        mSpinner.setDropdownMaxHeight(AppUtils.getHeightScreen(this) / 3);

        Button buttonCancel = (Button) dialogCreatArt.findViewById(R.id.action_bar).findViewById(R.id.button_cancel);
        Button buttonOk = (Button) dialogCreatArt.findViewById(R.id.action_bar).findViewById(R.id.button_ok);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCreatArt.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = inputName.getText().toString();
                ArtObj artObj = new ArtObj();
                artObj.setNameArt(string);
                artObj.setDrawableArt(0);
                artGroupAdapter.addItem(artObj);
                arrayListData.add(new Pair<String, Integer>(string, 0));
                dialogCreatArt.dismiss();
            }
        });
        dialogCreatArt.show();
    }

    /**
     * EDIT SUB ART
     */
    public void editSubArtDialog() {
        dialogEditArt = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialogEditArt.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEditArt.setContentView(R.layout.dialog_edit_sub_art);
        dialogEditArt.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogEditArt.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEditArt.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogEditArt.setCancelable(false);

        LinearLayout linearLayout = (LinearLayout) dialogEditArt.findViewById(R.id.linear_layout);
        linearLayout.setFocusableInTouchMode(true);

        TextView mTitle = (TextView) dialogEditArt.findViewById(R.id.action_bar).findViewById(R.id.textview_title);
        mTitle.setText("EDIT ART");

        TextInputLayout inputLayoutName = (TextInputLayout) dialogEditArt.findViewById(R.id.input_name);
        TextInputLayout inputLayoutPrice = (TextInputLayout) dialogEditArt.findViewById(R.id.input_price);
        TextInputLayout inputLayoutOldPrice = (TextInputLayout) dialogEditArt.findViewById(R.id.input_old_price);
        TextInputLayout inputLayoutDescriptions = (TextInputLayout) dialogEditArt.findViewById(R.id.input_descriptions);

        final EditText inputName = (EditText) dialogEditArt.findViewById(R.id.edittext_name_art);
        EditText inputPrice = (EditText) dialogEditArt.findViewById(R.id.edittext_price);
        EditText inputOldPrice = (EditText) dialogEditArt.findViewById(R.id.edittext_old_price);
        EditText inputDescription = (EditText) dialogEditArt.findViewById(R.id.edittext_descriptions);

        ViewUtils.configEditText(this, inputName, linearLayout, "", 0, null);
        ViewUtils.configEditText(this, inputPrice, linearLayout, "", 0, null);
        ViewUtils.configEditText(this, inputOldPrice, linearLayout, "", 0, null);
        ViewUtils.configEditText(this, inputDescription, linearLayout, "", 0, null);

        MaterialSpinner mSpinner = (MaterialSpinner) dialogEditArt.findViewById(R.id.spinner_creat_art);
        String[] timeDuration = getResources().getStringArray(R.array.duration_time_service);
        mSpinner.setItems(timeDuration);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSpinner.setTextColor(this.getResources().getColor(android.R.color.black, this.getTheme()));
        } else {
            mSpinner.setTextColor(this.getResources().getColor(android.R.color.black));
        }

        mSpinner.setDropdownMaxHeight(AppUtils.getHeightScreen(this) / 3);

        Button buttonCancel = (Button) dialogEditArt.findViewById(R.id.action_bar).findViewById(R.id.button_cancel);
        Button buttonOk = (Button) dialogEditArt.findViewById(R.id.action_bar).findViewById(R.id.button_ok);
        Button buttonDelete = (Button) dialogEditArt.findViewById(R.id.button_delete);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEditArt.dismiss();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEditArt.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEditArt.dismiss();
                artArrayList.remove(itemId);
                artGroupAdapter.notifyItemRemoved(itemId);
                artGroupAdapter.notifyItemRangeChanged(itemId, artArrayList.size());
            }
        });

        dialogEditArt.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_art:
                creatSubArtDialog();
                break;

            case R.id.button_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onArtClick(int position) {
        editSubArtDialog();
        itemId = position;
    }

    @Override
    public void onEditArt(int position) {
        editSubArtDialog();
        itemId = position;
    }

    @Override
    public void onDeleteArt(int position) {
        itemId = position;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialogCreatArt != null) {
            dialogCreatArt.dismiss();
        }
        if (dialogEditArt != null) {
            dialogEditArt.dismiss();
        }
    }
}
