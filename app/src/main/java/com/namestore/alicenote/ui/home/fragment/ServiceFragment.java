package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.models.ServiceViewObj;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ServiceResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.namestore.alicenote.ui.home.MainActivity;
import com.namestore.alicenote.ui.home.adapter.ServiceRecyclerViewAdapter;
import com.namestore.alicenote.ui.service.ServiceDetailActivity;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nhocnhinho on 23/11/2016.
 */

public class ServiceFragment  extends BaseFragment {

    private SearchBox mSearchBox;
    private List<ServiceViewObj> mServiceViewObjects = new ArrayList<>();
    private RecyclerView mRecyclerServiceSearchBox;

    private AliceApi mAliceApi;
    private Button mBtnAddServiceCLient;
    private MainActivity mMainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_service, container, false);


        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {
        mAliceApi = ServiceGenerator.creatService(AliceApi.class);

        searchService();
        mSearchBox = (SearchBox) view.findViewById(R.id.searchBox);
        mSearchBox.enableVoiceRecognition(this);
        mSearchBox.setOverflowMenu(R.menu.overflow_menu);

        mRecyclerServiceSearchBox = (RecyclerView) view.findViewById(R.id.recyclerServiceSearchBox);
        mRecyclerServiceSearchBox.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerServiceSearchBox.setHasFixedSize(true);
        mBtnAddServiceCLient = (Button) view.findViewById(R.id.btnAddNewService);

    }

    @Override
    protected void initModels() {
        mBtnAddServiceCLient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(new Intent(getContext(),ServiceDetailActivity.class));
                mIntent.putExtra(Constants.KEY_CHECK_SERVICE, Constants.ADD_SERVICE);
                startActivityForResult(mIntent, 1407);
            }
        });

        mRecyclerServiceSearchBox.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent mIntent = new Intent(new Intent(getContext(), ClientDetailActivity.class));
                        mIntent.putExtra(Constants.KEY_ID, mServiceViewObjects.get(position).getId());
                        mIntent.putExtra(Constants.KEY_CHECK_SERVICE, Constants.VIEW_SERVICE);
                        startActivity(mIntent);
                    }
                })
        );

        mSearchBox.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add_service:
                        Intent mIntent = new Intent(new Intent(getContext(),ServiceDetailActivity.class));
                        mIntent.putExtra(Constants.KEY_CHECK_SERVICE, Constants.ADD_SERVICE);
                        startActivityForResult(mIntent, 1407);
                        return true;
                }
                return false;
            }
        });
        mSearchBox.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {

                mMainActivity.drawer.openDrawer(GravityCompat.START);
            }
        });
        mSearchBox.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {


            }

            @Override
            public void onSearchClosed() {

            }

            @Override
            public void onSearchTermChanged(String term) {

            }

            @Override
            public void onSearch(String searchTerm) {

            }

            @Override
            public void onResultClick(SearchResult result) {
                Intent mIntent = new Intent(new Intent(getContext(),ServiceDetailActivity.class));
                mIntent.putExtra(Constants.KEY_ID, result.id);
                mIntent.putExtra(Constants.KEY_CHECK_SERVICE, Constants.ADD_SERVICE);
                startActivity(mIntent);
            }

            @Override
            public void onSearchCleared() {

            }

        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1407) {

            if (resultCode == Activity.RESULT_OK) {

                if (data.getIntExtra("result", 0) == 1) {

                 //   UpdateRecycleView();
                }

            }
        }

        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mSearchBox.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void searchService() {
        mAliceApi.searchService(189, 0,1,1, "toi").enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                mServiceViewObjects.clear();
                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        ServiceViewObj jsonArray = new ServiceViewObj(0, null, null, null);
                        jsonArray.setId(response.body().getData().get(i).getId());
                        jsonArray.setTvSvMoney(response.body().getData().get(i).getPrice());
                        jsonArray.setTvSvNameService(response.body().getData().get(i).getName());
                        jsonArray.setTvSvTime(response.body().getData().get(i).getDuration());

                        mServiceViewObjects.add(jsonArray);
                    }
                    for (ServiceViewObj cl : mServiceViewObjects) {
                        SearchResult option = new SearchResult(cl.getTvSvNameService(), cl.getId(), getResources().getDrawable(R.drawable.ic_history));
                        mSearchBox.addSearchable(option);
                    }
                    ServiceRecyclerViewAdapter adapter = new ServiceRecyclerViewAdapter(getContext(), mServiceViewObjects);
                    mRecyclerServiceSearchBox.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {

            }
        });

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
