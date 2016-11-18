package com.namestore.alicenote.ui.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.namestore.alicenote.R;
import com.namestore.alicenote.models.ClientObj;
import java.util.ArrayList;
import java.util.List;




/**
 * Created by Nhocnhinho on 11/18/2015.
 */
public class ClientCustomRecyclerViewAdapter extends
        RecyclerView.Adapter<ClientCustomRecyclerViewAdapter.RecyclerViewHolder> {
    Context context;
    private List<ClientObj> listView_Object = new ArrayList<ClientObj>();

    public ClientCustomRecyclerViewAdapter(Context context, List<ClientObj> listData) {
        this.listView_Object = listData;
        this.context =context;
    }

    public void updateList(List<ClientObj> data) {
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
        View itemView = inflater.inflate(R.layout.layout_client_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.tvName.setText(listView_Object.get(position).getTvFirstName()+"  "+
                listView_Object.get(position).getTvLastName());


    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            OnClickListener {

        private TextView tvName;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvNameofClient);

        }

        // remove main_layout_recyclerview_item when click button delete
        @Override
        public void onClick(View v) {

        }

    }

}
