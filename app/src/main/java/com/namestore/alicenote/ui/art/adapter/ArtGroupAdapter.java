package com.namestore.alicenote.ui.art.adapter;

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

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtGroupAdapter extends RecyclerView.Adapter<ArtGroupAdapter.ViewHolder> {

    private ArrayList<ArtObj> artArrayList;
    private Activity activity;
    private boolean isSwipeLayout;

    public interface OnArtItemClickListener {
        void onArtClick(int position);

        void onEditArt(int position);

        void onDeleteArt(int position);
    }

    public ArtGroupAdapter(Activity activity, ArrayList<ArtObj> artArrayList, boolean isSwipeLayout) {
        this.activity = activity;
        this.artArrayList = artArrayList;
        this.isSwipeLayout = isSwipeLayout;
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

    public void addItem(ArtObj artObj) {
        artArrayList.add(artObj);
        notifyItemInserted(artArrayList.size());
    }

    public void removeItem(int position) {
        artArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, artArrayList.size());
    }

    @Override
    public int getItemCount() {
        return artArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button mButtonArt;
        private SwipeLayout swipeLayout;
        private OnArtItemClickListener listener;
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            mButtonArt = (Button) view.findViewById(R.id.button_item_art_group);
            swipeLayout = (SwipeLayout) view.findViewById(R.id.swipe_layout);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_swipe);

            try {
                listener = (OnArtItemClickListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + "");
            }
        }

        public void bindData(ArtObj artObj) {
            mButtonArt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onArtClick(getAdapterPosition());
                }
            });

            if (isSwipeLayout) {
                linearLayout.setVisibility(View.GONE);
                swipeLayout.findViewById(R.id.edit).setVisibility(View.GONE);
                swipeLayout.findViewById(R.id.delete).setVisibility(View.GONE);
                return;
            }
            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewWithTag("swipeLayout"));
            swipeLayout.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditArt(getAdapterPosition());
                }
            });

            swipeLayout.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
                    listener.onDeleteArt(getAdapterPosition());
                }
            });

        }
    }

}
