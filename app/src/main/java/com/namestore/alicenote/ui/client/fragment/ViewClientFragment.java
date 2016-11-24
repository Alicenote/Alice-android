package com.namestore.alicenote.ui.client.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.network.AliceApi;
import com.namestore.alicenote.network.ServiceGenerator;
import com.namestore.alicenote.network.reponse.ViewClientResponse;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.client.ClientDetailActivity;

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
    private Toolbar mToolBar;
    private ViewClientFragment mViewFragment = new ViewClientFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_view_client, container, false);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);
        setHasOptionsMenu(true);
        mId = mClientDetailActivity.mId;
        mId = 44;
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

    }

    @Override
    protected void initModels() {

        searchViewClient();
    }


    public void searchViewClient() {

        mAliceApi.searchViewClient(130, mId).enqueue(new Callback<ViewClientResponse>() {
            @Override
            public void onResponse(Call<ViewClientResponse> call, Response<ViewClientResponse> response) {
                if (response.isSuccessful()) {
                    tvClName.setText(response.body().getData().getFirstName() + " " +
                            response.body().data.getLastName());
                    tvClBirthday.setText(response.body().data.getBirthday());
                    tvClPhone.setText(response.body().data.getPhone());
                    tvClEmail.setText(response.body().data.getEmail());
                    tvClAddreess.setText(response.body().data.getAddress());

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
        inflater.inflate(R.menu.menu_client_detail, menu);
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
            Bundle _bundle = new Bundle();
            _bundle.putInt(Constants.KEY_CHECK_EDIT_CLIENT, mId);
            _bundle.putString(Constants.KEY_CHECK_CLIENT, Constants.EDIT_CLIENT);
            mViewFragment.setArguments(_bundle);
            getActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, mClientDetailActivity.mAddEditClientFragment).commit();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
