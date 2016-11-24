package com.namestore.alicenote.ui.firstsetup.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daimajia.swipe.SwipeLayout;
import com.namestore.alicenote.R;
import com.namestore.alicenote.models.ArtObj;
import com.namestore.alicenote.models.ServicesCategoryObj;

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ServicesCategoryAdapter extends RecyclerView.Adapter<ServicesCategoryAdapter.ViewHolder> {

    private ArrayList<ServicesCategoryObj> servicesCategoryObjArrayList;
    private Activity activity;

    public ServicesCategoryAdapter(Activity activity, ArrayList<ServicesCategoryObj> servicesCategoryObjArrayList) {
        this.activity = activity;
        this.servicesCategoryObjArrayList = servicesCategoryObjArrayList;
    }

    @Override
    public ServicesCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesCategoryAdapter.ViewHolder holder, int position) {
        holder.bindData(servicesCategoryObjArrayList.get(position));
        holder.mButtonServicesCategory.setBackgroundResource(servicesCategoryObjArrayList.get(position).getImgResId());
        holder.mButtonServicesCategory.setText(servicesCategoryObjArrayList.get(position).getNameService());
    }

    public void addItem(ServicesCategoryObj servicesCategoryObj) {
        servicesCategoryObjArrayList.add(servicesCategoryObj);
        notifyItemInserted(servicesCategoryObjArrayList.size());
    }

    public void removeItem(int position) {
        servicesCategoryObjArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, servicesCategoryObjArrayList.size());
    }

    @Override
    public int getItemCount() {
        return servicesCategoryObjArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button mButtonServicesCategory;
        private SwipeLayout swipeLayout;
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            mButtonServicesCategory = (Button) view.findViewById(R.id.button_services_category);
        }

        public void bindData(ServicesCategoryObj servicesCategoryObjArrayList) {

        }
    }

}
