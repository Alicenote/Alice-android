package com.namestore.alicenote.ui.client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.namestore.alicenote.common.recycler.RecyclerItemClickListener;
import com.namestore.alicenote.database.Contact;
import com.namestore.alicenote.database.DatabaseHandler;
import com.namestore.alicenote.R;
import com.namestore.alicenote.ui.client.adapter.ClientCustomRecyclerViewAdapter;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.Constants;
import com.namestore.alicenote.common.recycler.OnFragmentInteractionListener;
import com.namestore.alicenote.models.ClientObj;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class ClientFragment extends BaseFragment {

    private SearchBox mSearchBox;
    private DatabaseHandler db;
    private List<ClientObj> listViewObjects = new ArrayList<>();
    private RecyclerView mRecyclerViewSearchBox;
    private String KEY="client.add";


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

        db = new DatabaseHandler(getContext());
        List<Contact> contactsList = db.getAllContacts();
        for (Contact cn : contactsList) {
            ClientObj apk = new ClientObj(null);
            apk.setTvName(cn.getName());
            listViewObjects.add(apk);
        }
        for (Contact cn : contactsList) {
            SearchResult option = new SearchResult(cn.getName(), getResources().getDrawable(R.drawable.ic_history));
            mSearchBox.addSearchable(option);
        }

        mRecyclerViewSearchBox = (RecyclerView) view.findViewById(R.id.recyclerViewSearchBox);//listview cua upcoming
        mRecyclerViewSearchBox.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        mRecyclerViewSearchBox.setHasFixedSize(true);
        ClientCustomRecyclerViewAdapter adapter = new ClientCustomRecyclerViewAdapter(getContext(), listViewObjects);
        mRecyclerViewSearchBox.setAdapter(adapter);

        mSearchBox = (SearchBox) view.findViewById(R.id.searchbox);
        mSearchBox.enableVoiceRecognition(this);
        mSearchBox.setOverflowMenu(R.menu.overflow_menu);

    }

    @Override
    protected void initModels() {
        mRecyclerViewSearchBox.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
        );

        mSearchBox.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Intent mIntent = new Intent(new Intent(getContext(), ClientDetailActivity.class));
                        mIntent.putExtra(Constants.KEY_CHECK_CLIENT, KEY);
                        startActivity(mIntent);
                        return true;
                }
                return false;
            }
        });
        mSearchBox.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen

            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen

            }

            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
                Log.w("heloo3", "asdsfsd");
            }

            @Override
            public void onSearch(String searchTerm) {
                Log.w("heloo4", "asdsfsd");
                Toast.makeText(getContext(), searchTerm + " Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
                Log.w("heloo5", "asdsfsd");
            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
                Log.w("heloo6", "asdsfsd");
            }

        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mSearchBox.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
