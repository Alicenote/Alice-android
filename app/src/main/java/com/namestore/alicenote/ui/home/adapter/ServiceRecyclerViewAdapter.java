package com.namestore.alicenote.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.ServiceViewObj;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nhocnhinho on 11/18/2015.
 */
public class ServiceRecyclerViewAdapter extends
        RecyclerView.Adapter<ServiceRecyclerViewAdapter.RecyclerViewHolder> {
    Context context;
    private List<ServiceViewObj> listView_Object = new ArrayList<ServiceViewObj>();

    public ServiceRecyclerViewAdapter(Context context, List<ServiceViewObj> listData) {
        this.listView_Object = listData;
        this.context =context;
    }

    public void updateList(List<ServiceViewObj> data) {
        listView_Object = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listView_Object.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_service_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.tvSvNameService.setText(listView_Object.get(position).getTvSvNameService());
        viewHolder.tvSvMoney.setText(listView_Object.get(position).getTvSvMoney());
        viewHolder.tvSvTime.setText(listView_Object.get(position).getTvSvTime());




    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            OnClickListener {

        private TextView tvSvTime;
        private TextView tvSvMoney;
        private TextView tvSvNameService;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvSvNameService = (TextView) itemView.findViewById(R.id.tvSvNameService);
            tvSvMoney = (TextView) itemView.findViewById(R.id.tvSvMoney);
            tvSvTime = (TextView) itemView.findViewById(R.id.tvSvTime);

        }

        // remove main_layout_recyclerview_item when click button delete
        @Override
        public void onClick(View v) {

        }

    }

}
