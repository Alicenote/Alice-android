package com.namestore.alicenote.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.Art;
import com.namestore.alicenote.models.SubServices;

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtGroupAdapter extends RecyclerView.Adapter<ArtGroupAdapter.ViewHolder> {

    private ArrayList<Art> artArrayList;

    public ArtGroupAdapter( ArrayList<Art> artArrayList) {
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
        holder.mImageViewArt.setBackgroundResource(artArrayList.get(position).getDrawableArt());
    }

    @Override
    public int getItemCount() {
        return artArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewArt;

        public ViewHolder(View view) {
            super(view);
            mImageViewArt = (ImageView) view.findViewById(R.id.imageview_item_art_group);
        }

        public void bindData(Art art) {

        }
    }

}
