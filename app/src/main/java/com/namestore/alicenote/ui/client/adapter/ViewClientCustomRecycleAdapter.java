package com.namestore.alicenote.ui.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.ViewClientObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhocnhinho on 14/11/2016.
 */

public class ViewClientCustomRecycleAdapter extends
        RecyclerView.Adapter<ViewClientCustomRecycleAdapter.RecyclerViewHolder> {
    Context context;
    private List<ViewClientObj> ViewClient_Obj = new ArrayList<ViewClientObj>();

    public ViewClientCustomRecycleAdapter(Context context, List<ViewClientObj> listData) {
        this.ViewClient_Obj = listData;
        this.context = context;
    }

    public void updateList(List<ViewClientObj> data) {
        ViewClient_Obj = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ViewClient_Obj.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_view_client_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.tvClNameService.setText(ViewClient_Obj.get(position).getTvClNameService());
        viewHolder.tvClDate.setText(ViewClient_Obj.get(position).getTvClDate());
        viewHolder.tvClTimeStart.setText(ViewClient_Obj.get(position).getTvClTimeStart());
        viewHolder.tvClNameStaff.setText(ViewClient_Obj.get(position).getTvClNameStaff());
        viewHolder.tvClMoney.setText(ViewClient_Obj.get(position).getTvClMoney());

    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private TextView tvClNameService;
        private TextView tvClDate;
        private TextView tvClTimeStart;
        private TextView tvClNameStaff;
        private TextView tvClMoney;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvClNameService = (TextView) itemView.findViewById(R.id.tvClNameService);
            tvClDate = (TextView) itemView.findViewById(R.id.tvClDate);
            tvClTimeStart = (TextView) itemView.findViewById(R.id.tvClTimeStart);
            tvClNameStaff = (TextView) itemView.findViewById(R.id.tvClNameStaff);
            tvClMoney = (TextView) itemView.findViewById(R.id.tvClMoney);


        }

        // remove main_layout_recyclerview_item when click button delete
        @Override
        public void onClick(View v) {

        }

    }

}
