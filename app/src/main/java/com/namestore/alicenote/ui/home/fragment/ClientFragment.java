package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.common.recycler.SimpleDividerItemDecoration;
import com.namestore.alicenote.models.ClientObj;
import com.namestore.alicenote.ui.BaseFragment;

import com.namestore.alicenote.ui.client.ClientDetailsActivity;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.home.adapter.ClientAdapterRecycler;

import java.util.ArrayList;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class ClientFragment extends BaseFragment implements ClientAdapterRecycler.OnClickItemClient {

    private View mToolBar;
    private LinearLayout linearLayoutMain;
    private SearchView mSearchView;
    private TextView mTextViewTitleBar;
    private Button mButtonSearch;
    private Button mButtonLeftBar;
    private Button mButtonRightBar;
    private RecyclerView mRecyclerView;
    private ClientAdapterRecycler mClientAdapterRecycler;
    private ArrayList<ClientObj> mClientObjArrayList = new ArrayList<>();
    boolean isKeyBoardShow = false;
    boolean isResizeRecycler = false; //cờ kiểm tra khi thay đổi height của recyclerview
    float densityScreen;
    private MainActivity mMainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_client, container, false);
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
        densityScreen = mMainActivity.getResources().getDisplayMetrics().density;
        linearLayoutMain = (LinearLayout) view.findViewById(R.id.activity_main);
        mToolBar = view.findViewById(R.id.include);
        mTextViewTitleBar = (TextView) view.findViewById(R.id.include).findViewById(R.id.toolbar_title);
        mButtonLeftBar = (Button) view.findViewById(R.id.include).findViewById(R.id.toolbar_backpress);
        mButtonRightBar = (Button) view.findViewById(R.id.include).findViewById(R.id.toolbar_edit);
        mSearchView = (SearchView) view.findViewById(R.id.search_view);
        mButtonSearch = (Button) view.findViewById(R.id.button_search);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
    }

    @Override
    protected void initModels() {
        ViewUtils.setAndScaleDrawableButton(mButtonRightBar, 0, 0, R.drawable.icon_add, 0, 0.7);

        AppUtils.setTypeFontForTextView(mMainActivity, AppUtils.BOLD, mTextViewTitleBar);
        AppUtils.setTypeFontForButton(mMainActivity, AppUtils.MEDIUM, mButtonLeftBar, mButtonRightBar, mButtonSearch);

        mTextViewTitleBar.setText("Your Clients");
        mButtonLeftBar.setVisibility(View.INVISIBLE);

        mButtonRightBar.setOnClickListener(view -> {
            startActivity(ClientDetailsActivity.createIntent(mMainActivity, null, true));
        });

        mButtonSearch.setOnClickListener(view -> {
            mButtonSearch.setVisibility(View.INVISIBLE);
            mSearchView.setIconified(false);
        });

        mSearchView.setQueryHint("Search");
        mSearchView.setOnCloseListener(() -> {
            mButtonSearch.setVisibility(View.VISIBLE);
            return false;
        });

        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mMainActivity, DividerItemDecoration.VERTICAL,
                AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._20dp)) / (int) densityScreen));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity));
        mRecyclerView.setHasFixedSize(true);

        mClientObjArrayList.add(new ClientObj("Ketty Perry", "ketty.perry@gmail.com", "3 days", "0911667991", 1, "12/12/1993", true));
        mClientObjArrayList.add(new ClientObj("Jenny Ha", "ha.jenny@gmail.com", "3 days", "0912667992", 1, "13/9/1994", false));
        mClientObjArrayList.add(new ClientObj("Hanah Pham", "hanah.pham@gmail.com", "5 days", "0913667994", 1, "14/6/1992", true));
        mClientObjArrayList.add(new ClientObj("Tuan Vu Quang", "tuanvq1488@gmail.com", "7 days", "0914667995", 0, "15/4/1991", false));
        mClientObjArrayList.add(new ClientObj("Han Trung Kien", "kienhantrung@gmail.com", "3 hours", "0915667996", 0, "16/1/1989", true));
        mClientObjArrayList.add(new ClientObj("Ketty Perry", "ketty.perry@gmail.com", "3 days", "0911667991", 1, "12/12/1993", true));
        mClientObjArrayList.add(new ClientObj("Jenny Ha", "ha.jenny@gmail.com", "3 days", "0912667992", 1, "13/9/1994", false));
        mClientObjArrayList.add(new ClientObj("Hanah Pham", "hanah.pham@gmail.com", "5 days", "0913667994", 1, "14/6/1992", true));
        mClientObjArrayList.add(new ClientObj("Tuan Vu Quang", "tuanvq1488@gmail.com", "7 days", "0914667995", 0, "15/4/1991", false));
        mClientObjArrayList.add(new ClientObj("Han Trung Kien", "kienhantrung@gmail.com", "3 hours", "0915667996", 0, "16/1/1989", true));

        mClientAdapterRecycler = new ClientAdapterRecycler(mMainActivity, mClientObjArrayList, this);
        mRecyclerView.setAdapter(mClientAdapterRecycler);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                mClientAdapterRecycler.getFilter().filter(text);
                mRecyclerView.scrollToPosition(0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                mClientAdapterRecycler.getFilter().filter(text);
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });

        mSearchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                mSearchView.setIconified(true);
            }
        });
        resizeHeightWhenKeyBoardShow();
    }

    private void resizeHeightWhenKeyBoardShow() {
        linearLayoutMain.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            linearLayoutMain.getWindowVisibleDisplayFrame(r);
            int screenHeight = linearLayoutMain.getRootView().getHeight();
            int keypadHeight = screenHeight - r.bottom;
            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                isKeyBoardShow = true;
                if (!isResizeRecycler) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mRecyclerView.getWidth(), screenHeight -
                            mToolBar.getHeight() - //get Height mToolBar
                            mButtonSearch.getHeight() - //get Height mButtonSearch
                            AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._35dp) / densityScreen) - //total margin top + bottom of allview in layout
                            AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._20dp) / densityScreen) - //margin top + bottom of divider recycler
                            keypadHeight);

                    //set lại margin như xml sau khi thay đổi chiều cao
                    layoutParams.setMargins(AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._20dp) / densityScreen),
                            AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._10dp) / densityScreen), 0,
                            AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._20dp)) / (int) densityScreen);
                    mRecyclerView.setLayoutParams(layoutParams);
                    isResizeRecycler = true;
                }
            } else {
                isKeyBoardShow = false;
                if (isResizeRecycler) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mRecyclerView.getWidth(),
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._20dp) / densityScreen),
                            AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._10dp) / densityScreen), 0,
                            AppUtils.convertDpToPx(mMainActivity, getResources().getDimension(R.dimen._20dp)) / (int) densityScreen);
                    mRecyclerView.setLayoutParams(layoutParams);
                    isResizeRecycler = false;
                }
            }
        });
    }


    @Override
    public void onDelete(int position) {

    }

    @Override
    public void onEdit(int position, ClientObj obj) {
        startActivity(ClientDetailsActivity.createIntent(mMainActivity, obj, false));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mMainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            this.mMainActivity = (MainActivity) activity;
        }
    }
}
