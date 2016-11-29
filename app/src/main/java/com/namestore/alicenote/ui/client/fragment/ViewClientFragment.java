package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.models.ViewClientObj;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ClientAddEditDelResponse;
import com.namestore.alicenote.network.reponse.ClientViewResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;
import com.namestore.alicenote.ui.client.adapter.ViewClientCustomRecycleAdapter;

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
    private ProgressBar mProgressBar;
    private Button mBtnViewClientDel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fm_view_client, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mClientDetailActivity.mToolBar);
        mClientDetailActivity.mToolBar.setTitle("View Client");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

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
        mProgressBar = (ProgressBar) view.findViewById(R.id.prgBarViewClient);
        mBtnViewClientDel = (Button) view.findViewById(R.id.btnViewClientDel);

    }

    @Override
    protected void initModels() {

        searchViewClient();
        mBtnViewClientDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDelDialog();
            }
        });

    }


    public void searchViewClient() {

        mAliceApi.searchViewClient(130, mId).enqueue(new Callback<ClientViewResponse>() {

            @Override
            public void onResponse(Call<ClientViewResponse> call, Response<ClientViewResponse> response) {
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
                ViewClientCustomRecycleAdapter adapterThisWeek = new ViewClientCustomRecycleAdapter(getContext(), mListViewClient);
                mRecyclerViewClient.setAdapter(adapterThisWeek);
                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ClientViewResponse> call, Throwable t) {

                if (call.isCanceled()) {
                    AppUtils.logE("request was cancelled");
                } else {
                    AppUtils.logE("FAILED " + t.getLocalizedMessage());
                }
            }
        });


    }

    public void showDelDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Warning !!!");
        builder.setMessage("Are you sure to delete this client");
        builder.setPositiveButton("OK!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                delInfoClient();


            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.create().show();
    }

    public void delInfoClient() {
        mAliceApi.delClient(130, mClientDetailActivity.mId).enqueue(new Callback<ClientAddEditDelResponse>() {
            @Override
            public void onResponse(Call<ClientAddEditDelResponse> call, Response<ClientAddEditDelResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), "Delete Client Completed", Toast.LENGTH_SHORT).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        getActivity().setResult(Activity.RESULT_OK, returnIntent);
                        getActivity().finish();

                    } else
                        Toast.makeText(getActivity(), "Delete Client Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClientAddEditDelResponse> call, Throwable t) {

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
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        super.onDestroy();
    }
}
