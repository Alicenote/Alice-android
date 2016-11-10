package com.namestore.alicenote.ui.art.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.ArtObj;

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtGroupAdapter extends RecyclerView.Adapter<ArtGroupAdapter.ViewHolder> {

    private ArrayList<ArtObj> artArrayList;

    public ArtGroupAdapter(ArrayList<ArtObj> artArrayList) {
        this.artArrayList = artArrayList;
    }

    @Override
    public ArtGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_art_group, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtGroupAdapter.ViewHolder holder, int position) {
        holder.bindData(artArrayList.get(position));
        holder.mButtonArt.setBackgroundResource(artArrayList.get(position).getDrawableArt());
        holder.mButtonArt.setText(artArrayList.get(position).getNameArt());
    }

    @Override
    public int getItemCount() {
        return artArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button mButtonArt;

        public ViewHolder(View view) {
            super(view);
            mButtonArt = (Button) view.findViewById(R.id.imageview_item_art_group);
        }

        public void bindData(ArtObj art) {

        }
    }

}
