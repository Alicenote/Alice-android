package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.models.DashboardObj;
import com.namestore.alicenote.models.ViewClientObj;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ViewClientResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.namestore.alicenote.ui.client.adapter.ViewClientCustomRecycleAdapter;
import com.namestore.alicenote.ui.home.adapter.DashboardCustomRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhocnhinho on 09/11/2016.
 */

public class ViewClientFragment extends BaseFragment {

    private ImageView imgCl;
    private TextView tvClName;
    private TextView tvClTextMessage;
    private TextView tvClBirthday;
    private TextView tvClPhone;
    private TextView tvClEmail;
    private TextView tvClAddreess;
    private AliceApi mAliceApi;
    private ClientDetailActivity mClientDetailActivity;
    private int mId;
    private List<ViewClientObj> mListViewClient = new ArrayList<>();
    private RecyclerView mRecyclerViewClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fm_view_client, container, false);


        mId = mClientDetailActivity.mId;
        initViews(view);
        initModels();
        return view;
    }

    @Override
    protected void initViews(View view) {
        mAliceApi = ServiceGenerator.creatService(AliceApi.class);
        imgCl = (ImageView) view.findViewById(R.id.imageView);
        tvClName = (TextView) view.findViewById(R.id.tvClName);
        tvClTextMessage = (TextView) view.findViewById(R.id.tvClTextMessage);
        tvClBirthday = (TextView) view.findViewById(R.id.tvClBirthday);
        tvClPhone = (TextView) view.findViewById(R.id.tvClPhone);
        tvClEmail = (TextView) view.findViewById(R.id.tvClEmail);
        tvClAddreess = (TextView) view.findViewById(R.id.tvClAddreess);
        mRecyclerViewClient = (RecyclerView) view.findViewById(R.id.recyclerViewClient);
        mRecyclerViewClient.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        mRecyclerViewClient.setHasFixedSize(true);

    }

    @Override
    protected void initModels() {

        searchViewClient();
        ViewClientCustomRecycleAdapter adapterThisWeek = new ViewClientCustomRecycleAdapter(getContext(), mListViewClient);
        mRecyclerViewClient.setAdapter(adapterThisWeek);

    }


    public void searchViewClient() {

        mAliceApi.searchViewClient(130, mId).enqueue(new Callback<ViewClientResponse>() {
            @Override
            public void onResponse(Call<ViewClientResponse> call, Response<ViewClientResponse> response) {
                if (response.isSuccessful()) {
                    tvClName.setText(response.body().getData().getClient().getFirstName() + " " +
                            response.body().getData().getClient().getLastName());
                    tvClBirthday.setText(response.body().getData().getClient().getBirthday());
                    tvClPhone.setText(response.body().getData().getClient().getPhone());
                    tvClEmail.setText(response.body().getData().getClient().getEmail());
                    tvClAddreess.setText(response.body().getData().getClient().getAddress());

                }
                for (int i = 0; i < response.body().getData().getAppointments().getAppointment().size(); i++) {

                    ViewClientObj jsonArray = new ViewClientObj(0, null, null, null, null, null);
                    jsonArray.setTvClNameService(response.body().getData().getAppointments().getAppointment().get(i).getService());
                    jsonArray.setTvClDate(response.body().getData().getAppointments().getAppointment().get(i).getDate());
                    jsonArray.setTvClNameStaff(response.body().getData().getAppointments().getAppointment().get(i).getStaff());
                    jsonArray.setTvClTimeStart(response.body().getData().getAppointments().getAppointment().get(i).getStartTime());
                    jsonArray.setTvClMoney(response.body().getData().getAppointments().getAppointment().get(i).getTotalPrice());
                    mListViewClient.add(jsonArray);
                }
            }

            @Override
            public void onFailure(Call<ViewClientResponse> call, Throwable t) {

                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ClientDetailActivity) {
            this.mClientDetailActivity = (ClientDetailActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ClientDetailActivity) {
            this.mClientDetailActivity = (ClientDetailActivity) activity;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_view_client, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {

            getActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, mClientDetailActivity.mAddEditDelClientFragment).commit();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
