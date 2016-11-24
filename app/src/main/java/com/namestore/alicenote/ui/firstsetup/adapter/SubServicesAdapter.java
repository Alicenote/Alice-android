package com.namestore.alicenote.ui.firstsetup.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.namestore.alicenote.Constants;
import com.namestore.alicenote.R;
import com.namestore.alicenote.models.SubServicesObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kienht on 11/2/16.
 */

public class SubServicesAdapter extends RecyclerView.Adapter<SubServicesAdapter.ViewHolder> {

    private ArrayList<SubServicesObj> subServicesObjArrayList;
    private Activity activity;
    private int tag;
    private OnSubServicesClickListener listener;

    public interface OnSubServicesClickListener {
        void onDeleteSubServiceItem(int position, int tag);
    }

    public SubServicesAdapter(Activity activity, List<SubServicesObj> subServicesObjArrayList, OnSubServicesClickListener listener, int tag) {
        this.activity = activity;
        this.subServicesObjArrayList = new ArrayList<>(subServicesObjArrayList);
        this.tag = tag;
        this.listener = listener;
    }

    @Override
    public SubServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subservices, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SubServicesAdapter.ViewHolder holder, int position) {
        holder.bindData(subServicesObjArrayList.get(position));

        //in some cases, it will prevent unwanted situations
        holder.checkboxService.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.checkboxService.setChecked(subServicesObjArrayList.get(position).isCheck());

        holder.checkboxService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subServicesObjArrayList.get(holder.getAdapterPosition()).setCheck(isChecked);
            }
        });
    }

    public void addItem(SubServicesObj item) {
        subServicesObjArrayList.add(item);
        notifyItemInserted(subServicesObjArrayList.size() - 1);
    }

    public void addItem(int position, SubServicesObj item) {
        subServicesObjArrayList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        subServicesObjArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(SubServicesObj item) {
        int index = subServicesObjArrayList.indexOf(item);
        if (index < 0)
            return;
        subServicesObjArrayList.remove(index);
        notifyItemRemoved(index);
    }

    public void replaceItem(int postion, SubServicesObj item) {
        subServicesObjArrayList.remove(postion);
        subServicesObjArrayList.add(postion, item);
        notifyItemChanged(postion);
    }

    public void clearData() {
        subServicesObjArrayList.clear(); //clear list
        notifyDataSetChanged(); //let your adapter know about the changes and reload view.
    }

    @Override
    public int getItemCount() {
        return subServicesObjArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textNameServices;
        private CheckBox checkboxService;
        private Button buttonDelete;

        public ViewHolder(final View view) {
            super(view);
            textNameServices = (TextView) view.findViewById(R.id.sub_service_name);
            checkboxService = (CheckBox) view.findViewById(R.id.sub_service_checkbox);
            buttonDelete = (Button) view.findViewById(R.id.sub_service_delete);
        }

        public void bindData(final SubServicesObj subServices) {

            textNameServices.setText(subServices.getNameSubServices());
            textNameServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkboxService.isChecked()) {
                        checkboxService.setChecked(false);
                    } else {
                        checkboxService.setChecked(true);
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(getAdapterPosition());
                    buttonDelete.setClickable(false);
                    switch (tag) {
                        case Constants.NAIL:
                            listener.onDeleteSubServiceItem(getPosition(), Constants.NAIL);
                            break;
                        case Constants.HAIR:
                            listener.onDeleteSubServiceItem(getPosition(), Constants.HAIR);
                            break;
                    }
                }
            });
        }
    }

}
