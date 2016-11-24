package com.namestore.alicenote.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.R;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ClientResponse;
import com.namestore.alicenote.ui.client.adapter.ClientCustomRecyclerViewAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.models.ClientObj;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.namestore.alicenote.ui.home.MainActivity;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class ClientFragment extends BaseFragment {

    private SearchBox mSearchBox;
    private List<ClientObj> mClientObjects = new ArrayList<>();
    private RecyclerView mRecyclerViewSearchBox;

    private AliceApi mAliceApi;
    private Button mBtnAddNewCLient;

   private MainActivity mMainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_client, container, false);


        initViews(view);
        initModels();
        return view;
    }


    @Override
    protected void initViews(View view) {

        mAliceApi = ServiceGenerator.creatService(AliceApi.class);
        searchClient();
        mSearchBox = (SearchBox) view.findViewById(R.id.searchbox);
        mSearchBox.enableVoiceRecognition(this);
        mSearchBox.setOverflowMenu(R.menu.overflow_menu);


        mRecyclerViewSearchBox = (RecyclerView) view.findViewById(R.id.recyclerViewSearchBox);
        mRecyclerViewSearchBox.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewSearchBox.setHasFixedSize(true);
        mBtnAddNewCLient = (Button) view.findViewById(R.id.btnAddNewClient);

    }

    @Override
    protected void initModels() {

        mBtnAddNewCLient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(new Intent(getContext(), ClientDetailActivity.class));
                mIntent.putExtra(Constants.KEY_CHECK_CLIENT, Constants.ADD_CLIENT);
                startActivityForResult(mIntent, 1406);
            }
        });

        mRecyclerViewSearchBox.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent mIntent = new Intent(new Intent(getContext(), ClientDetailActivity.class));
                        mIntent.putExtra(Constants.KEY_ID, mClientObjects.get(position).getId());
                        mIntent.putExtra(Constants.KEY_CHECK_CLIENT, Constants.VIEW_CLIENT);
                        startActivity(mIntent);
                    }
                })
        );

        mSearchBox.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        //  Intent mIntent = new Intent(new Intent(getContext(), ClientDetailActivity.class));
                        Intent mIntent = new Intent(getActivity(), ClientDetailActivity.class);
                        mIntent.putExtra(Constants.KEY_CHECK_CLIENT, Constants.ADD_CLIENT);
                        startActivityForResult(mIntent, 1406);
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
                Intent mIntent = new Intent(new Intent(getContext(), ClientDetailActivity.class));
                mIntent.putExtra(Constants.KEY_ID, result.id);
                mIntent.putExtra(Constants.KEY_CHECK_CLIENT, Constants.VIEW_CLIENT);

                startActivity(mIntent);
            }

            @Override
            public void onSearchCleared() {

            }

        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1406) {

            if (resultCode == Activity.RESULT_OK) {

                if (data.getIntExtra("result", 0) == 1) {

                    UpdateRecycleView();
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


    public void searchClient() {
        mAliceApi.searchClient(130, 3, 20, "desc").enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                mClientObjects.clear();
                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        ClientObj jsonArray = new ClientObj(0, null, null, null);
                        jsonArray.setId(response.body().getData().get(i).getId());
                        jsonArray.setTvFirstName(response.body().getData().get(i).getFirstName());
                        jsonArray.setTvLastName(response.body().getData().get(i).getLastName());
                        jsonArray.setImgAvatar(response.body().getData().get(i).getImage());

                        mClientObjects.add(jsonArray);
                    }
                    for (ClientObj cl : mClientObjects) {

                        SearchResult option = new SearchResult(cl.getTvFirstName() + " " +
                                cl.getTvLastName(), cl.getId(), getResources().getDrawable(R.drawable.ic_history));
                        mSearchBox.addSearchable(option);
                    }
                    ClientCustomRecyclerViewAdapter adapter = new ClientCustomRecyclerViewAdapter(getContext(), mClientObjects);

                    mRecyclerViewSearchBox.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {

            }
        });

    }

    public void UpdateRecycleView() {
        mClientObjects.clear();
        searchClient();

        ClientCustomRecyclerViewAdapter adapter = new ClientCustomRecyclerViewAdapter(getContext(), mClientObjects);
        adapter.notifyDataSetChanged();
        mRecyclerViewSearchBox.setAdapter(adapter);

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
