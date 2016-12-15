package com.namestore.alicenote.ui.home.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.models.ClientObj;

import java.util.ArrayList;


/**
 * Created by kienht on 12/10/16.
 */

public class ClientAdapterRecycler extends RecyclerView.Adapter<ClientAdapterRecycler.ViewHolder> {
    public static final String TAG = "TAG";

    private Activity activity;
    private ArrayList<ClientObj> clientObjArrayList;
    private ArrayList<ClientObj> tempArrayToFilter;
    private ArrayList<ClientObj> tempOriginalArray;
    private OnClickItemClient listener;

    public ClientAdapterRecycler(Activity activity, ArrayList<ClientObj> clientObjArrayList, OnClickItemClient listener) {
        this.activity = activity;
        this.clientObjArrayList = clientObjArrayList;
        this.tempOriginalArray = clientObjArrayList;
        this.tempArrayToFilter = clientObjArrayList;
        this.listener = listener;
    }

    public interface OnClickItemClient {
        void onDelete(int position);

        void onEdit(int position, ClientObj obj);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(clientObjArrayList.get(position));
        AppUtils.setTypeFontForTextView(activity, AppUtils.BOLD, holder.name);
        AppUtils.setTypeFontForTextView(activity, AppUtils.MEDIUM, holder.timeOnline, holder.email);
        ViewUtils.setAndScaleDrawableButton(holder.delete,R.drawable.icon_delete,0,0,0, 0.7);
        holder.delete.setOnClickListener(view -> {
            if (position >= clientObjArrayList.size()) {
                return;
            }
            listener.onDelete(position);
            //remove obj from array when filtering
            if (clientObjArrayList.size() < tempOriginalArray.size()) {
                for (int i = 0; i < tempOriginalArray.size(); i++) {
                    if (clientObjArrayList.get(position).getmName().equals(tempOriginalArray.get(i).getmName())) {
                        tempOriginalArray.remove(i);
                    }
                }
            }
            removeItem(position);
        });
        holder.relativeLayout.setOnClickListener(view -> listener.onEdit(position, clientObjArrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return clientObjArrayList.size();
    }

    public void addItem(ClientObj artObj) {
        clientObjArrayList.add(artObj);
        notifyItemInserted(getItemCount());
    }

    public void removeItem(int position) {
        clientObjArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public Filter getFilter() {
        return new ItemFilter();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView timeOnline;
        TextView email;
        Button delete;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textview_name);
            email = (TextView) itemView.findViewById(R.id.textview_email);
            timeOnline = (TextView) itemView.findViewById(R.id.textview_time_online);
            delete = (Button) itemView.findViewById(R.id.button_delete);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout_name);
        }

        public void bindData(ClientObj obj) {
            name.setText(obj.getmName());
            email.setText(obj.getmEmail());
            timeOnline.setText(activity.getResources().getString(R.string.time_online, obj.getmTimeOnline()));
        }
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            int count = tempArrayToFilter.size();
            final ArrayList<ClientObj> tempFilterList = new ArrayList<>(count);
            String filterableString;
            for (int i = 0; i < count; i++) {
                filterableString = tempArrayToFilter.get(i).getmName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    tempFilterList.add(tempArrayToFilter.get(i));
                }
            }
            results.values = tempFilterList;
            results.count = tempFilterList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            String filterString = constraint.toString().toLowerCase();
            if (!TextUtils.isEmpty(filterString)) {
                clientObjArrayList = (ArrayList<ClientObj>) results.values;
            } else {
                clientObjArrayList = tempOriginalArray;
            }
            notifyDataSetChanged();
        }
    }
}